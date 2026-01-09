import { createContext, useContext, useState, useEffect } from "react";
import axios from 'axios';

const apiClient = axios.create(
    {
        baseURL: "http://localhost:5001",
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

    const number = 0;
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [username,setUsername] = useState("DummyUsername");
    const [userId,setUserId] = useState(0);
    const [User,setUser] = useState({});

    useEffect( ()=>
    {
        if(isAuthenticated) {
            retrieveUser(username).then(
                response => {
                    console.log(response);
                    setUser(response.data);
                    setUserId(response.data.userId)
                }
            ).catch(error => console.log("error in retrieving user",error));

        }
    }, [isAuthenticated, username]
    );

    return (
        <AuthContext.Provider value= { {number, isAuthenticated, setIsAuthenticated, login, logout, username, userId} }>
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
    }

}


