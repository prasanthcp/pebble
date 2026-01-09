import { createContext, useContext, useState, useEffect } from "react";
import axios from 'axios';

const apiClient = axios.create(
    { 
        baseURL: "https://pebble-backend.onrender.com",  //"http://localhost:5001",
        auth: {
            username: "Prasanth",
            password: "Welcome1"
        }
    }
);

export const retrieveUser = (username) => {
    return apiClient.get('/getUser/'+username);
}

export const AuthContext = createContext(); // create context> put state > share with other components
export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({children}) {

    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [username,setUsername] = useState("DummyUsername");
    const [userId,setUserId] = useState(0);

    useEffect( ()=>
    {
        if (isAuthenticated) { 
            const fetchUser = async () => { 
                try { 
                    const response = await retrieveUser(username); 
                    // await the Axios Promise 
                    console.log("retrieveUser response:", response); 
                    const user_id = response.data.userId;
                    setUserId(user_id); 
                    console.log("user_id is:", userId+ " "+user_id); 
                } 
                catch (error) { 
                    console.error("error in retrieving user", error); 
                } 
            }; 
            fetchUser(); // call the async function 
        }
    }, [isAuthenticated, username]
    );

    return (
        <AuthContext.Provider value= { {isAuthenticated, setIsAuthenticated, login, logout, username, userId} }>
            {children}
        </AuthContext.Provider>
    );

    function login(username, password) {
        console.log("In login method: "+ username+" "+password);
        const result = username === 'Prasanth' && password === 'Welcome1';

        if(result){
            setUsername(username);
            setIsAuthenticated(result);
        }

        return result;
    }

    function logout() {
        setIsAuthenticated(false);
        setUsername("dummyUsername");
        setUserId(0);
    }

}


