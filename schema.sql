-- Criando o esquema
create schema manage_contests;

-- Criando enum personType
create type manage_contests.personType as enum ('JUDGE', 'COACH', 'STUDENT');

-- Criando enum submissionStatus
create type manage_contests.submissionStatus as enum ('AC', 'WA', 'TLE', 'RE', 'MLE');

-- Criando enum contestStatus
create type manage_contests.contestStatus as enum ('NOT_STARTED', 'RUNNING', 'FINISHED');

-- Criando tabela Person
create table manage_contests.person (
	personId SERIAL primary key,
	name varchar(30) unique,
	age integer,
	email varchar(30) unique,
	phone varchar(30),
	university varchar(30),
	personType manage_contests.personType
);

-- Criando tabela Team
create table manage_contests.team (
	teamId SERIAL primary key,
	teamName varchar(256) unique,
	teamPhotoUrl text,
	studentId01 integer,
	studentId02 integer,
	studentId03 integer,
	coachId integer,
	constraint fk_student_01 foreign key (studentId01) references manage_contests.person (personId),
	constraint fk_student_02 foreign key (studentId02) references manage_contests.person (personId),
	constraint fk_student_03 foreign key (studentId03) references manage_contests.person (personId),
	constraint fk_coach foreign key (coachId) references manage_contests.person (personId)
);

-- Criando tabela Contest
create table manage_contests.contest (
	contestId SERIAL primary key,
	title varchar(60) unique,
	status manage_contests.contestStatus default 'NOT_STARTED',
	date timestamp,
	duration decimal default 5.0,
	place varchar(60),
	judgeId integer,
	constraint fk_judge foreign key (judgeId) references manage_contests.person (personId)
);

-- Criando tabela Problem
create table manage_contests.problem (
	problemId SERIAL primary key,
	title varchar(60) unique,
	description text,
	timelimit decimal default 1.0,
	sampleInputProblem text,
	sampleOutputProblem text,
	contestId integer,
	constraint fk_contest foreign key (contestId) references manage_contests.contest (contestId)
);

-- Criando tabela Submission
create table manage_contests.submission (
	submissionId SERIAL unique,
	status manage_contests.submissionStatus,
	timestamp timestamp,
	sourceCode text,
	problemId integer,
	teamId integer,
	primary key (problemId, teamId, timestamp),
	constraint fk_problem foreign key (problemId) references manage_contests.problem (problemId),
	constraint fk_team foreign key (teamId) references manage_contests.team (teamId)
);

-- Criando tabela contest_registered_team
create table manage_contests.contest_registered_team (
	registrationId SERIAL unique,
	teamId integer,
	contestId integer,
	primary key (teamId, contestId)
);

-- Povoando banco de dados

-- Povoando tabela person
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Natan', 21, 'natan@mail.com', '88997456325', 'UFC Quixad??', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Daniel Vitor', 22, 'daniel@mail.com', '88997355182', 'UFC Quixad??', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Alex Souza', 20, 'alex@mail.com', '88998765432', 'UFC Quixad??', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Doug Nobrega', 24, 'doug@mail.com', '88923567354', 'UFC Quixad??', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Wladimir Tavares', 40, 'wlad@mail.com', '8896239835279', 'UFC Quixad??', 'COACH');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Judge global', 50, 'judge@mail.com', '82994758130', null, 'JUDGE');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Paulo Miranda', 25, 'paulo@mail.com', '82996881425', 'UFC Quixad??', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Claro', 25, 'claro@mail.com', '81993047842', 'UFC Quixad??', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Bruno', 26, 'bru@mail.com', '23465424467', 'IFCE', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Vitor', 20, 'vitor@mail.com', '54375445634', 'IFCE', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Napoleao', 19, 'napo@mail.com', '23456378432', 'IFCE', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Coach do IFCE', 60, 'coachifce@mail.com', '88946378625', 'IFCE', 'COACH');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Araruna', 39, 'araruna@mail.com', '88946378761', 'UFC Quixad??', 'COACH');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Ramon', 20, 'ramon@mail.com', '54375445634', 'UECE', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Pedro', 19, 'pedro@mail.com', '23456378432', 'UECE', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Wallace', 25, 'wallace@mail.com', '88964378454', 'UECE', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Jo??o', 51, 'joaoifce@mail.com', '88946378625', 'UECE', 'COACH');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Gabriel', 20, 'gabriel@mail.com', '54375445634', 'USP', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Miguel', 19, 'miguel@mail.com', '23456378432', 'USP', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Antonio', 25, 'antonio@mail.com', '88964378454', 'USP', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Henrique', 51, 'henrique@mail.com', '88946378625', 'USP', 'COACH');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Elias', 20, 'elias@mail.com', '54375445634', 'UNICAMP', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Botelho', 19, 'botelho@mail.com', '23456378432', 'UNICAMP', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Vieira', 25, 'vieira@mail.com', '88964378454', 'UNICAMP', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Lucas', 51, 'lucas@mail.com', '88946378625', 'UNICAMP', 'COACH');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Mateus', 23, 'mateus@mail.com', '12353624623', 'UFCG', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Patricia', 24, 'patricia@mail.com', '56753456789', 'UFCG', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Larissa', 25, 'larissa@mail.com', '23563678542', 'UFCG', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Natalia', 21, 'natalia@mail.com', '98745743268', 'UFCG', 'COACH');				   
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Mayara', 23, 'mayara@mail.com', '12353624623', 'UFPB', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Bianca', 24, 'bianca@mail.com', '56753456789', 'UFPB', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Maria', 25, 'maria@mail.com', '23563678542', 'UFPB', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Ana Alice', 21, 'anaalice@mail.com', '98745743268', 'UFPB', 'COACH');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Jaasiel', 17, 'jaasiel@mail.com', '78569876543', 'UDESC', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Pedro Ramon', 18, 'pedroramon@mail.com', '34567895632', 'UDESC', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Eliza', 19, 'eliza@mail.com', '41354357325', 'UDESC', 'STUDENT');
insert into manage_contests.person (name, age, email,	phone, university, personType) values 
								   ('Pedr??o', 25, 'pedrao@mail.com', '24623574322', 'UDESC', 'COACH');
								   
-- Povoando tabela team
insert into manage_contests.team (teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 ('Ultra fast parrot', 'https://media.giphy.com/media/Ep94i7XsV92ow/giphy.gif', 4, 7, 8, 5);
insert into manage_contests.team (teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 ('Arretados Inglorious', 'https://media.giphy.com/media/Q8IYWnnogTYM5T6Yo0/giphy.gif', 1, 2, 3, 13);
insert into manage_contests.team (teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 ('Time de Longe', 'https://media.giphy.com/media/vA4EnqvJxDv2g/giphy.gif', 9, 10, 11, 12);
insert into manage_contests.team (teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 ('Esqueci o nome', 'https://media.giphy.com/media/TFP51HPcAv2J3hQnqY/giphy.gif', 14, 15, 16, 17);
insert into manage_contests.team (teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 ('Time da Final', 'https://media.giphy.com/media/yoJC2GnSClbPOkV0eA/giphy.gif', 18, 19, 20, 21);
insert into manage_contests.team (teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 ('Tres caras numa moto', 'https://media.giphy.com/media/cfuL5gqFDreXxkWQ4o/giphy.gif', 22, 23, 24, 25);
insert into manage_contests.team (teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 ('Codando na praia', 'https://media.giphy.com/media/sJl2lS7nlXtde/giphy.gif', 26, 27, 28, 29);
insert into manage_contests.team (teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 ('Superpoderosas', 'https://media.giphy.com/media/lylKK15DfmdzO/giphy.gif', 30, 31, 32, 33);
insert into manage_contests.team (teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 ('Time sem nome', 'https://media.giphy.com/media/94xeIhxJKItxrJMfJV/giphy.gif', 34, 35, 36, 37);
insert into manage_contests.team (teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 ('Time com T', 'https://media.giphy.com/media/94xeIhxJKItxrJMfJV/giphy.gif', 2, 6, 9, 13);
								 
-- povoando tabela contest
insert into manage_contests.contest (status, title, date, duration, place, judgeid) values 
									('NOT_STARTED', 'I Contest Brasileiro', '2018-01-31', 5, 'USP', 6);
insert into manage_contests.contest (status, title, date, duration, place, judgeid) values 
									('RUNNING', 'II Contest Brasileiro', '2019-02-01', 5, 'UFC - Quixad??', 6);
insert into manage_contests.contest (status, title, date, duration, place, judgeid) values 
									('NOT_STARTED', 'III Contest Brasileiro', '2020-01-25', 5, 'UFPB', 6);
insert into manage_contests.contest (status, title, date, duration, place, judgeid) values 
									('FINISHED', 'I Contest do Nordeste', '2017-03-01', 5, 'IFCE', 6);
insert into manage_contests.contest (status, title, date, duration, place, judgeid) values 
									('FINISHED', 'XII Contest do Norte', '2021-05-15', 5, 'UFAM', 6);
insert into manage_contests.contest (status, title, date, duration, place, judgeid) values 
									('FINISHED', 'II Contest do Nordeste', '2018-03-01', 5, 'IFCE', 6);
insert into manage_contests.contest (status, title, date, duration, place, judgeid) values 
									('FINISHED', 'III Contest do Nordeste', '2019-03-01', 5, 'IFCE', 6);
insert into manage_contests.contest (status, title, date, duration, place, judgeid) values 
									('FINISHED', 'IV Contest do Nordeste', '2020-03-01', 5, 'IFCE', 6);
insert into manage_contests.contest (status, title, date, duration, place, judgeid) values 
									('FINISHED', 'V Contest do Nordeste', '2021-03-01', 5, 'IFCE', 6);
insert into manage_contests.contest (status, title, date, duration, place, judgeid) values 
									('FINISHED', 'VI Contest do Nordeste', '2022-03-01', 5, 'IFCE', 6);
									
-- Povoando tabela problem
insert into manage_contests.problem (title, description, timelimit, sampleinputproblem, sampleoutputproblem, contestid) values 
									('Perfect Matching', 'Some big description here of Perfect Matching problem', 12, 
									 'Sample Input of Perfect Matching problem', 'Sample Output of Perfect Matching problem', 1);
insert into manage_contests.problem (title, description, timelimit, sampleinputproblem, sampleoutputproblem, contestid) values 
									('Omkar and Akmar', 'Some big description here of Omkar and Akmar problem', 3, 
									 'Sample Input of Omkar and Akmar problem', 'Sample Output of Omkar and Akmar problem', 1);
insert into manage_contests.problem (title, description, timelimit, sampleinputproblem, sampleoutputproblem, contestid) values 
									('Two chandeliers', 'Some big description here of Two chandeliers problem', 2, 
									 'Sample Input of Two chandeliers problem', 'Sample Output of Two chandeliers problem', 1);
insert into manage_contests.problem (title, description, timelimit, sampleinputproblem, sampleoutputproblem, contestid) values 
									('Engineer Artem', 'Some big description here of Engineer Artem problem', 1, 
									 'Sample Input of Engineer Artem problem', 'Sample Output of Engineer Artem problem', 1);
insert into manage_contests.problem (title, description, timelimit, sampleinputproblem, sampleoutputproblem, contestid) values 
									('Competitive Programmer', 'Some big description here of Competitive Programmer problem', 1, 
									 'Sample Input of Competitive Programmer problem', 'Sample Output of Competitive Programmer problem', 1);
insert into manage_contests.problem (title, description, timelimit, sampleinputproblem, sampleoutputproblem, contestid) values 
									('Decypher the String', 'Some big description here of Decypher the String problem', 1.5, 
									 'Sample Input of Decypher the String problem', 'Sample Output of Decypher the String problem', 2);
insert into manage_contests.problem (title, description, timelimit, sampleinputproblem, sampleoutputproblem, contestid) values 
									('Epic Convolution', 'Some big description here of Epic Convolution problem', 0.5, 
									 'Sample Input of Epic Convolution problem', 'Sample Output of Epic Convolution problem', 2);
insert into manage_contests.problem (title, description, timelimit, sampleinputproblem, sampleoutputproblem, contestid) values 
									('Nikita and Order Statistics', 'Some big description here of Nikita and Order Statistics problem', 2.5, 
									 'Sample Input of Nikita and Order Statistics problem', 'Sample Output of Nikita and Order Statistics problem', 2);
insert into manage_contests.problem (title, description, timelimit, sampleinputproblem, sampleoutputproblem, contestid) values 
									('Power Tower', 'Some big description here of Power Tower problem', 3, 
									 'Sample Input of Power Tower problem', 'Sample Output of Power Tower problem', 2);
insert into manage_contests.problem (title, description, timelimit, sampleinputproblem, sampleoutputproblem, contestid) values 
									('GCD Table', 'Some big description here of GCD Table problem', 1.25, 
									 'Sample Input of GCD Table problem', 'Sample Output of GCD Table problem', 2);
insert into manage_contests.problem (title, description, timelimit, sampleinputproblem, sampleoutputproblem, contestid) values 
									('Make Them Equal', 'Some big description here of Make Them Equal problem', 1.75, 
									 'Sample Input of Make Them Equal problem', 'Sample Output of Make Them Equal problem', 3);
insert into manage_contests.problem (title, description, timelimit, sampleinputproblem, sampleoutputproblem, contestid) values 
									('Flipping Range', 'Some big description here of Flipping Range problem', 1.5, 
									 'Sample Input of Flipping Range problem', 'Sample Output of Flipping Range problem', 3);
insert into manage_contests.problem (title, description, timelimit, sampleinputproblem, sampleoutputproblem, contestid) values 
									('Not Adding', 'Some big description here of Not Adding problem', 0.5, 
									 'Sample Input of Not Adding problem', 'Sample Output of Not Adding problem', 4);
insert into manage_contests.problem (title, description, timelimit, sampleinputproblem, sampleoutputproblem, contestid) values 
									('New School', 'Some big description here of New School problem', 2.5, 
									 'Sample Input of New School problem', 'Sample Output of New School problem', 4);
insert into manage_contests.problem (title, description, timelimit, sampleinputproblem, sampleoutputproblem, contestid) values 
									('Messages', 'Some big description here of Messages problem', 3, 
									 'Sample Input of Messages problem', 'Sample Output of Messages problem', 5);
insert into manage_contests.problem (title, description, timelimit, sampleinputproblem, sampleoutputproblem, contestid) values 
									('Arena', 'Some big description here of Arena problem', 3, 
									 'Sample Input of Arena problem', 'Sample Output of Arena problem', 5);
									 
-- Povoando a tabela contest_registered_team
INSERT INTO manage_contests.contest_registered_team (teamid, contestid) VALUES 
													(1, 2);
INSERT INTO manage_contests.contest_registered_team (teamid, contestid) VALUES 
													(1, 4);
INSERT INTO manage_contests.contest_registered_team (teamid, contestid) VALUES 
													(1, 5);
INSERT INTO manage_contests.contest_registered_team (teamid, contestid) VALUES 
													(2, 1);
INSERT INTO manage_contests.contest_registered_team (teamid, contestid) VALUES 
													(2, 5);
INSERT INTO manage_contests.contest_registered_team (teamid, contestid) VALUES 
													(3, 2);
INSERT INTO manage_contests.contest_registered_team (teamid, contestid) VALUES 
													(3, 3);
INSERT INTO manage_contests.contest_registered_team (teamid, contestid) VALUES 
													(4, 1);
INSERT INTO manage_contests.contest_registered_team (teamid, contestid) VALUES 
													(4, 2);
INSERT INTO manage_contests.contest_registered_team (teamid, contestid) VALUES 
													(4, 3);
INSERT INTO manage_contests.contest_registered_team (teamid, contestid) VALUES 
													(4, 4);
INSERT INTO manage_contests.contest_registered_team (teamid, contestid) VALUES 
													(5, 3);
INSERT INTO manage_contests.contest_registered_team (teamid, contestid) VALUES 
													(5, 4);
INSERT INTO manage_contests.contest_registered_team (teamid, contestid) VALUES 
													(5, 5);
													
-- Povoando a tabela submission
insert into manage_contests.submission (status, "timestamp", sourcecode, problemid, teamid) values
									   ('AC', 'now()', 'Sample 1 of sourcecode', 1, 1);
insert into manage_contests.submission (status, "timestamp", sourcecode, problemid, teamid) values
									   ('WA', 'now()', 'Sample 2 of sourcecode', 2, 1);
insert into manage_contests.submission (status, "timestamp", sourcecode, problemid, teamid) values
									   ('AC', 'now()', 'Sample 3 of sourcecode', 3, 1);
insert into manage_contests.submission (status, "timestamp", sourcecode, problemid, teamid) values
									   ('TLE', 'now()', 'Sample 4 of sourcecode', 4, 1);
insert into manage_contests.submission (status, "timestamp", sourcecode, problemid, teamid) values
									   ('AC', 'now()', 'Sample 5 of sourcecode', 15, 2);
insert into manage_contests.submission (status, "timestamp", sourcecode, problemid, teamid) values
									   ('WA', 'now()', 'Sample 6 of sourcecode', 12, 2);
insert into manage_contests.submission (status, "timestamp", sourcecode, problemid, teamid) values
									   ('AC', 'now()', 'Sample 7 of sourcecode', 16, 2);
insert into manage_contests.submission (status, "timestamp", sourcecode, problemid, teamid) values
									   ('TLE', 'now()', 'Sample 8 of sourcecode', 6, 2);
insert into manage_contests.submission (status, "timestamp", sourcecode, problemid, teamid) values
									   ('AC', 'now()', 'Sample 9 of sourcecode', 10, 2);
insert into manage_contests.submission (status, "timestamp", sourcecode, problemid, teamid) values
									   ('AC', 'now()', 'Sample 10 of sourcecode', 3, 2);
insert into manage_contests.submission (status, "timestamp", sourcecode, problemid, teamid) values
									   ('AC', 'now()', 'Sample 11 of sourcecode', 5, 2);
insert into manage_contests.submission (status, "timestamp", sourcecode, problemid, teamid) values
									   ('AC', 'now()', 'Sample 12 of sourcecode', 9, 2);
insert into manage_contests.submission (status, "timestamp", sourcecode, problemid, teamid) values
									   ('WA', 'now()', 'Sample 13 of sourcecode', 15, 5);
insert into manage_contests.submission (status, "timestamp", sourcecode, problemid, teamid) values
									   ('WA', 'now()', 'Sample 14 of sourcecode', 14, 5);
insert into manage_contests.submission (status, "timestamp", sourcecode, problemid, teamid) values
									   ('RE', 'now()', 'Sample 15 of sourcecode', 16, 5);
									   
-- Criando views e materialized views

-- Criando view para visualizar os membros de cada time
create view manage_contests.view_teams as (
	select (select p.name from manage_contests.person as p where p.personId = t.studentid01) as student01,
	       (select p.name from manage_contests.person as p where p.personId = t.studentid02) as student02,
	 	     (select p.name from manage_contests.person as p where p.personId = t.studentid03) as student03,
		     (select p.name from manage_contests.person as p where p.personId = t.coachId) as coach
	from manage_contests.team as t
);

-- Criando view para visualizar o time e a quantidade de contests cadastrados
create view manage_contests.view_contests_teams as (
	select t.teamId as team_id,
		   (select p.name from manage_contests.person as p where p.personId = t.studentid01) as student01,
	       (select p.name from manage_contests.person as p where p.personId = t.studentid02) as student02,
	 	   (select p.name from manage_contests.person as p where p.personId = t.studentid03) as student03,
		   (select p.name from manage_contests.person as p where p.personId = t.coachId) as coach,
	       count(crt.teamId) as count_of_contests
	from manage_contests.contest_registered_team as crt right join manage_contests.team as t on t.teamId = crt.teamId
	group by (t.teamId)
	order by (t.teamId)
);

-- Criando materialized view para calcular a quantidade de submiss??es por time, mostrando membros do time
create materialized view manage_contests.view_teams_submissions as (
	select t.teamId as team_id,
		   (select p.name from manage_contests.person as p where p.personId = t.studentid01) as student01,
	       (select p.name from manage_contests.person as p where p.personId = t.studentid02) as student02,
	 	   (select p.name from manage_contests.person as p where p.personId = t.studentid03) as student03,
		   (select p.name from manage_contests.person as p where p.personId = t.coachId) as coach,
		   count(s.submissionId) as count_of_submissionsinsert
	from manage_contests.submission as s natural right join manage_contests.team as t
	group by (team_id, student01, student02, student03, coach)
);

-- Criando materialized view para calcular a quantidade de problemas por contest
create materialized view manage_contests.view_problems_of_contest as (
	select c.contestId, c.title as title_of_contest, count(p.problemId) as count_of_problems
	from manage_contests.contest as c left join manage_contests.problem as p on c.contestId = p.contestId
	group by (c.contestId, c.title)
);

-- Criando materialized view para calcular a quantidade de submiss??es por time por contest
create materialized view manage_contests.view_count_accepted_of_team_by_contest as (
  select t3.teamId as team_id, t3.contestId as contest_id, count(t3.submissionId) as count_of_accepted
  from                 (((manage_contests.problem as p 
		natural left join manage_contests.submission as s) as t1
		natural right join manage_contests.team as t) as t2
	    join manage_contests.contest as c using (contestId)) as t3
  group by (t3.teamId, t3.contestId)
);

-- Criando materialized view para calcular a quantidade de submiss??es AC por times
create materialized view manage_contests.view_count_accepted_of_team as (
  select res.teamId as team_id, count(res.submissionId) as count_of_accepted
  from (manage_contests.submission as s natural right join manage_contests.team as t) as res
  where res.status = 'AC' or res.status is null
  group by (res.teamId)
);
create unique index view_count_accepted_of_team_id on manage_contests.view_count_accepted_of_team (team_id);

-- Criando consultas

-- Consultando times e o somat??rio da quantidade de problemas de todos os contests que participaram
select team_contest_count_problems.teamName, sum(team_contest_count_problems.count_of_problems)
from ((manage_contests.contest_registered_team as crt natural join manage_contests.team as t) as team_contest
     natural join manage_contests.view_problems_of_contest as vpc) as team_contest_count_problems
group by (team_contest_count_problems.teamName);

-- Consultar quantidade de submiss??es por time
select team_id, sum(count_of_accepted)
from manage_contests.view_count_accepted_of_team_by_contest
group by team_id;

-- Quantidade de submiss??es AC por time
select * from manage_contests.view_count_accepted_of_team;

-- Nome do time e quantidade de submiss??es em todos os contests
select res.teamName, count(res.submissionId)
from (manage_contests.team as t natural left join manage_contests.submission as s) as res
group by (res.teamName);

-- Contar quantidade de contests RUNNING
select count(*)
from manage_contests.contest as c
where c.status = 'RUNNING';

-- Criando procedimento armazenado
CREATE OR REPLACE procedure manage_contests.inserir_time_no_contest(teamId int, contestId int)
language plpgsql
as $$
declare
begin
	insert into manage_contests.contest_registered_team (teamId, contestId) values (teamId, contestId);
end; 
$$;

-- Criando trigger para atualizar view de contar a quantidade de submiss??es com status EC por time
CREATE OR REPLACE FUNCTION manage_contests.update_view_count_accepted_of_team()
  RETURNS TRIGGER LANGUAGE plpgsql
  AS $$
  BEGIN
  REFRESH MATERIALIZED VIEW CONCURRENTLY manage_contests.view_count_accepted_of_team;
  RETURN NEW;
END 
$$;

create or replace trigger update_view_count_accepted_of_team1
after insert on manage_contests.submission
for each row
execute function manage_contests.update_view_count_accepted_of_team();

CREATE OR REPLACE TRIGGER update_view_count_accepted_of_team2
AFTER UPDATE ON manage_contests.submission
FOR EACH ROW
EXECUTE FUNCTION manage_contests.update_view_count_accepted_of_team();

CREATE OR REPLACE TRIGGER update_view_count_accepted_of_team3
AFTER DELETE ON manage_contests.submission
FOR EACH ROW
EXECUTE FUNCTION manage_contests.update_view_count_accepted_of_team();