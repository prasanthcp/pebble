Project Plan
------------
**Title:** Pebble
**Description:** Skill Development Tool

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
1) First show a static welcome page > Login in option
2) Project's Dashboard Page
   - Shows the list of selectable existing projects/skills user wants to build (if any)
   - Projects Table: 
     - Title, Started Date, Target Completion [Months & Hours left], Topics, Frequency, Studied, 
     - Actions: Edit Projects, Your Pebbles 
   - (+) Add Project Option
3) Project Page
    - Manage your Project {project_code} -> Add/Edit Form
     - Pebbles Dashboard:
       - Pebbles Table: Project Title, PebbleId, Topic, Description, StartTime, EndTime, Studied
       - (+) Add Pebble option
4) Pebble Page
   - Manage your Pebble for {project_code} -> Add/Edit Form > Save
- Better option: 
  - User enters target study time > saves pebble > it shows readonly details & a stopwatch to track study slot 
  - Stopwatch alerts a beep every {0.5 hour}
  - Studies for 1 hour and stops stopwatch
  - if forgot to stop it or closed app abruptly [pause it with description]
  - Update the status of pebble and studied time

**Must Do Changes:**
-------------------
d- Welcome Page Static Content
d- Improve CSS a bit
d- Reduce 5 to 3 topics
d- Add Status, Frequency, Hours Studied, field to Project table
  d- update status and start date when a projects first pebble comes up
d- Target Completion Computation Method,
  d- start date set when a pebble started
  d- default start date is creation date
  d- Hours studied is 0 for inactive projects
d- Topics List instead of each Topic in ProjectsDashboard Page
d- Reduce Boilerplate code [using Lombok]
d- Add StartTime, EndTime, Topic in Pebble Object
d- Add AOP Logs for all Endpoints
- Pebble Table list Columns+
  d- sort id desc
  - StudiedHours Computation
d- Understand and use Date Time APIs in Java, JS
d- Configure JWT Token
  1) Add jwt dependencies [jjwt-api, jjwt-impl, jjwt-jackson] in pom
  2) Generate, validate JWT Token
  3) Filter 
  4) Note: Permit auth call without spring security 
- Oracle Cloud
  - Create Oracle Free tier > Create Autonomous DB > Download Wallet > in SQLDeveloper using CloudWallet
    - Wallet info: Connections to your Autonomous AI Database are secured, and can be authorized using TLS or mTLS authentication options. TLS authentication is easier to use, provides better connection latency, and does not require you to download client credentials (wallet) if any of these is true for your connections:
        You are using JDBC Thin Client (version 12.2.0.1 or higher) with JDK 8(u163+) or higher.
        You are using the Python python-oracledb driver.
        You are using ODP.NET version 19.14 (or higher), or 21.5 (or higher).
        You are using an Oracle Call Interface based driver with Oracle Client libraries version 19.14 (or higher), or 21.5 (or higher).
    - Oracle Autonomous DB (Free) is always provisioned and reachable
  - Add JDBC Dependency, Properties [add ${DB_PASSWORD} OS property; inject OCI env var at deployment ]
  - Connect to Oracle SQL [ADMIN/DB_PASSWORD]
  - Run the Application [In prod add program arg --spring.profiles.active=prod]
  - Add Junit tests 
  - Enable Actuator > Logs
  - Deploy your application [CI/CD ?] 
- login, logout emoji
- Pebble crow Logo
  
d- Use code instead of passing id in URL : project_code
- Study Plan Browser Reminder Notifications or Calender Reminders

**Database Schema**
-------------------
Table 1: Project
Columns: id (primary key), userid (foreign key), title, description, topic1, topic2, 
topic3, target_date, creation_date, last_update_date, object_version

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
- Improvise Welcome Page to include - Science of learning Anything; 
- User Table field: Free Hours Available in a day
- Pebble Page: Notes + Timer
- idea Lab > Movable Ideas
- Change Form Fill to ChatBot
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
- Don't know what skill to learn ? > A Skill Finder that suites users personality ?

Understanding Date Time APIs:
- @CreatedDate @LastModifiedDate LocalDateTime dayjs dayjs(<date>).format("DD/MM/YYYY HH:mm")



Debug Notes:


2026-01-14T10:28:53.321+05:30  WARN 11185 --- [Pebble] [nio-5001-exec-8] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize value of type `java.time.LocalDateTime` from String "2026-01-14": Failed to deserialize `java.time.LocalDateTime` (with format 'ParseCaseSensitive(false)(Value(Year,4,10,EXCEEDS_PAD)'-'Value(MonthOfYear,2)'-'Value(DayOfMonth,2))'T'(Value(HourOfDay,2)':'Value(MinuteOfHour,2)[':'Value(SecondOfMinute,2)[Fraction(NanoOfSecond,0,9,DecimalPoint)]])'): (java.time.format.DateTimeParseException) Text '2026-01-14' could not be parsed at index 10]
