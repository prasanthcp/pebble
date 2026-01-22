import {useNavigate} from "react-router-dom";
import {useAuth} from './Authcontext';

export default function Logout() {

    const navigate = useNavigate();
    const authContext = useAuth();
    return(
        <div>
            <title>Logout</title>
            <h2>Are you sure you want to Logout ?</h2>
            <button className="btn btn-primary" onClick={logoutAction}>Yes</button>
            <button className="btn btn-secondary" onClick={DontlogoutAction}>No</button>
        </div>
    );

    function logoutAction(){
        alert("You have been logged out successfully!");
        authContext.setIsAuthenticated(false);
        navigate('/login');
    }

    function DontlogoutAction(){
        alert("Logout cancelled!");
        navigate('/projects');
    }

}

