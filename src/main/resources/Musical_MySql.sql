DROP DATABASE if exists MUSICAL;
CREATE DATABASE MUSICAL;
USE  MUSICAL;

CREATE TABLE EK_USER(
	EMAIL_ID VARCHAR(50),
	NAME VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(70) NOT NULL,
	constraint EK_CUSTOMER_EMAIL_ID_PK primary key ( EMAIL_ID )
);

INSERT INTO EK_USER (EMAIL_ID, NAME, PASSWORD) VALUES ('aayurshi@gmail.com', 'Aayurshi', 'Aayurshi@123');
INSERT INTO EK_USER (EMAIL_ID, NAME, PASSWORD) VALUES ('shubham@gmail.com', 'Shubham', 'Shubham@123');

Select * from EK_USER;
