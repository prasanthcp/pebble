Project Plan
------------
**Title:** Pebble
**Description:** Study Management Tool

**Tech Stack**
------------------
Back-End: Spring Boot 3+, Java; 
Front-End: React Javascript;
Database: MySql;
Deployment: OCI, Docker;

**Sequence of execution:**
---------------
[ Note: Everyday Push the changes to GIT ]
1) List out main Features
2) Create a Database Table Schema
3) Identifying pages and endpoints
4) Revisit above points
5) How to display data on the UI ?

**Main Features,**
-----------------
1) User logs in
2) Project's Dashboard Page
   - shows the list of selectable existing projects (if any)
   - option to Add a new project
3) Project Page
   - Add Project > Fill Project form > Save > Shows Project details + Add Pebbles Option
   - Existing Project > Shows Project details + List of Existing Pebbles (decs table form) + Add Pebble option
4) Pebble Page
   - Select a Topic [Required] and Description [Optional] and time studied > save > show readonly details of that pebble
   - Better option: 
     - User enters target study time > saves pebble > it shows readonly details & a stopwatch to track study slot 
     - Stopwatch alerts a beep every {0.5 hour}
     - Studies for 1 hour and stops stopwatch 
     - if forgot to stop it or closed app abruptly [pause it with description]
     - Update the status of pebble and studied time

**Database Schema**
-------------------
Table 1: Project
Columns: id (primary key), userid (foreign key), title, description, topic1, topic2, 
topic3, topic4, topic5, target_date, creation_date, last_update_date, object_version

Table 2: Pebble
Columns: id (primary key), project_id (foreign key), userid (foreign key), note, 
topic1, topic2, topic3, topic4, topic5, state, creation_date, last_update_date

Table 3: Users
Columns: userid (primary key), username, encrypted_password, creation_date, last_update_date 

**End Points Description**
---------------

For Feature 1,
1) Authenticate:- Post: /authenticate  Request Payload: {username, password} | Response Payload: {JWT, user details }
2) Get User Details:- Get: /getUser/{username} Payload: JWT Token
3) Logout:- Post URL: /logout | to timeout JWT Token is needed ?

For Feature 2,3,
1) Get Projects list for a user:- Get: project/getAll/{userId} | Response Payload: {projects array}
2) Add Project:- Post: project/add
3) Edit Project:- Post: project/update/{project_id} | RequestPayload: {new project json}
4) Get a project:- project/get/{project_id} | Response Payload: {Project details}
5) Get pebbles list:- pebble/getAll/{project_id} | Response: {pebbles array}

For Feature 4,
1) Get A pebble:- Get: pebble/get/{id} | response: {pebble json} 
2) Add Pebble:- Post: pebble/add/{project_id} | request: {pebble json}
3) Edit Pebble:- Post: pebble/update | request: {updated pebble json}

More Features: 
- delete project/delete pebble
- validation: only todays pebbles can be editable, rest are readonly

Existing Endpoints:

getUser/Prasanth

project/getAll/1
project/add
project/update/11
project/get/11

pebble/getAll/{project_id}
pebble/get/{pebble_id}
pebble/add/{project_id}
pebble/update/{pebble_id}

Sample request payload:
------------------------
{
    "creationDate": "2026-01-09",
    "description": "edited Learn Python coding and programming in next six months",
    "lastUpdateDate": "2026-01-09",
    "objectVersion": 1,
    "targetDate": "2026-07-08",
    "title": "Learn Python",
    "topic1": "Solve DSA",
    "topic2": "Read Core Python",
    "topic3": "Hands-on Projects",
    "topic4": "Spring Basics",
    "topic5": "Python Streams"
}

{
    "notes": "updateing concurrency day 2",
    "objectVersion": 1,
    "topic1Studied": 0.02,
    "topic2Studied": 0.0,
    "topic3Studied": 0.0,
    "topic4Studied": 0.0,
    "topic5Studied": 0.0
}

{
"notes": "new pebble concurrency day 1",
"topic1Studied": 0.02,
"topic2Studied": 0.01,
"topic3Studied": 0.0,
"topic4Studied": 0.0,
"topic5Studied": 0.0
}

------------------- ------------------- 
**Optional Ideas:**
- Settings Page: Make few things configurable,
    - {Alert time}
    - Stopwatch Themes
    - Dark & Light Theme
- Streak Appreciation when one topic is studied for 3 continuous days
- loading gif ?
- If loading takes time, show quotes
- Preview pebble in different modes [list or table]
- Closing tab abruptly should ask the user's confirmation ?
- Note down Prod best practices like Enable logging, metrics etc
- Deep Study Music ex: youtu.be/p_heym7pmEk
- Help in Handling Overwhelming Study phase ?
- Browser Fullscreen focused mode with collapsable -> notes, timer and music
- instead of project form > pick atleast five topics to cover [fetch them based on static DB > analytics > google web crawling ]
- Dont know what skill to learn ? > A Skill Finder that suites users personality ?
