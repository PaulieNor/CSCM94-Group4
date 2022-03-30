-- PHP Stuff might go up here, find out about where to host the DB. 

-- Database: 'Customers'
-- Author: James McMillan
-- --------------------------------------------------------------------------------

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
--- Primary key allocation. 
--- 
ALTER TABLE Customers
    ADD PRIMARY KEY (CustomerUserID); 