INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY)
VALUES
('Worker1', '1980-01-01', 'Trainee', 800),
('Worker2', '1985-02-02', 'Junior', 1200),
('Worker3', '1990-03-03', 'Middle', 3000),
('Worker4', '1995-04-04', 'Senior', 6000),
('Worker5', '1980-01-01', 'Trainee', 800),
('Worker6', '1985-02-02', 'Junior', 1200),
('Worker7', '1990-03-03', 'Middle', 3000),
('Worker8', '1995-04-04', 'Trainee', 6000),
('Worker9', '1995-04-04', 'Senior', 6000),
('Worker10', '1995-04-04', 'Junior', 6000);

INSERT INTO client (NAME)
VALUES
('Client1'),
('Client2'),
('Client3'),
('Client4'),
('Client5'),
('Client6'),
('Client7'),
('Client8'),
('Client9'),
('Client10');

INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE)
VALUES
(1, '2022-01-01', '2023-01-01'),
(2, '2023-02-02', '2024-02-02'),
(3, '2024-02-02', '2025-02-02'),
(4, '2025-02-02', '2027-02-02'),
(5, '2026-02-02', '2028-02-02'),
(6, '2027-02-02', '2029-02-02'),
(7, '2028-02-02', '2030-02-02'),
(8, '2029-02-02', '2031-02-02'),
(9, '2030-02-02', '2032-02-02'),
(10, '2031-02-02', '2033-02-02');