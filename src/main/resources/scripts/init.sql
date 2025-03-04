create database subscription;

use subscription;

CREATE TABLE subscription (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              firstname VARCHAR(50) NOT NULL,
                              lastname VARCHAR(50) NOT NULL,
                              email VARCHAR(100) UNIQUE NOT NULL,
                              phonenumber VARCHAR(20) UNIQUE
);
