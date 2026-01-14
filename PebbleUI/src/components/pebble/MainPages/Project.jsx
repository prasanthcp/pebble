import { Formik, Form, Field, ErrorMessage } from "formik";
import moment from "moment";
import { useNavigate, useParams } from "react-router-dom";
import { useAuth } from "../security/Authcontext";
import { retrieveProjectApi, createProjectApi, updateProjectApi } from "../../../services/ProjectService";
import { useEffect, useState } from "react";

export default function Project() {
  const { projectId } = useParams();
  const navigate = useNavigate();
  const userId = useAuth().userId;
  const [project, setProject] = useState(null);

  useEffect(() => {
    if (projectId === "-1") {
      const today = moment().format("YYYY-MM-DDTHH:mm");

      setProject({
        projectName: "Learning a New Skill",
        projectDescription: "Acquire this new skill in next six months",
        creationDate: today,
        lastUpdateDate: today,
        objectVersion: 1,
        projectStartDate: today,
        topic1: "Work on topic1",
        topic2: "Work on topic2",
        topic3: "Work on topic",
        projectStatus: "Started",
        frequency: 6,
      });
    } else {
      retrieveProjectApi(userId)
        .then((response) => setProject(response.data))
        .catch((error) => console.error("Couldn't get projects for this user:", error));
    }
  }, [projectId, userId]);

  function validate(values) {
    
    const errors = {};

    if (!values.projectDescription) errors.projectDescription = "Enter a projectDescription";
    
    if (!values.projectName || values.projectName.length < 5)
      errors.projectName = "Enter at least 5 Characters in projectName";
    
    if (!values.projectStartDate || !moment(values.projectStartDate).isValid())
      errors.projectStartDate = "Enter a valid Target Date";
    else if (moment(values.projectStartDate).isBefore(moment()))
      errors.projectStartDate = "Enter a Target Date in the Future";
    
    if (!values.topic1 && !values.topic2 && !values.topic3)
      errors.topic1 = "Enter at least one Topic";
    
    if (values.frequency < 1 || values.frequency > 6)
      errors.frequency = "Enter Number of available days per week";
    
    return errors;

  }

  async function handleSubmit(values) {
    try {
      if (projectId === "-1") {
        await createProjectApi(values);
      } else {
        await updateProjectApi(values, projectId);
      }
      navigate("/projects");
    } catch (error) {
      console.error("Submit failed:", error);
    }
  }

  if (!project) return <div>Loading...</div>;

  return (
    <div>
      <h1>Project Details</h1>
      <Formik
        initialValues={project}
        enableReinitialize
        validate={validate}
        onSubmit={handleSubmit}
      >
        <Form>
          <fieldset>
            <label>Project Name:</label>
            <Field type="text" name="projectName" />
            <ErrorMessage name="projectName" component="div" className="alert alert-warning" />
          </fieldset>

          <fieldset>
            <label>Description:</label>
            <Field type="text" name="projectDescription" />
            <ErrorMessage name="projectDescription" component="div" className="alert alert-warning" />
          </fieldset>

          <fieldset>
            <label>Target Date:</label>
            <Field type="datetime-local" name="projectStartDate" />
            <ErrorMessage name="projectStartDate" component="div" className="alert alert-warning" />
          </fieldset>

          <fieldset>
            <label>Topic 1:</label>
            <Field type="text" name="topic1" />
            <ErrorMessage name="topic1" component="div" className="alert alert-warning" />
          </fieldset>

          <fieldset>
            <label>Topic 2:</label>
            <Field type="text" name="topic2" />
          </fieldset>

          <fieldset>
            <label>Topic 3:</label>
            <Field type="text" name="topic3" />
          </fieldset>

          <fieldset>
            <label>Frequency (days per week):</label>
            <Field type="number" name="frequency" min="1" max="6" />
            <ErrorMessage name="frequency" component="div" className="alert alert-warning" />
          </fieldset>

          <div>
            <button className="btn btn-primary" type="submit">Save</button>
            <button className="btn btn-secondary" type="button" onClick={() => navigate("/projects")}>
              Cancel
            </button>
          </div>
        </Form>
      </Formik>
    </div>
  );
}
