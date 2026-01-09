import { use, useEffect, useState } from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { retrievePebblesApi } from "../../services/PebbleService";

export default function PebbleDashboard() {

    const today = new Date();
    const {projectId} = useParams();
    const navigate = useNavigate();
    const [pebbles, setPebbles] = useState([]);

    useEffect( () => retrievePebbles(projectId), [] )
    
    function retrievePebbles(projectId) {
        console.log('retrieve pebbles for projectId: '+projectId);
        retrievePebblesApi(projectId)
        .then(response => {
            console.log(response.data);
            setPebbles(response.data)
        })
        .catch();
    }

    return (
    <div className="card">
        <title>Pebbles</title>
        <h1>Your Pebbles</h1>
            {
                pebbles!={} && <table className="table">
                    <thead>
                        <tr>
                            <th>Note</th>
                            <th>State</th>
                            <th>Topic1 Studied</th>
                            <th>Topic2 Studied</th>
                            <th>Topic3 Studied</th>
                            <th>Topic4 Studied</th>
                            <th>Topic5 Studied</th>
                            <th>Creation Date</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                        pebbles.map (
                        pebble => (
                                <tr key={pebble.id}>
                                    <td>{pebble.notes}</td>
                                    <td>{pebble.state}</td>
                                    <td>{pebble.topic1Studied}</td>
                                    <td>{pebble.topic2Studied}</td>
                                    <td>{pebble.topic3Studied}</td>
                                    <td>{pebble.topic4Studied}</td>
                                    <td>{pebble.topic5Studied}</td>
                                    <td>{pebble.creationDate}</td>
                                    <td>
                                        <button className="btn btn-success m-5" onClick={()=> navigate(`/pebble/${pebble.id}`)}>
                                            Edit
                                        </button>
                                    </td>
                                </tr>
                        )
                        )}
                    </tbody>
                </table>
            }
            <button className="btn btn-success m-5" onClick={()=> navigate(`/pebble/-1`)}> Add New Pebble</button>
    </div>);
}