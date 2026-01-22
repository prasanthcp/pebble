import { useState} from 'react';
import {useNavigate} from 'react-router-dom';
import {useAuth} from './Authcontext';

export default function Login() {

    const [username, setusername] = useState('Prasanth');
    const [password, setpassword] = useState('Welcome1');
    const [showWarning, setshowWarning] = useState(false);
    const navigate = useNavigate();
    const authContext = useAuth();
    const isAuthenticated = authContext.isAuthenticated;

    return (
    <> 
    <title>Login Component</title>
       {!isAuthenticated && <div className="Login Form">
            { showWarning && <div className="warningMessage" role="alert">Login failed try again</div> }
            
                <div className="login-form">
                    <div>
                        <label>UserName: </label>
                        <input type="text" name="username" placeholder="Enter your username"
                        value={username} onChange={event => setusername(event.target.value)}></input>
                    </div>
                    <div>
                        <label>Password: </label>
                        <input type="password" name="password" placeholder="Enter your password"
                        value={password} onChange={event => setpassword(event.target.value)}></input>
                    </div>
                    <div>
                        <button href="#" onClick={handleSubmit}> Login</button>
                        <button href="#" onClick={() => alert('In Progress Feature')}> Reset Password</button>
                    </div>
                </div>
        </div>}

        {isAuthenticated && <div><h1>Already Logged-in</h1></div>}
    </>
    )

    function handleSubmit() {
        
        const result = authContext.login(username, password);
        console.log("In handleSubmit method: "+ result.toString());
        
        if(result) {
            console.log("here");
            navigate(`/projects`);
        } else {
            setshowWarning(true);
            navigate('/login');
        }

    }

}