--- Create our Main Database first
CREATE DATABASE Project;

--- Use it to create the following tables
USE Project;

--- Users table
CREATE TABLE ClinicUSERS(
	ID		INT		NOT NULL,
	USERNAME	CHAR(17) 	NOT NULL,
	FIRST_NAME	VARCHAR(25)	NOT NULL,
	LAST_NAME	VARCHAR(25)	NOT NULL,
	PASSWORD	VARCHAR(25)	NOT NULL,
	EMAIL 		VARCHAR(20) 	NOT NULL,
	--- Date of birth
	DOB 		DATE		NOT NULL,
	AGE		INT		CHECK (AGE >=0 AND AGE <= 105) NOT NULL,
	TELEPHONE	CHAR(14)	NULL,
	--- Alternate Phone
	ALTPHONE	VARCHAR(12)	NULL,
	ADDRESS		VARCHAR(25)	NULL,
	BLOOD_TYPE	VARCHAR(2)	NULL,
	USERTYPE	INT		NOT NULL,
	GENDER		BIT		NOT NULL,
	PRIMARY KEY(ID),
	CONSTRAINT UC_userName UNIQUE(USERNAME)
);

--- Table workers 
CREATE TABLE WORKERS (
	--- Their ID, from the USERs ID 
	WORKERID  INT,
	SALARY 	  NUMERIC NOT NULL,
	WORK_TIME VARCHAR(20) NOT NULL,
	NOTES 	  VARCHAR(20) NULL,
	CONSTRAINT WorkerId PRIMARY KEY(WORKERID),
	CONSTRAINT FK_WorkerID FOREIGN KEY (WORKERID) REFERENCES ClinicUSERS(ID)
);

--- Patient Data
CREATE TABLE PATIENTS (
	--- Their ID
	PATIENTID INT, 	NOTES VARCHAR(20),
	--- In case they have diabites 
	KNOWN_DISEASES VARCHAR(20),
	PRESCRIPTION VARCHAR(20),
	QUESTION VARCHAR(20),
	COMPLAINS VARCHAR(20),
	CONSTRAINT PatientId PRIMARY KEY(PATIENTID),
	CONSTRAINT FK_PatientID FOREIGN KEY (PATIENTID) REFERENCES ClinicUSERS(ID)
);

CREATE TABLE Vists(
	--- ID of the visit
	VISITID INT NOT NULL,
	--- Patient ID
	PatientID INT NOT NULL,
	--- Purpse of visit, like chest pain or what?
	PURPOSE VARCHAR(50) NOT NULL,
	--- Type of vist, reveal (كشف( , consultation(استشارة) , Others
	VISITTYPE VARCHAR(25) NOT NULL,
	--- Time of visit 
	VISIT_TIME DATETIME NOT NULL,
	--- Extra things like xray or scan or جلسة 
	EXTRA VARCHAR(20) NULL,
	--- Cost of visit
	COST NUMERIC NOT NULL
	CONSTRAINT VisitId PRIMARY KEY(VISITID),
	CONSTRAINT FK_vPatId FOREIGN KEY ( PatientID ) REFERENCES ClinicUSERS(ID)
);
