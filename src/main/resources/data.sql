INSERT INTO users (username, encrypted_password, object_version, creation_date, last_update_date)
VALUES ('Prasanth', 'Welcome1', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO project
(project_name, project_description, user_id, topic1, topic2, topic3, project_status,
 frequency, project_start_date, creation_date, last_update_date, object_version)
VALUES
('Learn Java', 'Learn Java coding and programming in next six months', 1,
 'Solve DSA', 'Read Core Java', 'Hands-on Projects',
 false, 6, DATEADD('DAY', 180, CURRENT_DATE), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),

('Master Spring Boot', 'Build production-ready Spring Boot applications', 1,
 'Spring Core', 'Spring Boot', 'JPA & Hibernate',
 false, 6, DATEADD('DAY', 120, CURRENT_DATE), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),

('DSA Intensive', 'Strengthen problem solving with DSA', 1,
 'Arrays', 'Linked List', 'Trees',
 false, 6, DATEADD('DAY', 90, CURRENT_DATE), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),

('System Design', 'Prepare for system design interviews', 1,
 'Scalability', 'Load Balancing', 'Caching',
 false, 6, DATEADD('DAY', 150, CURRENT_DATE), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);

INSERT INTO pebble
(project_id, user_id, pebble_notes, pebble_state, topic,
 pebble_start_time, pebble_end_time, creation_date, last_update_date, object_version)
VALUES
(1, 1, 'Studied Spring Boot basics', 'ACTIVE', 'Solve DSA',
 TIMESTAMP '2026-01-13 07:00:00', TIMESTAMP '2026-01-13 09:00:00',
 CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),

(1, 1, 'Practiced React components', 'COMPLETED', 'Solve DSA',
 TIMESTAMP '2026-01-12 16:00:00', TIMESTAMP '2026-01-12 18:30:00',
 CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);
