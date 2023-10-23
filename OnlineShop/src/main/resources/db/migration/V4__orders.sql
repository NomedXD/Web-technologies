DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,price FLOAT DEFAULT (NULL),
 date DATE DEFAULT (NULL), user_id INT, cc_number TINYTEXT DEFAULT (NULL), shipping_type VARCHAR(45) DEFAULT (NULL),
 shipping_cost FLOAT(4,2) DEFAULT (NULL), discount_code_id INT DEFAULT (NULL), address TINYTEXT DEFAULT (NULL), customer_notes TEXT DEFAULT (NULL),
 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
 FOREIGN KEY (discount_code_id) REFERENCES discount_codes (id) ON UPDATE CASCADE);

DROP TABLE IF EXISTS orders_products;
CREATE TABLE orders_products(order_id INT NOT NULL, product_id INT NOT NULL, PRIMARY KEY(order_id, product_id));