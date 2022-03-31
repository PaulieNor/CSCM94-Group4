---
--- @Cafe94 Database
--- @Authors: Group 4 -> James McMillan finalised this database. 
--- @Version: 1.0
---

----------------------------------------------------------------

---
--- STAFF DATA 
--- @Author: Sumi 
---


---
--- Staff table structure. 
---

CREATE TABLE Staff (
    StaffUsername varchar(255) NOT NULL,
    StaffType ENUM('Manager', 'Chef', 'Waiter', 'DeliveryDriver') NOT NULL,
    StaffFirst_Name varchar(255) NOT NULL, 
    StaffLast_Name varchar(255) NOT NULL, 
    StaffPassword varchar(255) NOT NULL, --Possibly make more secure, hash?
    HoursToWork INT NOT NULL
);

---
--- Dumping data for the staff table. 
---

INSERT INTO Staff (StaffUsername, StaffType, StaffFirst_Name, StaffLast_Name,
StaffPassword, HoursToWork) VALUES 
('BB', 'Manager', 'Big', 'Boss', 'Password1', 40),
('JS', 'Chef', 'Jessica', 'Smith', 'Password2', 55),
('KD', 'Chef', 'kierra', 'Davies', 'Password3', 55),
('RP', 'Chef', 'Ronnie', 'Peck', 'Password4', 55),
('JO', 'Waiter', 'Jenkins', 'Oldman', 'Password5', 20),
('CP', 'Waiter', 'Cory', 'Peterson', 'Password6', 25),
('LS', 'Waiter', 'Laika', 'Sputnik', 'Password7', 33),
('CH', 'Waiter', 'Chloe', 'Hines', 'Password8', 30),
('MW', 'DeliveryDriver', 'Marlon', 'Warren', 'Password9', 55),
('CW', 'DeliveryDriver', 'Carmelo', 'Woodward', 'Password10', 60); 

---
--- Check to see the table is playing nice.
---
SELECT * FROM Staff; 


---
--- Staff table primary key allocation.
---
ALTER TABLE Staff
    ADD PRIMARY KEY (StaffUsername); 


----------------------------------------------------------------

---
--- CUSTOMER DATA 
--- @Author: James
---

--- Table structure for the Customer table. No need for password, just profiles. 
--- Order history could be dealt with separately using ordersDatabase primary key
--- candidate from this table would be CustomerUserID, this could then be placed in
--- a table with order_id attactched to each customer ID. 
---

CREATE TABLE Customers (
    CustomerUserID varchar(255) NOT NULL,
    CustomerFirstName varchar(255) NOT NULL,
    CustomerLastName varchar(255) NOT NULL, 
    CustomerStreetAdd varchar(255) NOT NULL,
    CustomerPostCode varchar(255) NOT NULL
);

--- 
--- Trial data for Customers table.
---
INSERT INTO Customers (CustomerUserID, CustomerFirstName, 
CustomerLastName, CustomerStreetAdd, CustomerPostCode) VALUES 
('SC', 'Simon', 'Cook', '56 Harvest Road', 'SA1 3LS'),
('TT', 'Tim', 'Thomas', '128 Maes Ty Wyn', 'SA14 3LS'),
('TO', 'Tom', 'Owen', '22 Pentre Road', 'SA1 3SQ'),
('SH', 'Sally', 'Harris', '54 Zoo Lane', 'SA15 3ET'),
('AC', 'Archie', 'Charlston', '21 Mangolia Street', 'SA14 3QD'),
('WS', 'Willow', 'Sarah', '102 Maes Geraint', 'SA14 8XQ'),
('AO', 'Annie', 'Olivia', '123 Metro Station', 'SA1 C1D'),
('OT', 'Oliver', 'Twist', '56 Heol Goring', 'SA15 3LK'),
('CB', 'Charles', 'Babbish', '129 Severn Ave', 'SA1 2QS'),
('MH', 'Mark', 'Hamill', '117 Knightsbridge Ave', 'TW20 0EX');



---
--- Primary key allocation FOR CUSTOMERS. 
--- 
ALTER TABLE Customers
    ADD PRIMARY KEY (CustomerUserID); 

---
--- Table check 
---
SELECT * FROM Customers; 


----------------------------------------------------------------

---
--- ORDERS DATA 
--- @Author: Patrick (and James)
---

---
--- Table Structure for orders.
--- Note: Each order has a set composition,
--- Choose a main, choose a side, choose a drink.
--- Orders is three tables for three different types.
--- As each order type has differing attributes.
---


---
--- DeliveryOrders
---

CREATE TABLE DeliveryOrders (
    DeliveryOrderID INT AUTO_INCREMENT NOT NULL PRIMARY KEY, 
    DeliveryCustomerID INT NOT NULL,
    DeliveryAddress varchar(255) NOT NULL,
    DeliveryMain ENUM('Chicken Burger', 'Veggie Burger', 'Salmon Fillet', 'Meatballs', 'Lentil Soup') NOT NULL,
    DeliverySide ENUM('Chips', 'Salad', 'Bread and Butter', 'Mash Potato', 'Fruit salad') NOT NULL, 
    DeliveryDrink ENUM('Coca Cola', 'Water', 'Coffee', 'Tea', 'Wine', 'Beer', 'Long Island Iced Tea') NOT NULL, 
    -- FOREIGN KEY(StaffType) REFERENCES Staff(StaffType), foreign key, I am trying to assign delivery driver here.
    EstimatedDeliveryTime INT -- for now an int, but will become SUM of item weights. (In minutes).
); 

---
--- DeliveryOrders data dump. 
---

INSERT INTO DeliveryOrders (DeliveryCustomerID, DeliveryAddress, 
DeliveryMain, DeliverySide, DeliveryDrink, EstimatedDeliveryTime) VALUES
(1, 'SA14 8XT', 'Salmon Fillet', 'Chips', 'Coca Cola', 45),
(2, 'TW20 0ED', 'Chicken Burger', 'Fruit Salad', 'Water', 55),
(3, 'TW20 0ER', 'Veggie Burger', 'Salad', 'Coca Cola', 30),
(4, 'SA1 3LS', 'Meatballs', 'Mash Potato', 'Water', 40),
(5, 'SA14 9CD', 'Lentil Soup', 'Bread and Butter', 'Coffee', 35),
(6, 'SA14 4RD', 'Salmon Fillet', 'Mash Potato', 'Coca Cola', 45),
(7, 'SA15 3XQ', 'Chicken Burger', 'Chips', 'Tea', 55),
(8, 'SA1 3WT', 'Meatballs', 'Chips', 'Beer', 40),
(9, 'SA15 8XW', 'Salmon Fillet', 'Salad', 'Wine', 25),
(10, 'TW20 4ER', 'Veggie Burger', 'Chips', 'Long Island Iced Tea', 30);


DROP TABLE IF EXISTS `DeliveryOrders`;

---
--- Deliver order table check.
---
SELECT * FROM DeliveryOrders;


---
--- SitDownOrders table structure. 
---

CREATE TABLE SitDownOrders (
    SitDownOrderID INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    SitDownCustomerID INT NOT NULL,
    TableNumber INT NOT NULL,
    SitDownMain ENUM ('Chicken Burger', 'Veggie Burger', 'Salmon Fillet', 'Meatballs', 'Lentil Soup') NOT NULL, 
    SitDownSide ENUM ('Chips', 'Salad', 'Bread and Butter', 'Mash Potato', 'Fruit salad') NOT NULL,
    SitDownDrink ENUM ('Coca Cola', 'Water', 'Coffee', 'Tea', 'Wine', 'Beer', 'Long Island Iced Tea') NOT NULL
); 

---
--- SitDownOrders data dump. 
---
INSERT INTO SitDownOrders (SitDownCustomerID, TableNumber, 
SitDownMain, SitDownSide, SitDownDrink) VALUES
(1, 4, 'Chicken Burger', 'Chips', 'Long Island Iced Tea'),
(2, 4, 'Veggie Burger', 'Salad', 'Coca Cola'),
(3, 3, 'Salmon Fillet', 'Mash Potato', 'Water'),
(4, 6, 'Lentil Soup', 'Bread and Butter', 'Tea'),
(5, 2, 'Veggie Burger', 'Chips', 'Water'),
(6, 1, 'Meatballs', 'Fruit Salad', 'Beer'),
(7, 1, 'Salmon Fillet', 'Salad', 'Wine'),
(8, 1, 'Lentil Soup', 'Salad', 'Water'),
(9, 4, 'Meatballs', 'Chips', 'Coca Cola'),
(10, 4, 'Meatballs', 'Mash Potato', 'Wine');


---
--- SitDown order table check.
---
SELECT * FROM SitDownOrders; 


---
--- Here is where different views and queries will go for order tables. 
---
CREATE VIEW sitDownOrderForTable AS 
SELECT 
SitDownOrderID,
SitDownMain,
SitDownSide,
SitDownDrink
FROM SitDownOrders WHERE SitDownCustomerID = -- I am trying to create a view for particular tables here.


---
--- TakeawayOrders table structure. 
---


CREATE TABLE TakeawayOrders (
    TakeawayOrderID INT AUTO_INCREMENT NOT NULL PRIMARY KEY, 
    TakeawayCustomerID INT NOT NULL, 
    PickUpTime DATETIME NOT NULL, 
    TakeawayMain ENUM ('Chicken Burger', 'Veggie Burger', 'Salmon Fillet', 'Meatballs', 'Lentil Soup') NOT NULL, 
    TakeawaySide ENUM ('Chips', 'Salad', 'Bread and Butter', 'Mash Potato', 'Fruit salad') NOT NULL,
    TakeawayDrink ENUM ('Coca Cola', 'Water', 'Coffee', 'Tea', 'Wine', 'Beer', 'Long Island Iced Tea') NOT NULL
); 

DROP TABLE IF EXISTS TakeawayOrders; 

---
--- Data dump for takeaway orders. 
--- Datetime in format YYYY-MM-DD HH:MM:SS
---

INSERT INTO TakeawayOrders (TakeawayCustomerID, PickUpTime, 
TakeawayMain, TakeawaySide, TakeawayDrink) VALUES
(1, '2022-02-05 12:00:00', 'Chicken Burger', 'Chips', 'Water'),
(2, '2022-02-05 13:00:00', 'Meatballs', 'Salad', 'Wine'),
(3, '2022-02-05 13:00:00', 'Salmon Fillet', 'Mash Potato', 'Beer'),
(4, '2022-02-05 13:30:00', 'Salmon Fillet', 'Salad', 'Water'),
(5, '2022-02-05 14:15:00', 'Lentil Soup', 'Salad', 'Wine'),
(6, '2022-02-05 11:30:00', 'Veggie Burger', 'Fruit Salad', 'Wine'),
(7, '2022-02-05 14:35:00', 'Chicken Burger', 'Chips', 'Coca Cola'),
(8, '2022-02-06 15:15:00', 'Veggie Burger', 'Chips', 'Beer'),
(9, '2022-02-06 15:30:00', 'Lentil Soup', 'Salad', 'Tea'),
(10, '2022-02-06 12:30:00', 'Salmon Fillet', 'Chips', 'Beer'); 


---
--- Table check for TakeawayOrders
---

SELECT * FROM TakeawayOrders; 


----------------------------------------------------------------


---
--- MENU DATA
--- @Author: Daisy 
---





----------------------------------------------------------------

---
--- CAFE_TABLES DATA
--- @Author: Paul
---

----------------------------------------------------------------

---
--- BOOKING DATA
--- @Author James 
--- This has not been implemented yet. 
---