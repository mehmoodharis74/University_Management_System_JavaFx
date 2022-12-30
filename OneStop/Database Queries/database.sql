create database onestop;
use onestop;

create Table Student(
ID numeric(5),
SName varchar(20),
SAddress varchar(50),
SType varchar (5),
Passwords varchar(30),
Semester_Fee varchar(3),
Degree_Fee varchar(3),
CGPA varchar(5),
Phone varchar(20),
Degree varchar(20),
Email varchar (40),
primary key (ID)
);

create Table FYP(
ID numeric(5) primary key,
Passwords varchar(30),
FYP_Name varchar(30),
FYP_Phone varchar(20)
);

create Table Finance(
ID numeric(5) primary key,
Passwords varchar(30),
F_Name varchar(30),
F_Phone varchar(20)
);

create Table Admin(
ID numeric(5) primary key,
Passwords varchar(30),
A_Name varchar(30),
A_Phone varchar(20)
);

create Table Director(
ID numeric(5) primary key,
Passwords varchar(30),
D_Name varchar(30),
D_Phone varchar(20)
);

create Table Request(
Token int not null auto_increment,
StudentID varchar(20),
AdminID numeric(5),
Track  varchar(20),
S_Time Datetime,
E_Time Datetime,
RStatus varchar(30),
Finance_ID numeric(5),
Decision_Finance varchar(20),
Comment_Finance varchar(200),
Admin_StartTime Datetime,
Admin_EndTime Datetime,
F_StartTime Datetime,
F_EndtTime Datetime,
Admin_signature varchar(20),
HOD_signature varchar(20),
FYP_ID numeric(5),
Decision_FYP varchar(20),
Comment_FYP varchar(200),
FYP_StartTime Datetime,
FYP_Endtime Datetime,
degree_issued varchar(20),
Student_Name varchar(20),
Father_Name varchar(30),
SAddress varchar(50),
Degree varchar(20),
Email varchar (40),
CGPA varchar(5),
campus varchar(20),
primary key (Token),
FOREIGN KEY (FYP_ID) REFERENCES FYP(ID) ON DELETE CASCADE,
FOREIGN KEY (Finance_ID) REFERENCES Finance(ID) ON DELETE CASCADE,
FOREIGN KEY (AdminID) REFERENCES Admin(ID) ON DELETE CASCADE
);

 create table degree(
 DID int not null auto_increment,
 degree_name varchar(20),
 studentID numeric(5) unique,
 adminID numeric(5),
 Issued_date date,
 locationName varchar(20),
 primary key (did),
 FOREIGN KEY (studentID) REFERENCES Student(ID) ON DELETE CASCADE,
 FOREIGN KEY (adminID) REFERENCES Admin(ID) ON DELETE CASCADE);

create table transcript(
TID int not null auto_increment,
studentID numeric(5),
adminID numeric(5),
granted varchar(3),
requested varchar(3),
primary key(tid),
FOREIGN KEY (studentID) REFERENCES Student(ID) ON DELETE CASCADE,
FOREIGN KEY (adminID) REFERENCES Admin(ID) ON DELETE CASCADE
);

create table transcript_semester(
sem_number numeric(5),
transcriptID int,
subjects varchar(40),
grade varchar(20),
points varchar(5),
credits varchar(5),
s_time datetime,
e_time datetime,
gpa varchar(20),
FOREIGN KEY (transcriptID) REFERENCES transcript(TID) on delete cascade,
primary key (sem_number, transcriptID, subjects)
);




INSERT INTO student( ID, SName, SAddress, SType, Passwords, Semester_Fee, Degree_Fee, CGPA, Phone, Degree, Email)
VALUES (12345, 'Haris Mehmood', 'PTCL Officers Block', 'BS', '123@1', 'Yes', 'Yes', '3.50', '01234567891', 'Computer Science', 'HarisMehmood@informiza.com');

INSERT INTO student( ID, SName, SAddress, SType, Passwords, Semester_Fee, Degree_Fee, CGPA, Phone, Degree, Email)
VALUES (12346, 'Ali Raza', 'F11 Main Block', 'BS', '123@2', 'Yes', 'Yes', '2.50', '01234564591', 'Electrical', 'AliRaza4@gmail.com');

INSERT INTO student( ID, SName, SAddress, SType, Passwords, Semester_Fee, Degree_Fee, CGPA, Phone, Degree, Email)
VALUES (12347, 'Huzaifa Bilal', 'Askari14', 'BS', '123@3', 'Yes', 'Yes', '3.20', '03365218959', 'Computer Science', 'huzaifabilal2202@gmail.com');

INSERT INTO student( ID, SName, SAddress, SType, Passwords, Semester_Fee, Degree_Fee, CGPA, Phone, Degree, Email)
VAlUES (12348, 'Muhammad Usman', 'DHA Phsae 1', 'BS', '123@4', 'Yes', 'Yes', '2.70', '03315245959', 'Data Science', 'usman22@gmail.com');

INSERT INTO student( ID, SName, SAddress, SType, Passwords, Semester_Fee, Degree_Fee, CGPA, Phone, Degree, Email)
VALUES (12349, 'Muhammad Ali', 'DHA Phsae 2', 'BS', '123@5', 'Yes', 'Yes', '3.70', '03315245959', 'Computer Science', 'MuhammadALi@gmailc.com');


INSERT INTO FYP(ID, Passwords, FYP_Name, FYP_Phone)
VALUES(22345, '123@1', 'Computer Science', '01234567895');

insert into FYP (ID, Passwords, FYP_Name, FYP_Phone)
values(22346, '123@2', 'Data Science', '01234567896');

insert into FYP (ID, Passwords, FYP_Name, FYP_Phone)
values(22347, '123@3', 'Cyber Security', '01234567897');


insert into Finance (ID, Passwords, F_Name, F_Phone)
values(32345, '123@1', 'Ahmed Iqbal', '01234567895');

insert into Finance (ID, Passwords, F_Name, F_Phone)
values(32346, '123@2', 'Ashar Raza', '01234567896');

insert into Finance (ID, Passwords, F_Name, F_Phone)
values(32347, '123@3', 'Ahmed Sakhi', '01234567897');


insert into Admin (ID, Passwords, A_Name, A_Phone)
values(42345, '123@1', 'Ahmed Raza', '01234567895');

insert into Admin (ID, Passwords, A_Name, A_Phone)
values(42346, '123@2', 'Ashar Khaliq', '01234567896');

insert into Admin (ID, Passwords, A_Name, A_Phone)
values(42347, '123@3', 'Ahmed Bukhari', '01234567897');


insert into Director (ID, Passwords, D_Name, D_Phone)
values(52345, '123@1', 'Raja Ali', '01234567815');



insert into Transcript (TID, studentID, adminID, granted, requested)
values(5, 12345, null,'NO', 'NO');

insert into Transcript (TID, studentID, adminID, granted, requested)
values(6, 12346, null,'NO', 'NO');

insert into Transcript (TID, studentID, adminID, granted, requested)
values(7, 12347, null,'NO', 'NO');

insert into Transcript (TID, studentID, adminID, granted, requested)
values(8, 12348, null,'NO', 'NO');

insert into Transcript (TID, studentID, adminID, granted, requested)
values(12, 12348, null,'NO', 'YES');




insert into transcript_semester (sem_number, transcriptID, subjects, grade, points, credits, s_time, e_time, gpa) 
values(1, 5, 'Programming Fundamentals', 'A', '4', '3', '2022-09-01', '2023-02-01', '3.30');

insert into transcript_semester (sem_number, transcriptID, subjects, grade, points, credits, s_time, e_time, gpa)
values(1, 5, 'English', 'A-', '3.67', '2', '2022-09-01', '2023-02-01', '3.30');

insert into transcript_semester (sem_number, transcriptID, subjects, grade, points, credits, s_time, e_time, gpa)
values(1, 5, 'Calculus', 'B', '3', '3', '2022-09-01', '2023-02-01', '3.30');

insert into transcript_semester (sem_number, transcriptID, subjects, grade, points, credits, s_time, e_time, gpa)
values(1, 5, 'Physics', 'B-', '2.67', '3', '2022-09-01', '2023-02-01', '3.30');


insert into transcript_semester (sem_number, transcriptID, subjects, grade, points, credits, s_time, e_time, gpa)
values(2, 5, 'OOP', 'A', '4', '3', '2022-09-01', '2023-02-01', '3.30');

insert into transcript_semester (sem_number, transcriptID, subjects, grade, points, credits, s_time, e_time, gpa)
values(2, 5, 'CPS', 'A-', '3.67', '2', '2022-09-01', '2023-02-01', '3.30');

insert into transcript_semester (sem_number, transcriptID, subjects, grade, points, credits, s_time, e_time, gpa)
values(2, 5, 'Linear Algeba', 'B', '3', '3', '2022-09-01', '2023-02-01', '3.30');

insert into transcript_semester (sem_number, transcriptID, subjects, grade, points, credits, s_time, e_time, gpa)
values(2, 5, 'Discrete', 'B-', '2.67', '3', '2022-09-01', '2023-02-01', '3.30');


insert into transcript_semester (sem_number, transcriptID, subjects, grade, points, credits, s_time, e_time, gpa)
values(3, 5, 'Pak Study', 'A', '4', '3', '2022-09-01', '2023-02-01', '3.30');

insert into transcript_semester (sem_number, transcriptID, subjects, grade, points, credits, s_time, e_time, gpa)
values(3, 5, 'Data Structures', 'A-', '3.67', '2', '2022-09-01', '2023-02-01', '3.30');

insert into transcript_semester (sem_number, transcriptID, subjects, grade, points, credits, s_time, e_time, gpa)
values(3, 5, 'Coal', 'B', '3', '3', '2022-09-01', '2023-02-01', '3.30');

insert into transcript_semester (sem_number, transcriptID, subjects, grade, points, credits, s_time, e_time, gpa)
values(3, 5, 'Islamiyat', 'B-', '2.67', '3', '2022-09-01', '2023-02-01', '3.30');


insert into transcript_semester (sem_number, transcriptID, subjects, grade, points, credits, s_time, e_time, gpa)
values(4, 5, 'Probabiltiy', 'A', '4', '3', '2022-09-01', '2023-02-01', '3.30');

insert into transcript_semester (sem_number, transcriptID, subjects, grade, points, credits, s_time, e_time, gpa)
values(4, 5, 'FOM', 'A-', '3.67', '2', '2022-09-01', '2023-02-01', '3.30');

insert into transcript_semester (sem_number, transcriptID, subjects, grade, points, credits, s_time, e_time, gpa)
values(4, 5, 'Database', 'B', '3', '3', '2022-09-01', '2023-02-01', '3.30');

insert into transcript_semester (sem_number, transcriptID, subjects, grade, points, credits, s_time, e_time, gpa)
values(4, 5, 'OS', 'B-', '2.67', '3', '2022-09-01', '2023-02-01', '3.30');

