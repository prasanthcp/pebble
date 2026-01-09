import axios from 'axios';

const apiClient = axios.create(
    {
        baseURL: "http://localhost:5001"
    }
);

export const retrieveProjectsApi = (userId) => {
    apiClient.get('/project/getAll/'+userId);
}

export const retrieveProjectApi = (projectId) => {
    //apiClient.get('/project/'+projectId);
}

export const createProjectApi = (userId) => {
    apiClient.get('/project/getAll/'+userId);
}



/*
Method: POST
URL: localhost:5000/addProject
Payload: {
    "title": "Learn Java",
    "description": "I want to learn coding and programming in java in next 6months",
    "topics": [
        "topic1": "Core Java",
        "topic2": "Solve DSA Problems",
        "topic3": "Spring Framework 1",
        "topic4": "Practice Hands On Lab"
    ],
    "targetDate": "01-07-2026"
}

Method: GET
URL: localhost:5000/project/1
Content-Type: application/json
Response: {
    "title": "Learn Java",
    "description": "I want to learn coding and programming in java in next 6months",
    "topics": [
        "topic1": "Core Java",
        "topic2": "Solve DSA Problems",
        "topic3": "Spring Framework 1",
        "topic4": "Practice Hands On Lab"
    ],
    "targetDate": "01-07-2026"
}

Method: POST
URL: localhost:5000/addPebble
Payload: {
    "Topic": "Solve DSA Problems"
    "note": "Leet code coding problem 324",
    "state": "running"
}

Method: GET
URL: localhost:5000/pebbles

Method: GET
URL: localhost:5000/pebble/1
*/