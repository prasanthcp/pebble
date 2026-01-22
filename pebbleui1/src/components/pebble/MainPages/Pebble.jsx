import { Formik, Form, Field, ErrorMessage } from "formik";
import moment from "moment";
import { useParams, useNavigate, useLocation } from "react-router-dom";
import { useAuth } from "../security/Authcontext";
import { retrievePebbleApi, updatePebbleApi, createPebbleApi } from "../../../services/PebbleService";
import { useEffect, useState } from "react";

export default function Pebble() {
  const { pebbleId } = useParams();
  const location = useLocation();
  const navigate = useNavigate();
  const userId = useAuth().userId;
  const [pebble, setPebble] = useState(null);
  const topics = location.state?.topics || [];
  const projectId = location.state?.projectId;

  useEffect(() => {
    if (pebbleId === "-1") {
      setPebble({
        pebbleNotes: "Studying ..",
        topic: "",
        pebbleStartTime: moment().format("YYYY-MM-DDTHH:mm"),
        pebbleEndTime: moment().add(1, "hours").format("YYYY-MM-DDTHH:mm"),
      });
      
    } else {
      retrievePebbleApi(pebbleId)
        .then((response) => {
          setPebble(response.data);
        })
        .catch((error) => console.error("Retrieve failed:", error));
    }
  }, [pebbleId]);

  function validate(values) {
    const errors = {};
    if (!values.pebbleNotes) errors.pebbleNotes = "Enter notes !";
    if (!values.topic) errors.topic = "Select a topic !";
    if (!values.pebbleStartTime || !moment(values.pebbleStartTime).isValid())
      errors.pebbleStartTime = "Enter a valid Start Time !";
    if (!values.pebbleEndTime || !moment(values.pebbleEndTime).isValid())
      errors.pebbleEndTime = "Enter a valid End Time !";
    else if (moment(values.pebbleEndTime).isBefore(moment(values.pebbleStartTime)))
      errors.pebbleEndTime = "End Time must be after Start Time !";
    return errors;
  }

  async function handleSubmit(values) {
    try {
      const response =
        pebbleId === "-1"
          ? await createPebbleApi(values, projectId)
          : await updatePebbleApi(values, pebbleId);

      navigate(-1,{state: {topics}});
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
        enableReinitialize
        validate={validate}
        onSubmit={handleSubmit}
      >
        <Form>
          <fieldset>
            <label>Notes:</label>
            <Field type="text" name="pebbleNotes" />
            <ErrorMessage name="pebbleNotes" component="div" className="alert alert-warning" />
          </fieldset>

          <fieldset>
            <label>Topic:</label>
            <Field as="select" name="topic" className="form-control">
              <option value="">-- Select a topic --</option>
              {topics.map((t) => (
                <option key={t} value={t}>{t}</option>
              ))}
            </Field>
            <ErrorMessage name="topic" component="div" className="alert alert-warning" />
          </fieldset>

          <fieldset>
            <label>Start Time:</label>
            <Field type="datetime-local" name="pebbleStartTime" 
            min={moment().startOf('day').format('YYYY-MM-DDTHH:mm')}
            max={moment().endOf('day').format('YYYY-MM-DDTHH:mm')}/>
            <ErrorMessage name="pebbleStartTime" component="div" className="alert alert-warning" />
          </fieldset>

          <fieldset>
            <label>End Time:</label>
            <Field type="datetime-local" name="pebbleEndTime" 
            min={moment().startOf('day').format('YYYY-MM-DDTHH:mm')}
            max={moment().endOf('day').format('YYYY-MM-DDTHH:mm')}/>
            <ErrorMessage name="pebbleEndTime" component="div" className="alert alert-warning" />
          </fieldset>

          <div>
            <button type="submit" className="btn btn-primary my-4 me-3">Save</button>
            <button type="button" className="btn btn-secondary" onClick={() => navigate(-1, {state: {topics}})}>
              Cancel
            </button>
          </div>
        </Form>
      </Formik>
    </div>
  );
}
