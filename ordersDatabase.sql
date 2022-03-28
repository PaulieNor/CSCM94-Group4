CREATE DATABASE cafe;
USE  cafe;

DROP DATABASE cafe;


CREATE TABLE menu(
    menu_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    cost_item double NOT NULL,
    item_wait INT NOT NULL
);

INSERT INTO menu(cost_item, item_wait)
        VALUES
        (23.66, 35),
        (10.99, 45),
        (5.76, 10),
        (13.50, 50),
        (12.85, 15),
        (14.60, 25)
;

CREATE TABLE customer(
    cust_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    cust_name varchar(255)
);

INSERT INTO customer(cust_name)
        VALUES
        ('james'),
        ('paul'),
        ('daisy'),
        ('ryan'),
        ('sum'),
        ('christian'),
        ('paddy')
;




CREATE TABLE orders(
    order_id INT AUTO_INCReMENT NOT NULL PRIMARY KEY,--auto
    cust_id INT NOT NULL,--FK
    type ENUM('eat in', 'delivery', 'takeaway') NOT NULL,--entered
    address VARCHAR(255),--entered
    driver_num int--FK
);

drop table orders;

INSERT INTO orders(cust_id,type, address, driver_num)
    VALUES 
    (2,'eat in',NULL,NULL),
    (3,'takeaway',NULL,NULL),
    (4,'delivery','TN5 6HA',3),
    (5,'delivery','TN5 6HA',1),
    (6,'eat in',NULL,NULL)
;

create VIEW order_waits as
SELECT 
order_id,
mu.menu_id,
mu.item_wait
from items_ordered
LEFT OUTER join menu mu on items_ordered.menu_id = mu.menu_id
--LEFT OUTER join menu m on m.item_wait = m.item_wait
;

SELECT MAX(item_wait) from order_waits
where order_id = 3;

CREATE VIEW max_wait_order as
SELECT
order_waits.*
from order_waits
inner join
    (SELECT order_id, MAX(item_wait) AS max_wait
    From order_waits
    GROUP BY order_id) grouped_orders
ON order_waits.order_id = grouped_orders.order_id
AND order_waits.item_wait = grouped_orders.max_wait
;

DROP VIEW max_wait_order;

SELECT * FROM  max_wait_order;

SELECT * FROM order_waits;
DROP VIEW order_waits;


CREATE TABLE items_ordered(
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    menu_id INT NOT NULL,
    order_id INT NOT NULL,
    cust_id INT NOT NULL,
    quantity int NOT NULL,
    --subtotal 
    FOREIGN KEY (menu_id) REFERENCES menu(menu_id),
    FOREIGN KEY (cust_id) REFERENCES customer(cust_id)
);
drop table items_ordered;
  
SELECT * FROM items_ordered;


INSERT INTO items_ordered(menu_id,order_id,cust_id,quantity)
        VALUES
        (2,1,1,1),
        (6,1,1,3),
        (5,1,1,1),
        (3,1,1,2),
        (4,1,1,4),
        (1,1,1,1)
;

INSERT INTO items_ordered(menu_id,order_id,cust_id,quantity)
        VALUES
        (1,2,1,1),
        (4,2,1,3),
        (5,3,1,1),
        (3,4,1,2),
        (2,5,1,4),
        (4,6,1,1)
;


CREATE VIEW driver_to_order AS
select 
order_id,
address,
type,
driver_num
FROM orders
WHERE orders.type = 'delivery'
;

SELECT * FROM driver_to_order;


---order_costs doesn't quite work yet, need to fix duplicates on joining
CREATE VIEW order_costs AS 
SELECT 
items_ordered.order_id,
(SUM(menu.cost_item)) as total_cost
from items_ordered
join menu on cost_item = menu.cost_item
GROUP BY order_id
;
--https://stackoverflow.com/questions/11014677/mysql-join-without-duplicates

DROP VIEW order_costs;


SELECT * FROM order_costs;


SELECT * FROM items_ordered;


SELECT 
SUM(cost_item) from menu
where menu_id > 2;