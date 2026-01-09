import { use, useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import {retrieveProjectsApi} from "../../services/ProjectService";
import {useAuth} from "./security/Authcontext";

export default function ProjectDashboard() {

    const userId = useAuth().userId;
    const username = useAuth().username;
    const today = new Date();
    const navigate = useNavigate();
    const [projects, setProjects] = useState({});

    useEffect(
        () => {
            console.log('retrieve projects for a user');
            retrieveProjects(userId)
            .then(response => setProjects(response.data))
            .catch(error => console.log('error in retrieving projects'));
        }, []
    )

    function retrieveProjects(userId) {
        console.log('retrieve projects for a user'+userId);
        retrieveProjectsApi(userId);
    }

    // var projects = [
    //     {   
    //         "title": "Learn Java add test",
    //         "creationDate": "2026-01-07",
    //         "description": "Learn Java coding and programming in next six months",
    //         "lastUpdateDate": today,
    //         "objectVersion": 1,
    //         "targetDate": today,
    //         "topic1": "Solve DSA",
    //         "topic2": "Read Core Java",
    //         "topic3": "Hands-on Projects",
    //         "topic4": "Spring Basics",
    //         "topic5": "Java Streams"
    //     },
    //     {
    //         "title": "Learn Python add test",
    //         "creationDate": "2026-01-07",
    //         "description": "Learn Python coding and programming in next six months",
    //         "lastUpdateDate": today,
    //         "objectVersion": 1,
    //         "targetDate": new Date(today.getFullYear()+1, today.getMonth(), today.getDate()),
    //         "topic1": "Solve DSA",
    //         "topic2": "Read Core Python",
    //         "topic3": "Hands-on Projects",
    //         "topic4": "Numpy Basics",
    //         "topic5": "Python Streams"
    //     },
    // ];

    return (<> 
    <h1>{username}'s Projects</h1>
        {/* <div className="welcome">Lets make your day productive.</div> 
        <Link to="/pebbleDashboard">Here are your Pebbles</Link> */}
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
                        </tr>
                   )
                )}
            </tbody>

        </table>

        <div>
            <button className="btn btn-success m-5" onClick={()=> navigate("/project/-1")}>
                Add New Project
            </button>
        </div>

    </>)
}