USE DTAPROYECT;
CREATE TABLE PRODUCTS 
(
id int primary key NOT NULL AUTO_INCREMENT,
nombre varchar (255) not null,
price DECIMAL (10,2) not null,
stock int not null,
vendedor varchar (255) not null,
createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
lastUpdate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

