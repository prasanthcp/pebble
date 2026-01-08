Project Plan
------------
**Title:** Pebble
**Description:** Study Management Tool

**Tech Stack**
------------------
Back-End: Spring Boot 3+, Java; 
Front-End: React Javascript;
Database: PostgreSql;
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
2) Dashboard Page
   - shows the list of existing projects
   - option to add a new project
   - option to select an existing project
3) Project Page
   - list of recent pebbles in last_update_date descending
   - Add today's pebble (only if no pebble created today)
   - Select today's pebble (if exists)
4) Pebbles Page
   - List of recent pebbles
   - Select a Topic [Required] and Description [Optional]
   - User starts the stopwatch 
   - Stopwatch alerts a beep every {0.5 hour}
   - Studies for 1 hour and stops stopwatch 
     - if forgot to stop it or closed app abruptly [pause it with description]
   - Show the summary of today's pebble

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

**End Points**
---------------

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

Study Logs:
=> 6th January 2026: => 2hrs
d- Documented the project plan and basic features
d- Decide the simple tech stack to get started
d- Draft basic table schema
d- commit your first version by the EOD
- Security ?

=> 7th January 2026: => 2hrs
- Prepare basic React UI 
- Deploy existing simple project in cloud
- Components needed: 
    - Login, Logout, Authentication, 
    - ProjectDashboard, AddProject
    - PebbleDashboard, AddPebble
    - Profile