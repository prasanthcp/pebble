

export default function PebbleDashboard() {

    const today = new Date();
    var pebbles = [
        {
            "topic": "Solve DSA Problems",
            "note": "Leet code coding problem 324",
            "state": "RUNNING",
            "creationDate": today
        },
        {
            "topic": "Java Handson Practice",
            "note": "Trying out Java Orielly Practice Lab",
            "state": "RUNNING",
            "creationDate": today
        }
    ];

    return (<>
    
        <title>Pebbles</title>
        <h1>Your Pebbles</h1>
        <table className="table">
            <thead>
                <tr>
                    <th>Topic</th>
                    <th>Note</th>
                    <th>State</th>
                    <th>Creation Date</th>
                </tr>
            </thead>
            <tbody>
                {
                pebbles.map (
                   pebble => (
                        <tr key={pebble.id}>
                            <td>{pebble.topic}</td>
                            <td>{pebble.note}</td>
                            <td>{pebble.state}</td>
                            <td>{pebble.creationDate.toString()}</td>
                        </tr>
                   )
                )}
            </tbody>

        </table>
    
    </>);
}