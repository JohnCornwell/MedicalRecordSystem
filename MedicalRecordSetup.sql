DROP TABLE IF EXISTS Treats;

DROP TABLE IF EXISTS AssignedTo;

DROP TABLE IF EXISTS Scheduled;

DROP TABLE IF EXISTS Prescribed;

DROP TABLE IF EXISTS Received;

DROP TABLE IF EXISTS Appointment;

DROP TABLE IF EXISTS Treatment;

DROP TABLE IF EXISTS Patient;

DROP TABLE IF EXISTS Doctor;

CREATE TABLE Patient (
PatientID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
FirstName VARCHAR(25) NOT NULL,
MiddleName VARCHAR(25),
LastName VARCHAR(25) NOT NULL,
BirthMonth INTEGER NOT NULL,
BirthDay INTEGER NOT NULL,
BirthYear INTEGER NOT NULL,
Insurance VARCHAR(50)
);

CREATE TABLE Doctor (
DoctorID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
FirstName VARCHAR(25) NOT NULL,
MiddleName VARCHAR(25),
LastName VARCHAR(25) NOT NULL,
Specialty VARCHAR(50) NOT NULL
);

CREATE TABLE Treats (
DoctorID INTEGER NOT NULL,
PatientID INTEGER NOT NULL,
CONSTRAINT DoctorID FOREIGN KEY (DoctorID)
REFERENCES Doctor (DoctorID)
CONSTRAINT PatientId FOREIGN KEY (PatientID)
REFERENCES Patient (PatientID)
);

CREATE TABLE Appointment (
AppointmentNum INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
DoctorID INTEGER NOT NULL,
PatientID INTEGER NOT NULL,
Notes TEXT,
Month INTEGER NOT NULL, 
Day INTEGER NOT NULL,
Year INTEGER NOT NULL,
StartHour INTEGER NOT NULL,
StartMinute INTEGER NOT NULL,
EndHour INTEGER NOT NULL,
EndMinute INTEGER NOT NULL,
CONSTRAINT DoctorID FOREIGN KEY (DoctorID)
REFERENCES Doctor(DoctorID)
CONSTRAINT PatientId FOREIGN KEY (PatientID)
REFERENCES Patient (PatientID)
);

CREATE TABLE AssignedTo (
DoctorID INTEGER NOT NULL,
AppointmentNum INTEGER NOT NULL,
CONSTRAINT DoctorID FOREIGN KEY (DoctorID)
REFERENCES Doctor (DoctorID)
CONSTRAINT AppointmentNum FOREIGN KEY (AppointmentNum)
REFERENCES Appointment (AppointmentNum)
);

CREATE TABLE Scheduled (
PatientID INTEGER NOT NULL,
AppointmentNum INTEGER NOT NULL,
CONSTRAINT PatientID FOREIGN KEY (PatientID)
REFERENCES Patient (PatientID)
CONSTRAINT AppointmentNum FOREIGN KEY (AppointmentNum)
REFERENCES Appointment (AppointmentNum)
);

CREATE TABLE Treatment (
TreatmentId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
DoctorID INTEGER NOT NULL,
PatientID INTEGER NOT NULL,
Initials VARCHAR(5) NOT NULL,--allows for three initials
Notes BLOB,
Month INTEGER NOT NULL, 
Day INTEGER NOT NULL,
Year INTEGER NOT NULL,
CONSTRAINT DoctorID FOREIGN KEY (DoctorID)
REFERENCES Doctor (DoctorID)
CONSTRAINT PatientID FOREIGN KEY (PatientID)
REFERENCES Patient (PatientID)
);

CREATE TABLE Received (
PatientID INTEGER NOT NULL,
TreatmentID INTEGER NOT NULL,
CONSTRAINT PatientID FOREIGN KEY (PatientID)
REFERENCES Patient (PatientID)
CONSTRAINT TreatmentID FOREIGN KEY (TreatmentID)
REFERENCES Treatment (TreatmentID)
);

CREATE TABLE Prescribed (
DoctorID INTEGER NOT NULL,
TreatmentID INTEGER NOT NULL,
CONSTRAINT DoctorID FOREIGN KEY (DoctorID)
REFERENCES Doctor (DoctorID)
CONSTRAINT TreatmentID FOREIGN KEY (TreatmentID)
REFERENCES Treatment (TreatmentID)
);

INSERT INTO [Doctor] (FirstName, LastName, Specialty)
VALUES ('George', 'Washington', 'Abdominal Radiology');

INSERT INTO [Doctor] (FirstName, MiddleName, LastName, Specialty)
VALUES ('Alec', 'Terry', 'Delta', 'Foods');

INSERT INTO [Doctor] (FirstName, MiddleName, LastName, Specialty)
VALUES ('Harlow', 'Jim', 'Farlow', 'Toenails');

INSERT INTO [Doctor] (FirstName, MiddleName, LastName, Specialty)
VALUES ('Xander', 'Clifford', 'Isbert', 'Fungus');

INSERT INTO [Doctor] (FirstName, LastName, Specialty)
VALUES ('Karl', 'Minty', 'X-Rays');

INSERT INTO [Doctor] (FirstName, MiddleName, LastName, Specialty)
VALUES ('Poppy', 'Annie', 'Nannerson', 'Jello');

INSERT INTO [Doctor] (FirstName, MiddleName, LastName, Specialty)
VALUES ('Elizabeth', 'Tammy', 'Karlson', 'Zebras');

INSERT INTO [Doctor] (FirstName, MiddleName, LastName, Specialty)
VALUES ('Alexa', 'Jean', 'Billsborrow', 'Abdominal Radiology');

INSERT INTO [Doctor] (FirstName, MiddleName, LastName, Specialty)
VALUES ('Girdy', 'James', 'Larson', 'Oranges');

INSERT INTO [Doctor] (FirstName, MiddleName, LastName, Specialty)
VALUES ('Cooper', 'Jared', 'Alsen', 'Elbows');

INSERT INTO [Doctor] (FirstName, MiddleName, LastName, Specialty)
VALUES ('Robert', 'Grant', 'Delta', 'Pastronomy');

INSERT INTO [Doctor] (FirstName, LastName, Specialty)
VALUES ('Tersin', 'Palika', 'Rhinoplasty');

INSERT INTO [Doctor] (FirstName, LastName, Specialty)
VALUES ('Valkyrie', 'Jopernin', 'Jello');

INSERT INTO [Doctor] (FirstName, MiddleName, LastName, Specialty)
VALUES ('Sharina', 'Megan', 'Osbert', 'Nuggets');

INSERT INTO [Doctor] (FirstName, MiddleName, LastName, Specialty)
VALUES ('Dennis', 'Hugh', 'Elbert', 'Arms and Legs');

INSERT INTO [Doctor] (FirstName, MiddleName, LastName, Specialty)
VALUES ('Taric', 'Smith', 'Galucka', 'Eyes and Ears');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Ylva', 'Shaina', 'Donaldson', 6, 15, 2001, 'BBay inc.');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Ema', 'Danka', 'Ayodele', 1, 27, 1967, 'Feldco');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Slavko', 'Kassandra', 'Eldridge', 9, 20, 1944, 'State Farm');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Carran', 'Nastasia', 'Rey', 8, 17, 1945, 'Progressive');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Meical', 'Binyamin', 'Doyle', 7, 8, 1979, 'State Farm');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Lucinde', 'Somchai', 'Laurentis', 3, 11, 1955, 'Progressive');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Blandus', 'Alojzije', 'Afolabi', 7, 19, 1946, 'Progressive');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Felix', 'Eligio', 'Jung', 12, 31, 1974, 'Georgetown Insurance');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Ngawang', 'Mitul', 'Penners', 6, 8, 1952, 'American Insurance Association');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Treasach', 'Anjali', 'Tanzi', 5, 9, 1953, 'Progressive');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Ellington', 'Ryleigh', 'Gunnarsen', 2, 12, 1956, 'Progressive');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Frea', 'Damodar', 'Smith', 1, 13, 1957, 'Progressive');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Raja', 'Daniel', 'Lloyd', 5, 5, 1949, 'State Farm');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Suibhne', 'Indira', 'Halvorsen', 10, 16, 1943, 'Progressive');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Abrar', 'Zeynep', 'Pavlovski', 7, 19, 1946, 'Progressive');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Suriya', 'Nestani', 'Angus', 12, 31, 1974, 'Georgetown Insurance');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Kristoffer', 'Kali', 'Price', 6, 8, 1952, 'American Insurance Association');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Thea', 'Leandro', 'Wragge', 5, 9, 1953, 'Progressive');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Giiwedinokwe', 'Zan', 'Derricks', 11, 15, 1942, 'Progressive');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Qiu', 'Jack', 'Simmons', 1, 8, 1979, 'Farmers');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Maxim', 'Eerikki', 'Garfagnini', 6, 18, 1947, 'Grange');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Alkmene', 'Elpis', 'Matheson', 4, 10, 1954, 'State Farm');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Rosendo', 'Fionnlagh', 'Abbas', 7, 7, 1951, 'Farmers');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Anneka', 'Selena', 'Clacher', 2, 12, 1956, 'Progressive');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Publius', 'Rumen', 'Mathiasen', 1, 13, 1957, 'Progressive');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Hroekekr', 'Ashok', 'Neuman', 8, 6, 1950, 'Allstate');

INSERT INTO [Patient] (FirstName, MiddleName, LastName, BirthMonth, BirthDay, BirthYear, Insurance)
VALUES ('Dada', 'Vishal', 'Berntsen', 12, 14, 1941, 'Progressive');

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(1, 5);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(7, 3);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(8, 1);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(12, 2);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(2, 13);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(10, 4);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(8, 23);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(4, 21);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(10, 20);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(9, 18);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(2, 19);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(5, 14);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(1, 16);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(16, 8);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(7, 7);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(8, 17);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(3, 10);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(9, 26);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(2, 22);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(3, 9);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(2, 24);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(13, 15);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(7, 6);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(1, 11);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(4, 25);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(9, 12);

INSERT INTO [Treats] (DoctorID, PatientID)
VALUES(5, 27);

/*
Set up Appointment and relationships to Patient and Doctor
*/

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (8, 1, 9, 2, 2005, 15, 15, 19, 15);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (1, 8);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (1, 1);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (8, 1, 10, 3, 2005, 8, 0, 10, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (2, 8);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (2, 1);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (8, 1, 1, 13, 2006, 14, 30, 15, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (3, 8);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (3, 1);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (12, 2, 5, 17, 2002, 9, 30, 12, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (4, 12);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (4, 2);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (12, 2, 7, 23, 2007, 13, 0, 15, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (5, 12);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (5, 2);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (12, 2, 12, 9, 2010, 17, 0, 18, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (6, 12);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (6, 2);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (7, 3, 8, 2, 2001, 12, 0, 12, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (7, 7);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (7, 3);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (7, 3, 4, 4, 2003, 15, 30, 16, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (8, 7);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (8, 3);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (7, 3, 2, 28, 2007, 7, 0, 9, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (9, 7);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (9, 3);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (10, 4, 6, 30, 2005, 17, 0, 18, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (10, 10);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (10, 4);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (10, 4, 3, 7, 2006, 10, 0, 12, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (11, 10);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (11, 4);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (10, 4, 5, 9, 2006, 14, 0, 15, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (12, 10);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (12, 4);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (1, 5, 2, 20, 2008, 11, 0, 13, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (13, 1);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (13, 5);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (1, 5, 5, 14, 2008, 13, 0, 14, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (14, 1);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (14, 5);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (1, 5, 7, 4, 2009, 7, 0, 9, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (15, 1);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (15, 5);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (7, 6, 4, 2, 2000, 16, 0, 17, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (16, 7);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (16, 6);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (7, 6, 7, 30, 2002, 9, 0, 10, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (17, 7);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (17, 6);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (7, 6, 8, 2, 2002, 11, 0, 13, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (18, 7);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (18, 6);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (7, 7, 9, 1, 2009, 10, 30, 11, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (19, 7);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (19, 7);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (7, 7, 12, 14, 2009, 13, 0, 14, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (20, 7);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (20, 7);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (7, 7, 4, 25, 2010, 14, 0, 15, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (21, 7);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (21, 7);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (16, 8, 8, 2, 2004, 14, 0, 15, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (22, 16);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (22, 8);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (16, 8, 3, 17, 2006, 9, 0, 11, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (23, 16);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (23, 8);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (16, 8, 4, 18, 2006, 15, 0, 16, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (24, 16);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (24, 8);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (3, 9, 5, 22, 2003, 10, 0, 11, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (25, 3);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (25, 9);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (3, 9, 6, 30, 2004, 13, 0, 14, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (26, 3);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (26, 9);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (3, 9, 7, 28, 2004, 9, 0, 10, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (27, 3);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (27, 9);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (3, 10, 9, 1, 2009, 10, 0, 11, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (28, 3);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (28, 10);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (3, 10, 4, 13, 2010, 16, 0, 17, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (29, 3);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (29, 10);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (3, 10, 11, 2, 2014, 15, 0, 16, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (30, 3);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (30, 10);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (1, 11, 2, 4, 2002, 12, 0, 13, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (31, 1);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (31, 11);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (1, 11, 12, 31, 2002, 9, 0, 10, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (32, 1);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (32, 11);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (1, 11, 7, 11, 2005, 15, 0, 17, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (33, 1);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (33, 11);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (9, 12, 6, 1, 2003, 14, 30, 15, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (34, 9);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (34, 12);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (9, 12, 5, 13, 2004, 11, 0, 12, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (35, 9);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (35, 12);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (9, 12, 9, 17, 2004, 13, 0, 14, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (36, 9);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (36, 12);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (2, 13, 1, 12, 2008, 9, 30, 10, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (37, 2);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (37, 13);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (2, 13, 4, 23, 2008, 16, 0, 17, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (38, 2);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (38, 13);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (2, 13, 9, 16, 2009, 12, 0, 13, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (39, 2);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (39, 13);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (5, 14, 4, 18, 2006, 15, 0, 16, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (40, 5);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (40, 14);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (5, 14, 2, 7, 2007, 8, 0, 10, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (41, 5);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (41, 14);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (5, 14, 5, 9, 2010, 10, 0, 11, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (42, 5);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (42, 14);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (13, 15, 3, 18, 2008, 14, 0, 15, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (43, 13);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (43, 15);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (13, 15, 10, 2, 2009, 17, 0, 18, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (44, 13);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (44, 15);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (13, 15, 6, 12, 2009, 11, 0, 13, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (45, 13);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (45, 15);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (1, 16, 3, 2, 2002, 16, 0, 17, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (46, 1);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (46, 16);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (1, 16, 8, 14, 2002, 9, 0, 10, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (47, 1);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (47, 16);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (1, 16, 1, 27, 2004, 12, 0, 13, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (48, 1);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (48, 16);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (8, 17, 7, 19, 2004, 15, 0, 17, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (49, 8);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (49, 17);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (8, 17, 10, 14, 2004, 10, 0, 11, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (50, 8);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (50, 17);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (8, 17, 1, 30, 2005, 9, 0, 10, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (51, 8);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (51, 17);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (9, 18, 4, 5, 2007, 17, 0, 18, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (52, 9);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (52, 18);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (9, 18, 6, 17, 2009, 14, 0, 16, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (53, 9);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (53, 18);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (9, 18, 12, 28, 2009, 11, 0, 12, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (54, 9);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (54, 18);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (2, 19, 7, 12, 2003, 9, 0, 10, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (55, 2);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (55, 19);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (2, 19, 8, 24, 2004, 17, 0, 18, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (56, 2);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (56, 19);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (2, 19, 3, 4, 2007, 15, 0, 16, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (57, 2);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (57, 19);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (10, 20, 4, 1, 2002, 14, 0, 16, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (58, 10);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (58, 20);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (10, 20, 7, 30, 2005, 11, 0, 13, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (59, 10);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (59, 20);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (10, 20, 11, 4, 2006, 11, 0, 13, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (60, 10);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (60, 20);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (4, 21, 7, 19, 2003, 12, 0, 13, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (61, 4);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (61, 21);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (4, 21, 12, 8, 2004, 14, 0, 15, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (62, 4);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (62, 21);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (4, 21, 9, 27, 2005, 11, 0, 13, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (63, 4);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (63, 21);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (2, 22, 5, 24, 2006, 8, 0, 10, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (64, 2);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (64, 22);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (2, 22, 9, 17, 2006, 16, 30, 18, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (65, 2);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (65, 22);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (2, 22, 7, 18, 2007, 13, 0, 15, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (66, 2);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (66, 22);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (8, 23, 3, 10, 2006, 17, 0, 18, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (67, 8);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (67, 23);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (8, 23, 5, 12, 2006, 11, 0, 13, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (68, 8);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (68, 23);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (8, 23, 4, 2, 2007, 13, 0, 15, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (69, 8);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (69, 23);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (2, 24, 4, 11, 2007, 9, 0, 10, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (70, 2);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (70, 24);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (2, 24, 6, 18, 2008, 12, 0, 14, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (71, 2);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (71, 24);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (2, 24, 8, 24, 2010, 11, 0, 12, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (72, 2);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (72, 24);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (4, 25, 8, 2, 2001, 16, 0, 17, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (73, 4);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (73, 25);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (4, 25, 5, 16, 2002, 11, 0, 13, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (74, 4);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (74, 25);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (4, 25, 2, 18, 2003, 13, 0, 14, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (75, 4);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (75, 25);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (9, 26, 4, 5, 2002, 16, 30, 18, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (76, 9);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (76, 26);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (9, 26, 8, 14, 2004, 9, 0, 10, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (77, 9);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (77, 26);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (9, 26, 7, 12, 2005, 11, 30, 13, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (78, 9);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (78, 26);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (5, 27, 2, 8, 2004, 12, 0, 13, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (79, 5);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (79, 27);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (5, 27, 8, 20, 2005, 17, 0, 18, 0);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (80, 5);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (80, 27);

INSERT INTO [Appointment] (DoctorID, PatientID, Month, Day, Year, StartHour, StartMinute, EndHour, EndMinute)
VALUES (5, 27, 7, 24, 2005, 15, 0, 17, 30);

INSERT INTO [AssignedTo] (AppointmentNum, DoctorID)
VALUES (81, 5);

INSERT INTO [Scheduled] (AppointmentNum, PatientID)
VALUES (81, 27);

/*
Set up Treatment and relationships to Patient and Doctor
*/

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (8, 1, 5, 4, 2006, 'A.B.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (1, 8);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (1, 1);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (8, 1, 7, 3, 2006, 'A.B.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (2, 8);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (2, 1);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (12, 2, 9, 18, 2004, 'T.P.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (3, 12);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (3, 2);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (7, 3, 5, 16, 2002, 'E.K.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (4, 7);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (4, 3);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (7, 3, 11, 4, 2003, 'E.K.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (5, 7);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (5, 3);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (10, 4, 5, 16, 2002, 'C.A.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (6, 10);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (6, 4);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (10, 4, 9, 22, 2005, 'C.A.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (7, 10);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (7, 4);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (1, 5, 12, 1, 2008, 'G.W.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (8, 1);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (8, 5);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (7, 6, 7, 30, 2001, 'E.K.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (9, 7);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (9, 6);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (7, 7, 3, 12, 2010, 'E.K.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (10, 7);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (10, 7);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (7, 7, 8, 24, 2010, 'E.K.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (11, 7);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (11, 7);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (16, 8, 7, 15, 2004, 'T.G.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (12, 16);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (12, 8);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (16, 8, 5, 3, 2005, 'T.G.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (13, 16);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (13, 8);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (3, 9, 6, 12, 2003, 'H.F.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (14, 3);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (14, 9);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (3, 10, 7, 27, 2010, 'H.F.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (15, 3);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (15, 10);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (3, 10, 1, 19, 2011, 'H.F.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (16, 3);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (16, 10);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (1, 11, 5, 14, 2002, 'G.W.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (17, 1);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (17, 11);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (1, 11, 6, 28, 2003, 'G.W.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (18, 1);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (18, 11);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (9, 12, 8, 4, 2003, 'G.L.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (19, 9);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (19, 12);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (2, 13, 4, 13, 2008, 'A.D.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (20, 2);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (20, 13);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (2, 13, 7, 8, 2008, 'A.D.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (21, 2);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (21, 13);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (5, 14, 9, 28, 2006, 'K.M.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (22, 5);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (22, 14);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (13, 15, 11, 2, 2008, 'V.J.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (23, 13);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (23, 15);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (13, 15, 6, 13, 2009, 'V.J.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (24, 13);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (24, 15);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (1, 16, 7, 12, 2002, 'G.W.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (25, 1);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (25, 16);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (8, 17, 8, 2, 2004, 'A.B.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (26, 8);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (26, 17);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (8, 17, 11, 5, 2004, 'A.B.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (27, 8);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (27, 17);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (9, 18, 5, 11, 2007, 'G.L.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (28, 9);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (28, 18);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (2, 19, 7, 23, 2003, 'A.D.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (29, 2);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (29, 19);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (2, 19, 12, 4, 2003, 'A.D.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (30, 2);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (30, 19);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (10, 20, 8, 14, 2002, 'C.A.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (31, 10);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (31, 20);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (10, 20, 3, 11, 2003, 'C.A.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (32, 10);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (32, 20);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (4, 21, 9, 15, 2003, 'X.I.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (33, 4);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (33, 21);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (2, 22, 6, 27, 2006, 'A.D.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (34, 2);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (34, 22);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (2, 22, 11, 21, 2006, 'A.D.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (35, 2);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (35, 22);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (8, 23, 4, 18, 2006, 'A.B.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (36, 8);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (36, 23);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (2, 24, 8, 19, 2007, 'A.D.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (37, 2);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (37, 24);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (2, 24, 11, 5, 2007, 'A.D.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (38, 2);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (38, 24);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (4, 25, 12, 31, 2001, 'X.I.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (39, 4);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (39, 25);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (4, 25, 1, 18, 2002, 'X.I.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (40, 4);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (40, 25);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (9, 26, 7, 20, 2002, 'G.L.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (41, 9);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (41, 26);

INSERT INTO [Treatment] (DoctorID, PatientID, Month, Day, Year, Initials)
VALUES (5, 27, 3, 17, 2004, 'K.M.');

INSERT INTO [Prescribed] (TreatmentID, DoctorID)
VALUES (42, 5);

INSERT INTO [Received] (TreatmentID, PatientID)
VALUES (42, 27);