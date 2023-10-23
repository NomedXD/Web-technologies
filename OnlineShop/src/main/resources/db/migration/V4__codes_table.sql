DROP TABLE IF EXISTS discount_codes;
CREATE TABLE discount_codes
(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT, name VARCHAR(20) NOT NULL, discount FLOAT(4,2) NOT NULL);
INSERT INTO discount_codes(name, discount)
VALUES
    ('ПАПИЧ', 10.00),
    ('МИНСК',5.00);
