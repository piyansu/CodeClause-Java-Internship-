-- Create the database
CREATE DATABASE cinema_booking;

-- Use the database
USE cinema_booking;

-- Create the table for booking information
CREATE TABLE IF NOT EXISTS booking_info (
    id INT AUTO_INCREMENT PRIMARY KEY,
    booking_id VARCHAR(255) NOT NULL ,  
    name VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    cinema VARCHAR(255) NOT NULL,
    time VARCHAR(255) NOT NULL,
    tickets INT NOT NULL,
    seat VARCHAR(255) NOT NULL,
    total_cost INT NOT NULL
);
DROP TABLE booking_info ; 
TRUNCATE TABLE booking_info;
SELECT * FROM booking_info ;