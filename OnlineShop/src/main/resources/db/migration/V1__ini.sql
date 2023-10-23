DROP TABLE IF EXISTS categories;
CREATE TABLE categories
(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, name VARCHAR(45) DEFAULT (NULL),
 some_text VARCHAR(45) DEFAULT (NULL), image_path VARCHAR(45) DEFAULT (NULL));
INSERT INTO categories(name, some_text, image_path)
VALUES
    ('Nike','Лучшие кроссовки Nike','images/sneakers.png'),
    ('Vans','Самые продаваемые Vans','images/running shoes.png');