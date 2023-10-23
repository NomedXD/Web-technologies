DROP TABLE IF EXISTS users;
CREATE TABLE users(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, mail VARCHAR(45) UNIQUE DEFAULT (NULL),
                   password VARCHAR(70) DEFAULT (NULL), name VARCHAR(45) DEFAULT (NULL), surname VARCHAR(45) DEFAULT (NULL),
                   date DATE DEFAULT (NULL), mobile VARCHAR(45) DEFAULT ('Не указано'),
                   street VARCHAR(45) DEFAULT ('Не указано') , accommodation_number VARCHAR(45) DEFAULT ('Не указано'),
                   flat_number VARCHAR(45) DEFAULT ('Не указано'));