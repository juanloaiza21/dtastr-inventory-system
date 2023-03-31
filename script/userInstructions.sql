USE DTAPROYECT;
CREATE TABLE USERS 
(
id int primary key not null,
nombre varchar (255) not null,
email varchar (255) not null,
cellphone varchar (20) not null,
rol enum("seller", "user") NOT NULL,
pass VARCHAR(255) NOT NULL,
createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
lastUpdate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);