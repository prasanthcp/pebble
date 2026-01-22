import {useState, useRef} from 'react';

export default function Stopwatch() {

    const [seconds, setSeconds] = useState(0);
    const [minutes, setMinutes] = useState(0);
    const [hours, setHours] = useState(0);
    const [startButtonText, setStartButtonText] = useState("Start");
    const [secId, setSecId] = useState(0);
    const intervalRef = useRef(null);

    return (
        <div>
            <br/><br/>
            <h1>{hours} : {minutes} : {seconds}</h1>
            <button className="btn btn-primary my-4 me-3" onClick={StartAction}>{startButtonText}</button>
            {startButtonText!="Start" && <button className="btn btn-secondary" onClick={StopAction}>Stop</button>}
        </div>
    );
    
    function StartAction() {
        
        if(startButtonText === "Start") {
            
            setStartButtonText("Pause");
        } else if(startButtonText === "Pause") {
            clearInterval(intervalRef.current);
            setStartButtonText("Play");
        } else if(startButtonText === "Play") {
            setStartButtonText("Pause");
        }


    }

    // function startTimer() {
    //     intervalRef.current = setInterval(
    //         () => {
    //                 setSeconds(
    //                     (prevSeconds)=> {
    //                         if(prevSeconds==59) {

    //                             setMinutes(
    //                                 (prevMinutes) 
    //                             )

    //                             return 0;
    //                         }
    //                         return prevSeconds+1;
    //                     }
    //                 ); 
    //             },1000)

    // }
            // ()=> { setSeconds((prevSeconds)=> (prevSeconds+1)%60); },1000);
            // setInterval(()=> { setMinutes((prevMinutes)=> (prevMinutes+1)%60); },60000);
            // setInterval(()=> { setHours((prevHours)=> (prevHours+1)%60); },3600000);

    function StopAction() {
        clearInterval(intervalRef.current);
    }

}