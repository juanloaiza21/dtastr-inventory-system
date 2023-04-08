USE DTAPROYECT;
SELECT * FROM USERS;
INSERT INTO USERS (id, nombre, email, cellphone, rol, pass)
VALUES
-- usuario1@example.com : password123 USUARIO
-- usuario2@example.com : abcd1234 USUARIO
-- usuario3@example.com : qwerty123 SELLER
-- usuario4@example.com : 1234abcd SELLER
  (1, 'Usuario 1', 'usuario1@example.com', '555-1234', 'user', '482c811da5d5b4bc6d497ffa98491e38'),
  (2, 'Usuario 2', 'usuario2@example.com', '555-5678', 'user', 'e19d5cd5af0378da05f63f891c7467af'),
  (3, 'Usuario 3', 'usuario3@example.com', '555-9012', 'seller', '3fc0a7acf087f549ac2b266baf94b8b1'),
  (4, 'Usuario 4', 'usuario4@example.com', '555-3456', 'seller', 'ef73781effc5774100f87fe2f437a435');