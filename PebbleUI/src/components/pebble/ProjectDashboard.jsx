import { use, useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import {retrieveProjectsApi} from "../../services/ProjectService";
import {useAuth} from "./security/Authcontext";

export default function ProjectDashboard() {

    const userId = useAuth().userId;
    const username = useAuth().username;
    const today = new Date();
    const navigate = useNavigate();
    const [projects, setProjects] = useState([]);

    useEffect( () => {if(userId!=0) retrieveProjects(userId)}, [userId] );

    function retrieveProjects(userId) {
        console.log('retrieve projects for a user'+userId);
        retrieveProjectsApi(userId).then(response => {
            console.log('projects data is'+response.data);
            setProjects(response.data);
        }).catch(error => console.log('error in retrieving projects'));;
    }

    return (<> 
    <h1>{username}'s Projects</h1>
    <div className="card">
        <table className="table">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Topic1</th>
                    <th>Topic2</th>
                    <th>Topic3</th>
                    <th>Topic4</th>
                    <th>Topic5</th>
                    <th>Target Date</th>
                    <th>Action1</th>
                    <th>Action2</th>
                </tr>
            </thead>
            <tbody>
                {
                projects.map (
                   project => (
                        <tr key={project.id}>
                            <td>{project.title}</td>
                            <td>{project.description}</td>
                            <td>{project.topic1}</td>
                            <td>{project.topic2}</td>
                            <td>{project.topic3}</td>
                            <td>{project.topic4}</td>
                            <td>{project.topic5}</td>
                            <td>{project.targetDate.toString()}</td>
                            <td>
                                <button className="btn btn-success m-5" onClick={()=> navigate(`/project/${project.id}`)}>
                                    Edit Project
                                </button>
                            </td>
                            <td>
                                <button className="btn btn-success m-5" onClick={()=> navigate(`/pebbles/${project.id}`)}>
                                    Your Pebbles
                                </button>
                            </td>
                        </tr>
                   )
                )}
            </tbody>

        </table>
        </div>
        <div>
            <button className="btn btn-success m-5" onClick={()=> navigate("/project/-1")}>
                Add New Project
            </button>
        </div>

    </>)
}