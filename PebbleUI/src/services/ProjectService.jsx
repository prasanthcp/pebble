import axios from 'axios';

const apiClient = axios.create(
    {
        baseURL: "https://pebble-backend.onrender.com", //"http://localhost:5001",
        auth: {
            username: "Prasanth",
            password: "Welcome1"
        }
    }
);

export const retrieveProjectsApi = (userId) => {
    return apiClient.get('/project/getAll/'+userId);
}

export const retrieveProjectApi = (projectId) => {
    return apiClient.get('/project/get/'+projectId); 
}

export const createProjectApi = (project) => {
    return apiClient.post('/project/add/', project);
}

export const updateProjectApi = (project, projectId) => {
   return apiClient.post('/project/update/'+projectId, project);
}