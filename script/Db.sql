USE DTAPROYECT;
CREATE TABLE USERS 
(
id int primary key not null,
nombre varchar (255) not null,
email varchar (255) not null unique,
cellphone varchar (20) not null,
rol enum("seller", "user") NOT NULL,
pass VARCHAR(255) NOT NULL,
createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
lastUpdate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE PRODUCTS 
(
id int primary key not null,
nombre varchar (255) not null,
precio DECIMAL (10,2) not null,
cantidad int not null,
proveedor varchar (255) not null,
primary key(id)
);