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

export const retrievePebblesApi = (projectId) => {
    return apiClient.get('/pebble/getAll/'+projectId);
}

export const retrievePebbleApi = (pebbleId) => {
    return apiClient.get('/pebble/get/'+pebbleId); 
}

export const createPebbleApi = (projectId,pebble) => {
    return apiClient.post('/pebble/add/'+projectId, pebble);
}

export const updatePebbleApi = (pebble,pebbleId) => {
   return apiClient.post('/pebble/update/'+pebbleId, pebble);
}

/*

project/getAll/1
project/add
project/update/11
project/get/11

pebble/getAll/{project_id}
pebble/get/{pebble_id}
pebble/add/{project_id}
pebble/update/{pebble_id}

*/