import { Formik, Form, Field, ErrorMessage } from "formik";
import moment from "moment";
import { useNavigate, useParams } from "react-router-dom";
import { useAuth } from "./security/Authcontext";
import { retrieveProjectApi, createProjectApi, updateProjectApi } from "../../services/ProjectService";
import { useEffect, useState } from "react";

export default function Project() {
  const { projectId } = useParams();
  const navigate = useNavigate();
  const userId = useAuth().userId;
  const [project, setProject] = useState(null);
  const today = new Date();

  useEffect(() => {
    if (projectId == -1) {
      setProject({
        title: "Learning a New Skill",
        description: "acquire a new skill in next six months",
        creationDate: today,
        lastUpdateDate: today,
        objectVersion: 1,
        targetDate: today,
        topic1: "",
        topic2: "",
        topic3: "",
        topic4: "",
        topic5: ""
      });
    } else {
      retrieveProjectApi(userId)
        .then((response) => setProject(response.data))
        .catch((error) => console.log("couldn't get projects for this user " + error));
    }
  }, [projectId, userId]);

  function validate(values) {
    let errors = {};
    if (!values.description) errors.description = "Enter a Description";
    if (!values.title || values.title.length < 5) errors.title = "Enter at least 5 Characters in Title";
    if (!values.targetDate || !moment(values.targetDate).isValid())
      errors.targetDate = "Enter a valid Target Date";
    if (moment(values.targetDate).isBefore(moment()))
      errors.targetDate = "Enter a Target Date in the Future";
    if (!values.topic1 && !values.topic2 && !values.topic3 && !values.topic4 && !values.topic5)
      errors.topic1 = "Enter at least one Topic";
    return errors;
  }

  async function handleSubmit(values) {
    try {
      if (projectId == -1) {
        const response = await createProjectApi(values);
        navigate("/projects");
      } else {
        await updateProjectApi(values, projectId);
        navigate("/projects");
      }
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
        enableReinitialize={true}
        validate={validate}
        onSubmit={handleSubmit}
      >
        <Form>
          <fieldset>
            <label>Title: </label>
            <Field type="text" name="title" />
            <ErrorMessage name="title" component="div" className="alert alert-warning" />
          </fieldset>

          <fieldset>
            <label>Description: </label>
            <Field type="text" name="description" />
            <ErrorMessage name="description" component="div" className="alert alert-warning" />
          </fieldset>

          <fieldset>
            <label>Target Date: </label>
            <Field type="text" name="targetDate" />
            <ErrorMessage name="targetDate" component="div" className="alert alert-warning" />
          </fieldset>

          <fieldset>
            <label>Topic1: </label>
            <Field type="text" name="topic1" />
            <ErrorMessage name="topic1" component="div" className="alert alert-warning" />
          </fieldset>

          <fieldset>
            <label>Topic2: </label>
            <Field type="text" name="topic2" />
          </fieldset>

          <fieldset>
            <label>Topic3: </label>
            <Field type="text" name="topic3" />
          </fieldset>

          <fieldset>
            <label>Topic4: </label>
            <Field type="text" name="topic4" />
          </fieldset>

          <fieldset>
            <label>Topic5: </label>
            <Field type="text" name="topic5" />
          </fieldset>

          <div>
            <button className="btn btn-primary" type="submit">Save</button>
            <button className="btn btn-secondary" type="button" onClick={() => navigate("/projects")}>Cancel</button>
          </div>
        </Form>
      </Formik>
    </div>
  );
}
