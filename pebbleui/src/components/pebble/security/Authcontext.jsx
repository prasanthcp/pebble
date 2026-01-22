import { createContext, useContext, useState } from "react";
import axios from 'axios';
import API_BASE_URL from "../../../config";

// Create axios client with baseURL
const apiClient = axios.create(
    { 
        baseURL: API_BASE_URL //"http://localhost:8080" //, auth: { username: "Prasanth", password: "Welcome1" }
    }
);

// Attach interceptor to apiClient
apiClient.interceptors.request.use(config => {
    const Token = localStorage.getItem("Token");
    console.log("intercepting with Token as : "+ Token)
    if(Token) {
        console.log("inside if:"+ Token)
        config.headers.Authorization =`Bearer ${Token}`;
    } else {
        delete config.headers.Authorization;
    }
    return config;
});

// API call to retrieve user
export const retrieveUser = (username) => {
    return apiClient.get(`/getUser/${username}`);
}

// Context setup
export const AuthContext = createContext();
export const useAuth = () => useContext(AuthContext);

export default function AuthProvider({children}) {

const [isAuthenticated, setIsAuthenticated] = useState(false);
const [username,setUsername] = useState("DummyUsername");
const [userId,setUserId] = useState(0);

return (
    <AuthContext.Provider 
    value= { {isAuthenticated, setIsAuthenticated, login, logout, username, userId} }>
        {children}
    </AuthContext.Provider>
);

// Logout clears state + Token
function logout() {
    setIsAuthenticated(false);
    localStorage.removeItem("Token");
    setUsername("dummyUsername");
    setUserId(0);
}

async function login(username, password) {
    try {
        console.log("In login method: "+ username+" "+password);
        const res = await apiClient.post("/auth/login",{username, password});
        const Token = res.data.Token;
        console.log("In login method Token: "+ Token);
        if(Token) localStorage.setItem("Token",res.data.Token);
        setUsername(res.data.username);
        setUserId(res.data.userId);
        setIsAuthenticated(true);
        return true;
    } catch(error) {
        console.log("Login Failed " + error);
        setIsAuthenticated(false);
        return false;
    }
}

}


