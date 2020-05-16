create table if not exists persistent_logins (
  username varchar_ignorecase(100) not null,
  series varchar(64) primary key,
  token varchar(64) not null,
  last_used timestamp not null
  );

INSERT INTO Role VALUES (1, 'ADMIN');
INSERT INTO Role VALUES (2, 'BUYER');
INSERT INTO Role VALUES (3, 'SELLER');

insert into address (id,is_billing, city, country, is_shipping, state, street, user_id, zip_code) values(1,true,'Fairfield','US',false,'Iowa','1000 North 4th Street',1,'52557');
insert into user (user_id, active, address_id, email, last_name, name, password, phone_number) values (1, true, 1, 'buyer01@gmail.com','Jules','Buyer','$2y$12$81Zbc8F8HytDnTd1rneOVOlsHM65sXnPSr.U5.LpPBY0k4XA50Ej2', '132147856');
insert into carts (cart_lines, total_price, id) values (0, 0.0, 1);

update user set cart_id=1  where user_id=1;
update carts set user_user_id=1 where id=1;

insert into address (id,is_billing, city, country, is_shipping, state, street, user_id, zip_code) values(2,false,'Chicago','US',false,'Illinois','1000 North 4th Street',2,'52457');
insert into user (user_id, active, address_id, email, last_name, name, password, phone_number) values (2, true, 2, 'seller01@gmail.com','Aden','Seller','$2y$12$81Zbc8F8HytDnTd1rneOVOlsHM65sXnPSr.U5.LpPBY0k4XA50Ej2', '134787856');


insert into address (id,is_billing, city, country, is_shipping, state, street, user_id, zip_code) values(3,false,'Dallas','US',false,'Texas','1000 North 4th Street',3,'52857');
insert into user (user_id, active, address_id, email, last_name, name, password, phone_number) values (3, true, 3, 'admin01@gmail.com','Chris','Admin','$2y$12$81Zbc8F8HytDnTd1rneOVOlsHM65sXnPSr.U5.LpPBY0k4XA50Ej2', '132147856');





INSERT INTO USER_ROLE VALUES(1,2);
INSERT INTO USER_ROLE VALUES(2,3);
INSERT INTO USER_ROLE VALUES(3,1);

INSERT INTO category (id,name) VALUES (1,'Books');
INSERT INTO category (id,name) VALUES (2,'Electronics');
INSERT INTO category (id,name) VALUES (3,'Watches');

INSERT INTO PRODUCT (id,IS_ACTIVE,name,price,description,CATEGORY_ID,UNITS_IN_STOCK,PURCHASES,SUPPLIER_USER_ID) VALUES (101,true,'Mac Air',1500,'The new Mac air is best',2,50,0,2);
INSERT INTO PRODUCT (id,IS_ACTIVE,name,price,description,CATEGORY_ID,UNITS_IN_STOCK,PURCHASES,SUPPLIER_USER_ID) VALUES (102,true,'Toshiba',750,'High resolution and speed computer',2,65,0,2);
INSERT INTO PRODUCT (id,IS_ACTIVE,name,price,description,CATEGORY_ID,UNITS_IN_STOCK,PURCHASES,SUPPLIER_USER_ID) VALUES (103,true,'HP',800,'New Generation computer with 16GB of RAM in black and gold colors',2,65,0,2);
