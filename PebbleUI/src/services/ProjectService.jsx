import axios from 'axios';

const apiClient = axios.create(
    {
        baseURL: "http://localhost:5001",
        // auth: {
        //     username: "Prasanth",
        //     password: "Welcome1"
        // }
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

export const retrieveProjectsApi = (userId) => {
    return apiClient.get('/project/getAll/'+userId);
}

export const retrieveProjectApi = (projectId) => {
    return apiClient.get('/project/get/'+projectId); 
}

export const createProjectApi = (project) => {
    return apiClient.post('/project/add', project);
}

export const updateProjectApi = (project, projectId) => {
   return apiClient.put('/project/update/'+projectId, project);
}