create table if not exists persistent_logins (
  username varchar_ignorecase(100) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
  );

INSERT INTO Role VALUES (1, 'ADMIN');
INSERT INTO Role VALUES (2, 'BUYER');
INSERT INTO Role VALUES (3, 'SELLER');

INSERT INTO ADDRESS VALUES(1,'IOWA','USA','Fairfield','1000 North 4th Street','52557');

INSERT INTO USER VALUES(1,1,'admin@gmail.com','admin','Lyan','$2y$12$81Zbc8F8HytDnTd1rneOVOlsHM65sXnPSr.U5.LpPBY0k4XA50Ej2','1258975632',1,null,null);
INSERT INTO USER VALUES(2,1,'buyer@gmail.com','buyer','Lyan','$2y$12$81Zbc8F8HytDnTd1rneOVOlsHM65sXnPSr.U5.LpPBY0k4XA50Ej2','1258975632',1,null,null);

INSERT INTO USER VALUES(3,1,'seller@gmail.com','seller','Lyan','$2y$12$81Zbc8F8HytDnTd1rneOVOlsHM65sXnPSr.U5.LpPBY0k4XA50Ej2','1258975632',1,null,null);

INSERT INTO USER_ROLE VALUES(1,1);
INSERT INTO USER_ROLE VALUES(2,2);
INSERT INTO USER_ROLE VALUES(3,3);

INSERT INTO category (id,name) VALUES (1,'books');
INSERT INTO category (id,name) VALUES (2,'electronics');
INSERT INTO category (id,name) VALUES (3,'watches');
INSERT INTO PRODUCT (id,IS_ACTIVE,name,price,description,image,CATEGORY_ID,UNITS_IN_STOCK) VALUES (101,true,'acer',100,'Acer is best','1.jpg',2,50);
INSERT INTO PRODUCT (id,IS_ACTIVE,name,price,description,image,CATEGORY_ID,UNITS_IN_STOCK) VALUES (102,true,'Toshipa',300,'Toshiba is best','1.jpg',2,65);
