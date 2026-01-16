select u1_0.user_id,u1_0.creation_date,u1_0.encrypted_password,u1_0.last_update_date,u1_0.object_version,u1_0.role,u1_0.username
from users u1_0 where u1_0.username='Prasanth';

select * from users;

select * from project;

select * from pebble;

INSERT INTO project
(project_name, project_description, user_id, topic1, topic2, topic3, project_status,
 frequency, project_start_date, creation_date, last_update_date, object_version)
VALUES
('Learn Java', 'Learn Java coding and programming in next six months', 4,
 'Solve DSA', 'Read Core Java', 'Hands-on Projects',
 0, 6, CURRENT_DATE+180, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO project
(project_name, project_description, user_id, topic1, topic2, topic3, project_status,
 frequency, project_start_date, creation_date, last_update_date, object_version)
VALUES
('Master Spring Boot', 'Build production-ready Spring Boot applications', 4,
 'Spring Core', 'Spring Boot', 'JPA Hibernate',
 0, 6, CURRENT_DATE+120, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);
 
 
INSERT INTO project
(project_name, project_description, user_id, topic1, topic2, topic3, project_status,
 frequency, project_start_date, creation_date, last_update_date, object_version)
VALUES

('DSA Intensive', 'Strengthen problem solving with DSA', 4,
 'Arrays', 'Linked List', 'Trees',
 0, 6, CURRENT_DATE+90, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);
 
 
INSERT INTO project
(project_name, project_description, user_id, topic1, topic2, topic3, project_status,
 frequency, project_start_date, creation_date, last_update_date, object_version)
VALUES

('System Design', 'Prepare for system design interviews', 4,
 'Scalability', 'Load Balancing', 'Caching',
 0, 6, CURRENT_DATE+150, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO pebble
(project_id, user_id, pebble_notes, pebble_state, topic,
 pebble_start_time, pebble_end_time, creation_date, last_update_date, object_version)
VALUES
(5, 4, 'Studied Spring Boot basics', 'ACTIVE', 'Solve DSA',
 TIMESTAMP '2026-01-13 07:00:00', TIMESTAMP '2026-01-13 09:00:00',
 CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO pebble
(project_id, user_id, pebble_notes, pebble_state, topic,
 pebble_start_time, pebble_end_time, creation_date, last_update_date, object_version)
VALUES
(5, 4, 'Practiced React components', 'COMPLETED', 'Solve DSA',
 TIMESTAMP '2026-01-12 16:00:00', TIMESTAMP '2026-01-12 18:30:00',
 CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);
