import axios from 'axios';
import API_BASE_URL from '../config';

const apiClient = axios.create(
    {
        baseURL: API_BASE_URL //"http://localhost:8080",
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

export const retrievePebblesApi = (projectId) => {
    return apiClient.get('/pebble/getAll/'+projectId);
}

export const retrievePebbleApi = (pebbleId) => {
    return apiClient.get('/pebble/get/'+pebbleId); 
}

export const createPebbleApi = (pebble, projectId) => {
    return apiClient.post('/pebble/add/'+projectId, pebble);
}

export const updatePebbleApi = (pebble, pebbleId) => {
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