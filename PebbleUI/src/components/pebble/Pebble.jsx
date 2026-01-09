import { Formik, Form, Field, ErrorMessage } from "formik";
import moment from "moment";
import { useParams, useNavigate } from "react-router-dom";
import { useAuth } from "./security/Authcontext";
import { retrievePebbleApi, updatePebbleApi, createPebbleApi } from "../../services/PebbleService";
import { useEffect, useState } from "react";

export default function Pebble() {
  const { pebbleId } = useParams();
  const navigate = useNavigate();
  const userId = useAuth().userId;
  const [pebble, setPebble] = useState(null);
  //const today = new Date();

  useEffect(() => {
    if (pebbleId == -1) {
      setPebble({
        notes: "new pebble concurrency day 1",
        topic1Studied: 0.02,
        topic2Studied: 0.01,
        topic3Studied: 0.0,
        topic4Studied: 0.0,
        topic5Studied: 0.0,
        //targetDate: today.toISOString().split("T")[0], // format as yyyy-mm-dd
      });
    } else {
      retrievePebbleApi(pebbleId)
        .then((response) => setPebble(response.data))
        .catch((error) => console.log(error));
    }
  }, [pebbleId]);

  function validate(values) {
    let errors = {};
    if (!values.notes) errors.notes = "Enter notes";
    // if (!values.targetDate || !moment(values.targetDate).isValid())
    //   errors.targetDate = "Enter a valid Target Date";
    return errors;
  }

  async function handleSubmit(values) {
    try {
      if (pebbleId == -1) {
        const response = await createPebbleApi(1, values);
        const newProjectId = response.data.project.id;
        navigate(`/pebbles/${newProjectId}`);
      } else {
        const response = await updatePebbleApi(values, pebbleId);
        const newProjectId = response.data.project.id;
        navigate(`/pebbles/${newProjectId}`);
      }
    } catch (error) {
      console.error("Submit failed:", error);
    }
  }

  if (!pebble) return <div>Loading...</div>;

  return (
    <div>
      <h1>Pebble Details</h1>
      <Formik
        initialValues={pebble}
        enableReinitialize={true}
        validate={validate}
        onSubmit={handleSubmit}
      >
        <Form>
          <fieldset>
            <label>Notes: </label>
            <Field type="text" name="notes" />
            <ErrorMessage name="notes" component="div" className="alert alert-warning" />
          </fieldset>

          <fieldset>
            <label>Topic 1 Studied: </label>
            <Field type="number" step="0.01" name="topic1Studied" />
          </fieldset>

          <fieldset>
            <label>Topic 2 Studied: </label>
            <Field type="number" step="0.01" name="topic2Studied" />
          </fieldset>

          <fieldset>
            <label>Topic 3 Studied: </label>
            <Field type="number" step="0.01" name="topic3Studied" />
          </fieldset>

          <fieldset>
            <label>Topic 4 Studied: </label>
            <Field type="number" step="0.01" name="topic4Studied" />
          </fieldset>

          <fieldset>
            <label>Topic 5 Studied: </label>
            <Field type="number" step="0.01" name="topic5Studied" />
          </fieldset>

          <div>
            <button type="submit" className="btn btn-primary">Save</button>
            <button
              type="button"
              className="btn btn-secondary"
              onClick={() => navigate("/projects")}
            >
              Cancel
            </button>
          </div>
        </Form>
      </Formik>
    </div>
  );
}
