INSERT INTO users (first_name, last_name, birthdate, email) VALUES
('Emma', 'Johnson', '1996-01-15', 'Emma.Johnson@domain.com'),
('Ethan', 'Taylor', '1973-03-22', 'Ethan.Taylor@domain.com'),
('Olivia', 'Davis', '1948-05-10', 'Olivia.Davis@domain.com'),
('Daniel', 'Thomas', '1990-07-08', 'Daniel.Thomas@domain.com'),
('Sophia', 'Baker', '1975-09-14', 'Sophia.Baker@domain.com'),
('Liam', 'Jones', '2001-11-20', 'Liam.Jones@domain.com'),
('Ava', 'Williams', '2003-02-28', 'Ava.Williams@domain.com'),
('Noah', 'Miller', '1985-04-05', 'Noah.Miller@domain.com'),
('Grace', 'Anderson', '1991-06-18', 'Grace.Anderson@domain.com'),
('Oliver', 'Swift', '1995-08-25', 'Oliver.Swift@domain.com');


INSERT INTO health_metrics (user_id, date, weight, height, heart_rate) VALUES
(1, '2024-01-15', 65.5, 170.0, 72),
(1, '2024-01-20', 65.2, 170.0, 70),
(2, '2024-01-15', 82.3, 180.0, 68),
(2, '2024-01-20', 81.9, 180.0, 67),
(3, '2024-01-15', 58.7, 160.0, 75),
(4, '2024-01-15', 75.0, 175.0, 70),
(4, '2024-01-20', 74.8, 175.0, 69),
(5, '2024-01-15', 68.2, 165.0, 73),
(6, '2024-01-15', 70.5, 178.0, 71),
(7, '2024-01-15', 55.8, 162.0, 74),
(8, '2024-01-15', 80.0, 182.0, 69),
(9, '2024-01-15', 63.5, 168.0, 72),
(10, '2024-01-15', 72.3, 176.0, 70);

INSERT INTO statistics (user_id, total_trainings, total_distance, total_calories_burned) VALUES
(1, 15, 157.5, 3150),
(2, 12, 300.0, 6000),
(3, 8, 41.6, 832),
(4, 20, 246.0, 4920),
(5, 18, 336.6, 6732),
(6, 5, 17.5, 350),
(7, 25, 375.0, 7500),
(8, 14, 315.0, 6300),
(9, 10, 42.0, 840),
(10, 16, 188.8, 3776);