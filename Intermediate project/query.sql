create DATABASE HOSPITAL ;
USE HOSPITAL ;
CREATE TABLE patient (
    patient_no INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    ph_no VARCHAR(10),
    dob VARCHAR(255),
    gender VARCHAR(10),
    email VARCHAR(255),
    marital VARCHAR(20),
    address VARCHAR(255),
    city VARCHAR(50),
    state VARCHAR(50),
    pincode VARCHAR(20)
);

SELECT * FROM patient;
TRUNCATE TABLE patient;
DROP TABLE patient ;