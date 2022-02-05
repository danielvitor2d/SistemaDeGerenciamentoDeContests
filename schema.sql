drop schema manage_contests cascade;

-- Criando o esquema
create schema manage_contests;

-- Criando enum personType
create type manage_contests.personType as enum ('JUDGE', 'COACH', 'STUDENT');

-- Criando tabela Person
create table manage_contests.person (
	personId integer primary key,
	name varchar(30) unique,
	age integer,
	email varchar(30) unique,
	phone varchar(30),
	university varchar(30),
	personType manage_contests.personType
);

-- Criando tabela Team
create table manage_contests.team (
	teamId integer primary key,
	teamName varchar(30) unique,
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
	contestId integer primary key,
	title varchar(60),
	date timestamp,
	duration decimal,
	place varchar(60),
	judgeId integer,
	constraint fk_judge foreign key (judgeId) references manage_contests.person (personId)
);

-- Criando tabela Problem
create table manage_contests.problem (
	problemId integer primary key,
	title varchar(60) unique,
	description varchar(200),
	tle decimal default 1.0,
	sampleInputProblem text,
	sampleOutputProblem text,
	contestId integer,
	constraint fk_contest foreign key (contestId) references manage_contests.contest (contestId)
);

-- Criando tabela Submission
create table manage_contests.submission (
	submissionId integer unique,
	status varchar(30),
	timestamp timestamp,
	sourceCode text,
	contestId integer,
	problemId integer,
	teamId integer,
	judgeId integer,
	primary key (contestId, problemId, teamId, judgeId),
	constraint fk_contest foreign key (contestId) references manage_contests.contest (contestId),
	constraint fk_problem foreign key (problemId) references manage_contests.problem (problemId),
	constraint fk_team foreign key (teamId) references manage_contests.team (teamId),
	constraint fk_judge foreign key (judgeId) references manage_contests.person (personId)
);

-- Povoando banco de dados

-- Povoando tabela person
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (1, 'Natan', 21, 'natan@mail.com', '88997456325', 'UFC Quixadá', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (2, 'Daniel Vitor', 22, 'daniel@mail.com', '88997355182', 'UFC Quixadá', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (3, 'Alex Souza', 20, 'alex@mail.com', '88998765432', 'UFC Quixadá', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (4, 'Doug Nobrega', 24, 'doug@mail.com', '88923567354', 'UFC Quixadá', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (5, 'Wladimir Tavares', 40, 'wlad@mail.com', '8896239835279', 'UFC Quixadá', 'COACH');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (6, 'Judge global', 50, 'judge@mail.com', '82994758130', null, 'JUDGE');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (7, 'Paulo Miranda', 25, 'paulo@mail.com', '82996881425', 'UFC Quixadá', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (8, 'Claro', 25, 'claro@mail.com', '81993047842', 'UFC Quixadá', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (9, 'Bruno', 26, 'bru@mail.com', '23465424467', 'IFCE', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (10, 'Vitor', 20, 'vitor@mail.com', '54375445634', 'IFCE', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (11, 'Napoleao', 19, 'napo@mail.com', '23456378432', 'IFCE', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (12, 'Coach do IFCE', 60, 'coachifce@mail.com', '88946378625', 'IFCE', 'COACH');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (13, 'Araruna', 39, 'araruna@mail.com', '88946378761', 'UFC Quixadá', 'COACH');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (14, 'Ramon', 20, 'ramon@mail.com', '54375445634', 'UECE', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (15, 'Pedro', 19, 'pedro@mail.com', '23456378432', 'UECE', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (16, 'Wallace', 25, 'wallace@mail.com', '88964378454', 'UECE', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (17, 'João', 51, 'joaoifce@mail.com', '88946378625', 'UECE', 'COACH');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (18, 'Gabriel', 20, 'gabriel@mail.com', '54375445634', 'USP', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (19, 'Miguel', 19, 'miguel@mail.com', '23456378432', 'USP', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (20, 'Antonio', 25, 'antonio@mail.com', '88964378454', 'USP', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (21, 'Henrique', 51, 'henrique@mail.com', '88946378625', 'USP', 'COACH');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (22, 'Elias', 20, 'elias@mail.com', '54375445634', 'UNICAMP', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (23, 'Botelho', 19, 'botelho@mail.com', '23456378432', 'UNICAMP', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (24, 'Vieira', 25, 'vieira@mail.com', '88964378454', 'UNICAMP', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (25, 'Lucas', 51, 'lucas@mail.com', '88946378625', 'UNICAMP', 'COACH');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (26, 'Mateus', 23, 'mateus@mail.com', '12353624623', 'UFCG', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (27, 'Patricia', 24, 'patricia@mail.com', '56753456789', 'UFCG', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (28, 'Larissa', 25, 'larissa@mail.com', '23563678542', 'UFCG', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (29, 'Natalia', 21, 'natalia@mail.com', '98745743268', 'UFCG', 'COACH');				   
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (30, 'Mayara', 23, 'mayara@mail.com', '12353624623', 'UFPB', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (31, 'Bianca', 24, 'bianca@mail.com', '56753456789', 'UFPB', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (32, 'Maria', 25, 'maria@mail.com', '23563678542', 'UFPB', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (33, 'Ana Alice', 21, 'anaalice@mail.com', '98745743268', 'UFPB', 'COACH');
								   
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (34, 'Jaasiel', 17, 'jaasiel@mail.com', '78569876543', 'UDESC', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (35, 'Pedro Ramon', 18, 'pedroramon@mail.com', '34567895632', 'UDESC', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (36, 'Eliza', 19, 'eliza@mail.com', '41354357325', 'UDESC', 'STUDENT');
insert into manage_contests.person (personId, name, age, email,	phone, university, personType) values 
								   (37, 'Pedrão', 25, 'pedrao@mail.com', '24623574322', 'UDESC', 'COACH');

-- Povoando tabela team
insert into manage_contests.team (teamId, teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 (1, 'Ultra fast parrot', 'https://media.giphy.com/media/Ep94i7XsV92ow/giphy.gif', 4, 7, 8, 5);
insert into manage_contests.team (teamId, teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 (2, 'Arretados Inglorious', 'https://media.giphy.com/media/Q8IYWnnogTYM5T6Yo0/giphy.gif', 1, 2, 3, 13);
insert into manage_contests.team (teamId, teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 (3, 'Time de Longe', 'https://media.giphy.com/media/vA4EnqvJxDv2g/giphy.gif', 9, 10, 11, 12);
insert into manage_contests.team (teamId, teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 (4, 'Esqueci o nome', 'https://media.giphy.com/media/TFP51HPcAv2J3hQnqY/giphy.gif', 14, 15, 16, 17);
insert into manage_contests.team (teamId, teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 (5, 'Time da Final', 'https://media.giphy.com/media/yoJC2GnSClbPOkV0eA/giphy.gif', 18, 19, 20, 21);
insert into manage_contests.team (teamId, teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 (6, 'Tres caras numa moto', 'https://media.giphy.com/media/cfuL5gqFDreXxkWQ4o/giphy.gif', 22, 23, 24, 25);
insert into manage_contests.team (teamId, teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 (7, 'Codando na praia', 'https://media.giphy.com/media/sJl2lS7nlXtde/giphy.gif', 26, 27, 28, 29);
insert into manage_contests.team (teamId, teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 (8, 'Superpoderosas', 'https://media.giphy.com/media/lylKK15DfmdzO/giphy.gif', 30, 31, 32, 33);
insert into manage_contests.team (teamId, teamName, teamPhotoUrl, studentId01, studentId02, studentId03, coachId) values
								 (9, 'Time sem nome', 'https://media.giphy.com/media/94xeIhxJKItxrJMfJV/giphy.gif', 34, 35, 36, 37);