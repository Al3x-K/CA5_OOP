CREATE TABLE Products (
    ProductID int NOT NULL,
    ProductName varchar(50)  NOT NULL,
    CONSTRAINT Products_pk PRIMARY KEY (ProductID)
);
CREATE TABLE Vendors (
    VendorID int NOT NULL,
    VendorName varchar(50)  NOT NULL,
    CONSTRAINT Vendors_pk PRIMARY KEY (VendorID)
);
CREATE TABLE ProductsVendors (
    ProductID int NOT NULL,
    VendorID int NOT NULL,
    Price double(6,2) NOT NULL,
    Quantity int NOT NULL,
    CONSTRAINT ProductsVendors_pk PRIMARY KEY (ProductID,VendorID)
);

ALTER TABLE ProductsVendors ADD CONSTRAINT ProductsVendors_Products FOREIGN KEY ProductsVendors_Products (ProductID)
    REFERENCES Products (ProductID);

ALTER TABLE ProductsVendors ADD CONSTRAINT ProductsVendors_Vendors FOREIGN KEY ProductsVendors_Vendors (VendorID)
    REFERENCES Vendors (VendorID);
	
INSERT INTO Products VALUES(1,"Gold Ring");
INSERT INTO Products VALUES(2,"Silver Ring");
INSERT INTO Products VALUES(3,"Necklace");
INSERT INTO Products VALUES(4,"Gold Earrings");
INSERT INTO Products VALUES(5,"Headband");
INSERT INTO Products VALUES(6,"Hairclip");
INSERT INTO Products VALUES(7,"Armlet");
INSERT INTO Products VALUES(8,"Bracelet");
INSERT INTO Products VALUES(9,"Pendant");
INSERT INTO Products VALUES(10,"Medalion");

INSERT INTO Vendors VALUES(1,"Wendy's");
INSERT INTO Vendors VALUES(2,"Kane's Jewellery");
INSERT INTO Vendors VALUES(3,"Gold Dragon");

INSERT INTO ProductsVendors VALUES (1,1,140.20,7);
INSERT INTO ProductsVendors VALUES (1,3,93.80,2);
INSERT INTO ProductsVendors VALUES (2,1,45.00,5);
INSERT INTO ProductsVendors VALUES (2,2,39.99,2);
INSERT INTO ProductsVendors VALUES (3,3,55.30,4);
INSERT INTO ProductsVendors VALUES (3,2,72.80,6);
INSERT INTO ProductsVendors VALUES (4,1,120.00,2);
INSERT INTO ProductsVendors VALUES (4,2,125.00,3);
INSERT INTO ProductsVendors VALUES (4,3,112.50,3);
INSERT INTO ProductsVendors VALUES (5,1,7.20,12);
INSERT INTO ProductsVendors VALUES (5,2,8.90,25);
INSERT INTO ProductsVendors VALUES (6,1,2.40,15);
INSERT INTO ProductsVendors VALUES (6,3,2.50,16);
INSERT INTO ProductsVendors VALUES (7,1,15.20,3);
INSERT INTO ProductsVendors VALUES (7,2,18.20,4);
INSERT INTO ProductsVendors VALUES (7,3,16.20,5);
INSERT INTO ProductsVendors VALUES (8,2,75.00,4);
INSERT INTO ProductsVendors VALUES (9,1,54.90,3);
INSERT INTO ProductsVendors VALUES (9,3,55.00,3);
INSERT INTO ProductsVendors VALUES (10,2,87.30,1);