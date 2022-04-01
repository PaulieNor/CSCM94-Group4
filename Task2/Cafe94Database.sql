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
--- Generate views for staff data.
---

CREATE VIEW MostHoursWorked AS 
SELECT StaffFirst_Name, StaffLast_Name, StaffType, HoursToWork
FROM Staff
WHERE HoursToWork = (SELECT MAX(HoursToWork) FROM Staff)
LIMIT 1;


DROP VIEW mosthoursworked; 

SELECT * FROM mosthoursworked; 


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
    DeliveryOrderCompleted BOOLEAN NOT NULL, 
    DeliveryMain INT NOT NULL, 
    DeliverySide INT NOT NULL,
    DeliveryDrink INT NOT NULL,
    FOREIGN KEY (DeliveryMain) REFERENCES MenuItems(MenuItemID),
    FOREIGN KEY (DeliverySide) REFERENCES MenuItems(MenuItemID),
    FOREIGN KEY (DeliveryDrink) REFERENCES MenuItems(MenuItemID) 
    -- FOREIGN KEY(StaffType) REFERENCES Staff(StaffType), foreign key, I am trying to assign delivery driver here.
    EstimatedDeliveryTime INT -- for now an int, but will become SUM of item weights. (In minutes).
); 

---
--- DeliveryOrders data dump. 
---

INSERT INTO DeliveryOrders (DeliveryCustomerID, DeliveryAddress, 
DeliveryOrderCompleted, DeliveryMain, DeliverySide, DeliveryDrink, EstimatedDeliveryTime) VALUES
(1, 'SA14 8XT', 0, 'Salmon Fillet', 'Chips', 'Coca Cola', 45),
(2, 'TW20 0ED', 0, 'Chicken Burger', 'Fruit Salad', 'Water', 55),
(3, 'TW20 0ER', 0, 'Veggie Burger', 'Salad', 'Coca Cola', 30),
(4, 'SA1 3LS', 0, 'Meatballs', 'Mash Potato', 'Water', 40),
(5, 'SA14 9CD', 0, 'Lentil Soup', 'Bread and Butter', 'Coffee', 35),
(6, 'SA14 4RD', 0, 'Salmon Fillet', 'Mash Potato', 'Coca Cola', 45),
(7, 'SA15 3XQ', 0, 'Chicken Burger', 'Chips', 'Tea', 55),
(8, 'SA1 3WT', 0, 'Meatballs', 'Chips', 'Beer', 40),
(9, 'SA15 8XW', 0, 'Salmon Fillet', 'Salad', 'Wine', 25),
(10, 'TW20 4ER', 0, 'Veggie Burger', 'Chips', 'Long Island Iced Tea', 30);


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
    SitDownCompletedOrder BOOLEAN NOT NULL, 
    SitDownMain INT NOT NULL, 
    SitDownSide INT NOT NULL,
    SitDownDrink INT NOT NULL,
    FOREIGN KEY (SitDownMain) REFERENCES MenuItems(MenuItemID),
    FOREIGN KEY (SitDownSide) REFERENCES MenuItems(MenuItemID),
    FOREIGN KEY (SitDownDrink) REFERENCES MenuItems(MenuItemID) 
); 

---
--- SitDownOrders data dump. 
---
INSERT INTO SitDownOrders (SitDownCustomerID, TableNumber, 
SitDownOrderCompleted, SitDownMain, SitDownSide, SitDownDrink) VALUES
(1, 4, 0, 'Chicken Burger', 'Chips', 'Long Island Iced Tea'),
(2, 4, 0, 'Veggie Burger', 'Salad', 'Coca Cola'),
(3, 3, 0, 'Salmon Fillet', 'Mash Potato', 'Water'),
(4, 6, 0, 'Lentil Soup', 'Bread and Butter', 'Tea'),
(5, 2, 0, 'Veggie Burger', 'Chips', 'Water'),
(6, 1, 0, 'Meatballs', 'Fruit Salad', 'Beer'),
(7, 1, 0, 'Salmon Fillet', 'Salad', 'Wine'),
(8, 1, 0, 'Lentil Soup', 'Salad', 'Water'),
(9, 4, 0, 'Meatballs', 'Chips', 'Coca Cola'),
(10, 4, 0, 'Meatballs', 'Mash Potato', 'Wine');


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
;

---
--- TakeawayOrders table structure. 
---


CREATE TABLE TakeawayOrders (
    TakeawayOrderID INT AUTO_INCREMENT NOT NULL PRIMARY KEY, 
    TakeawayCustomerID INT NOT NULL, 
    PickUpTime DATETIME NOT NULL, 
    TakeawayOrderCompleted BOOLEAN NOT NULL, 
    TakeawayMain INT NOT NULL, 
    TakeawaySide INT NOT NULL,
    TakeawayDrink INT NOT NULL,
    FOREIGN KEY (TakeawayMain) REFERENCES MenuItems(MenuItemID),
    FOREIGN KEY (TakeawaySide) REFERENCES MenuItems(MenuItemID),
    FOREIGN KEY (TakeawayDrink) REFERENCES MenuItems(MenuItemID) 
); 

DROP TABLE IF EXISTS TakeawayOrders; 

---
--- Data dump for takeaway orders. 
--- Datetime in format YYYY-MM-DD HH:MM:SS
---

INSERT INTO TakeawayOrders (TakeawayCustomerID, PickUpTime, TakeawayOrderCompleted,
TakeawayMain, TakeawaySide, TakeawayDrink) VALUES
(1, '2022-02-05 12:00:00', 0, 3, 6, 14),
(2, '2022-02-05 13:00:00', 0, 4, 7, 12),
(3, '2022-02-05 13:00:00', 0, 5, 9, 14),
(4, '2022-02-05 13:30:00', 0, 5, 7, 14),
(5, '2022-02-05 14:15:00', 0, 1, 9, 15),
(6, '2022-02-05 11:30:00', 0, 3, 6, 12),
(7, '2022-02-05 14:35:00', 0, 2, 6, 14),
(8, '2022-02-06 15:15:00', 0, 3, 7, 14),
(9, '2022-02-06 15:30:00', 0, 4, 8, 14),
(10, '2022-02-06 12:30:00', 0, 5, 7, 14); 


---
--- Table check for TakeawayOrders
---

SELECT * FROM TakeawayOrders; 


----------------------------------------------------------------


---
--- MENU DATA
--- @Author: Daisy 
---

CREATE TABLE MenuItems (
    MenuItemID INT AUTO_INCREMENT NOT NULL PRIMARY KEY, 
    ItemName VARCHAR(255) NOT NULL,
    ItemType ENUM('Main', 'Side', 'Drink') NOT NULL, 
    Price DOUBLE NOT NULL, -- £GBP
    TimeToMake INT NOT NULL, -- in MINUTES, a weight, this can be summed in the orders table through a relation producing delivery time.
    IsVegetarian BOOLEAN NOT NULL
);

---
--- Just in case of edits, drop table tor re-create.
---
DROP TABLE IF EXISTS MenuItems; 

---
--- Populate table with trial data.
---
INSERT INTO MenuItems (ItemName, ItemType, Price, TimeToMake, IsVegetarian) VALUES 
('Chicken Burger', 'Main', 10.00, 20, 0),
('Veggie Burger', 'Main', 8.00, 25, 1),
('Salmon Fillet', 'Main', 14.00, 15, 0),
('Meatballs', 'Main', 5.00, 18, 0),
('Lentil Soup', 'Main', 6.00, 5, 1),
('NO MAIN', 'Main', 0.00, 0, 1), -- No main option. 
('Chips', 'Side', 3.00, 8, 1),
('Salad', 'Side', 4.00, 5, 1), 
('Bread and Butter', 'Side', 2.50, 2, 1),
('Mash Potato', 'Side', 3.50, 6, 1),
('Fruit Salad', 'Side', 3.20, 8, 1),
('NO SIDE', 'Side', 0.00, 0, 1), -- No side option. 
('Coca Cola', 'Drink', 2.50, 2, 1),
('Water', 'Drink', 1.00, 2, 1),
('Coffee', 'Drink', 4.20, 5, 1),
('Tea', 'Drink', 2.50, 4, 1),
('Wine', 'Drink', 5.60, 2, 1), -- Future scope: maybe seperate drinks and add customer age check BOOLEAN (18+).
('Beer', 'Drink', 4.80, 2, 1),
('Long Island Iced Tea', 'Drink', 7.30, 6, 1),
('NO DRINK', 'Drink', 0.00, 0, 1); -- No drink option. 


---
--- Test to see everything works, show table.
---
SELECT * FROM MenuItems; 

----------------------------------------------------------------

---
--- CAFE_TABLES DATA
--- @Author: Paul
---

---
--- Table structure, for tables table (try saying that fast). 
---
CREATE TABLE CafeTables (
    TableID VARCHAR(255) NOT NULL PRIMARY KEY, -- Decided against auto_increment, as this is more static and "controllable" data. 
    NumberOfSeats INT NOT NULL,
    IsAvailable BOOLEAN NOT NULL
); --Total capacity would go here as a SUM of NumberOfSeats or a query, but not needed for Coursework 2. 

---
--- Just in case of mistake, drop table to edit then CREATE again. 
---
DROP TABLE IF EXISTS CafeTables; 

---
--- Data dump for cafe tables, this is perhaps more rigid for version 1.0 as cafe has set amount of tables.
---
INSERT INTO CafeTables (TableID, NumberOfSeats, IsAvailable) VALUES
('Table1', 2, 0),
('Table2', 2, 1),
('Table3', 2, 1),
('Table4', 2, 0),
('Table5', 4, 0),
('Table6', 4, 0),
('Table7', 4, 0),
('Table8', 4, 1),
('Table9', 8, 1),
('Table10', 8, 1),
('Table11', 10, 1);


---
--- Test to see table forms as expected.
---
SELECT * FROM CafeTables; 

----------------------------------------------------------------

---
--- BOOKING DATA
--- @Author James and (Patrick?) -> Will require collaboration/pair programming. 
---


---
--- Booking table structure. 
---
DROP TABLE BookingTables;
CREATE TABLE BookingTables(
    BookingID INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    BookingTime DATETIME NOT NULL,
    CustomerID INT NOT NULL,
    NumberGuests INT NOT NULL,
    TableID VARCHAR(255) NOT NULL,
    FOREIGN KEY (TableID) REFERENCES CafeTables(TableID)
);

INSERT INTO BookingTables(BookingTime, CustomerID, NumberGuests,TableID)
        VALUES
        ('2022-04-14 12:30:00', 1, 4, 'Table8'),
        ('2022-04-15 13:30:00', 2, 2, 'Table2'),
        ('2022-04-15 14:30:00', 3, 2, 'Table3'),
        ('2022-04-15 17:00:00', 4, 2, 'Table2'),
        ('2022-04-16 12:30:00', 5, 10, 'Table11'),
        ('2022-04-16 13:45:00', 6, 8, 'Table9'),
        ('2022-04-16 14:30:00', 7, 4, 'Table8');

SELECT * FROM BookingTables;

CREATE VIEW AvailableTables AS 
SELECT * FROM CafeTables 
WHERE IsAvailable = 1;

SELECT * FROM availabletables;
