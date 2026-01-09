import { Formik, Form, Field, ErrorMessage} from "formik";
import moment from "moment";
import { useParams } from "react-router-dom";
import { useAuth } from "./security/Authcontext";
import {retrieveProjectsApi, createProjectApi} from "../../services/ProjectService";
import { useEffect, useState } from "react";

export default function Project() {

    const {projectId} = useParams();
    const userId = useAuth().userId;
    const [projects, setProjects] = useState({});
    const today = new Date();

    useEffect(
    () => {
            if(projectId==-1) {
                var project = { "title": "Learning a New Skill", "description": "acquire a new skill in next six months",
                "creationDate": today, "lastUpdateDate": today, "objectVersion": 1, "targetDate": today, "topic1": "", "topic2": "", "topic3": "", "topic4": "", "topic5": ""
                }
                setProjects(project);
            } else {
                setProjects(retrieveProjects(userId));
            }
        }, []
    )
    
    function validate(values) {
        let errors = {};

        if(values.description == null || values.description.length == 0 )
            errors.description = "Enter a Description";
        
        if(values.title == null || values.title.length < 3 )
            errors.title = "Enter at least 5 Characters in Title";
        
        if(values.targetDate == null || values.targetDate.length == 0 || !moment(values.targetDate).isValid())
            errors.targetDate = "Enter a valid Target Date";
        if(moment(values.targetDate).isBefore(moment()))
            errors.targetDate = "Enter a Target Date in the Future";    

        if(values.topic1 == null && values.topic2 == 0 && values.topic3 == 0 && values.topic4 == 0 && values.topic5 == 0 )
            errors.topic1 = "Enter atleast one Topic";

        return errors;

    }


    return (
        <div>

            <h1>Project Details</h1>
            <Formik initialValues={projects}
            enableReinitialize={true}
            validate = {validate}
            >
            { (props) => (
                        <Form>
                        <fieldset>
                            <label>Title: </label>
                            <input type="text" name="text" value={projects.title} />
                        </fieldset>

                        <fieldset>
                            <label>Description: </label>
                            <input type="text" name="text" value={projects.description} />
                        </fieldset>

                        <fieldset>
                            <label>TargetDate: </label>
                            <input type="text" name="text" value={projects.targetDate} />
                        </fieldset>

                        <fieldset>
                            <label>Topic1: </label>
                            <input type="text" name="text" value={projects.topic1} />
                        </fieldset>

                        <fieldset>
                            <label>Topic1: </label>
                            <input type="text" name="text" value={projects.topic1} />
                        </fieldset>

                        <fieldset>
                            <label>Topic2: </label>
                            <input type="text" name="text" value={projects.topic2} />
                        </fieldset>

                        <fieldset>
                            <label>Topic3: </label>
                            <input type="text" name="text" value={projects.topic3} />
                        </fieldset>

                        <fieldset>
                            <label>Topic4: </label>
                            <input type="text" name="text" value={projects.topic4} />
                        </fieldset>

                        <fieldset>
                            <label>Topic5: </label>
                            <input type="text" name="text" value={projects.topic5} />
                        </fieldset>
                        <div>
                            <button type="submit" onClick={updateProject}>Save</button>
                        </div>
                        </Form>
                        
                    )
            }
            </Formik>

        </div>
    );

    function getProjectById(projectId) {
        return null;
    }


    function retrieveProjects(userId) {
        console.log('retrieve projects for a user'+userId);
        retrieveProjectsApi(userId);
    }


    function updateProject(event) {
        var project = event.values;
        console.log("Update Project called"+ project);
        if(project.projectId==-1) {
            //call create project api
            createProjectApi(project)
            .then()
            .catch((error)=> console.log(error));
        } else {
            //call update project api
            //updateProjectApi(project);
        }
    }


}