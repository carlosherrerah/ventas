-- Base de datos: ventas
use ventas;

CREATE TABLE employees (
  EmployeeID int(10) AUTO_INCREMENT PRIMARY KEY,
  LastName varchar(20) NOT NULL,
  FirstName varchar(10),
  BirthDate date,
  HireDate date
);

CREATE TABLE orders (
  OrderID int(10) AUTO_INCREMENT PRIMARY KEY,
  EmployeeID int(10),
  OrderDate date,
  CONSTRAINT Emp_Ord FOREIGN KEY (EmployeeID) 
            REFERENCES employees (EmployeeID)
);

CREATE TABLE products (
  ProductID int(10) AUTO_INCREMENT PRIMARY KEY,
  ProductName varchar(40),
  UnitPrice decimal(12,4) check(UnitPrice > 0)
);

CREATE TABLE orderdetails (
  OrderID int(10),
  ProductID int(10),
  UnitPrice decimal(12,4),
  Quantity smallint(5),
  Discount double,
  CONSTRAINT PRIMARY_KEY PRIMARY KEY(OrderID, ProductID),
  CONSTRAINT Ord_det FOREIGN KEY (OrderID)   REFERENCES orders (OrderID),
  CONSTRAINT det_pro FOREIGN KEY (ProductID) REFERENCES products (ProductID)
  );



