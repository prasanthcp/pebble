import { useEffect, useState } from "react";
import { useNavigate, useParams, useLocation } from "react-router-dom";
import { retrievePebblesApi } from "../../../services/PebbleService";
import dayjs from "dayjs";

export default function PebbleDashboard() {

    const {projectId} = useParams();
    const location = useLocation();
    const navigate = useNavigate();
    const [pebbles, setPebbles] = useState([]);
    const topics = location.state?.topics || [];

    useEffect( () => retrievePebbles(projectId), [projectId] )
    
    function retrievePebbles(projectId) {
        console.log('retrieve pebbles for projectId: '+projectId);
        retrievePebblesApi(projectId)
        .then(response => {
            console.log(response.data + " "+ projectId);
            setPebbles(response.data)
        })
        .catch((error)=> console.error("Error retrieving pebbles:", error));
    }

    return (
    <div className="card">
        <title>Pebbles</title>

        <h1>Pebbles for Your Project {pebbles.length > 0 ? ` - ${pebbles[0]?.project?.projectName}` : ""} </h1>
            {
                pebbles.length > 0 && <table className="table table-sm table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Pebble Id</th>
                            <th>Topic</th>
                            <th>Notes</th>
                            <th>Start Time</th>
                            <th>End Time</th>
                            <th>Studied</th>
                            <th>Creation Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                        pebbles.map ( (pebble) => {
                         
                        const start = dayjs(pebble.pebbleStartTime);
                        const end = dayjs(pebble.pebbleEndTime);
                        const studiedHours = end.diff(start, "hour", true).toFixed(2);

                        return (
                                <tr key={pebble.pebbleId} onClick={() => navigate(`/pebble/${pebble.pebbleId}`, {state: {topics}}, )} style={{ cursor: "pointer" }}>
                                    <td>{pebble.pebbleId}</td>
                                    <td>{pebble.topic}</td>
                                    <td>{pebble.pebbleNotes}</td>
                                    <td>{start.format("DD/MM/YYYY HH:mm")}</td>
                                    <td>{end.format("DD/MM/YYYY HH:mm")}</td>
                                    <td>{studiedHours}</td>
                                    <td>{dayjs(pebble.creationDate).format("DD/MM/YYYY HH:mm")}</td>
                                </tr>
                        )
                        })}
                    </tbody>
                </table>
            }
            <button className="btn btn-success m-5" onClick={()=> navigate(`/pebble/-1`, {state: {topics, projectId}}, )}> Add New Pebble</button>
    </div>);
}