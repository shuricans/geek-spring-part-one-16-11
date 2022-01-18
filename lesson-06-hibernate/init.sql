DROP DATABASE IF EXISTS lesson_6;
CREATE DATABASE lesson_6 ENCODING = 'UTF8';

ALTER DATABASE lesson_6 OWNER TO postgres;


-- Customer

CREATE SEQUENCE customers_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE customers_sequence
    OWNER TO postgres;

CREATE TABLE customer
(
    customer_id  bigint NOT NULL DEFAULT nextval('customers_sequence'),
    user_id bigint
);

ALTER TABLE customer
    OWNER TO postgres;


-- Item

CREATE SEQUENCE items_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE items_sequence
    OWNER TO postgres;

CREATE TABLE item
(
    item_id    bigint         NOT NULL DEFAULT nextval('items_sequence'),
    count      integer        NOT NULL,
    price      numeric(19, 2) NOT NULL,
    order_id   bigint         NOT NULL,
    product_id bigint         NOT NULL
);

ALTER TABLE item
    OWNER TO postgres;


-- Order

CREATE SEQUENCE orders_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE orders_sequence
    OWNER TO postgres;

CREATE TABLE ordr
(
    order_id    bigint                      NOT NULL DEFAULT nextval('orders_sequence'),
    date        timestamp without time zone NOT NULL,
    customer_id bigint
);

ALTER TABLE ordr
    OWNER TO postgres;


-- Product

CREATE SEQUENCE products_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE products_sequence
    OWNER TO postgres;

CREATE TABLE product
(
    product_id bigint                 NOT NULL DEFAULT nextval('products_sequence'),
    name       character varying(255) NOT NULL,
    price      numeric(19, 2)         NOT NULL
);

ALTER TABLE product
    OWNER TO postgres;


-- User

CREATE SEQUENCE users_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE users_sequence
    OWNER TO postgres;

CREATE TABLE usr
(
    user_id bigint                 NOT NULL DEFAULT nextval('users_sequence'),
    name    character varying(255) NOT NULL
);

ALTER TABLE usr
    OWNER TO postgres;

-- primary keys

ALTER TABLE ONLY usr
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);

ALTER TABLE ONLY customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (customer_id);

ALTER TABLE ONLY item
    ADD CONSTRAINT item_pkey PRIMARY KEY (item_id);

ALTER TABLE ONLY ordr
    ADD CONSTRAINT order_pkey PRIMARY KEY (order_id);

ALTER TABLE ONLY product
    ADD CONSTRAINT product_pkey PRIMARY KEY (product_id);

-- unique

ALTER TABLE ONLY product
    ADD CONSTRAINT uk_product_name UNIQUE (name);

ALTER TABLE ONLY usr
    ADD CONSTRAINT uk_user_name UNIQUE (name);

-- foreign keys

ALTER TABLE ONLY customer
    ADD CONSTRAINT fk_customer_user_user_id FOREIGN KEY (user_id) REFERENCES usr (user_id);

ALTER TABLE ONLY ordr
    ADD CONSTRAINT fk_order_customer_customer_id FOREIGN KEY (customer_id) REFERENCES customer (customer_id);

ALTER TABLE ONLY item
    ADD CONSTRAINT fk_item_product_product_id FOREIGN KEY (product_id) REFERENCES product (product_id);

ALTER TABLE ONLY item
    ADD CONSTRAINT fk_item_order_order_id FOREIGN KEY (order_id) REFERENCES ordr (order_id);


-- inserts users

insert into usr (name) values ('Hendrika Bonde');
insert into usr (name) values ('Had Fateley');
insert into usr (name) values ('Mateo McCabe');
insert into usr (name) values ('Alwin Crittal');
insert into usr (name) values ('Brooke Meddows');
insert into usr (name) values ('Gloriane Bemrose');
insert into usr (name) values ('Melessa Goodding');
insert into usr (name) values ('Redford Aldcorne');
insert into usr (name) values ('Julissa Lohmeyer');
insert into usr (name) values ( 'Gaylord Dedney');

-- inserts products

insert into product (name, price) values ('Chicken - Whole Fryers', 895);
insert into product (name, price) values ('Grouper - Fresh', 373);
insert into product (name, price) values ('Wine - Ej Gallo Sonoma', 269);
insert into product (name, price) values ('Veal - Striploin', 200);
insert into product (name, price) values ('Wine - Sherry Dry Sack, William', 455);
insert into product (name, price) values ('Spring Roll Veg Mini', 800);
insert into product (name, price) values ('Chocolate - Sugar Free Semi Choc', 809);
insert into product (name, price) values ('Drambuie', 850);
insert into product (name, price) values ('Turkey - Breast, Boneless Sk On', 272);
insert into product (name, price) values ( 'Creme De Menthe Green', 132);
insert into product (name, price) values ( 'Juice - Orangina', 969);
insert into product (name, price) values ( 'Beef - Tenderloin Tails', 881);
insert into product (name, price) values ( 'Wine - White, Antinore Orvieto', 761);
insert into product (name, price) values ( 'Soda Water - Club Soda, 355 Ml', 824);
insert into product (name, price) values ( 'Carbonated Water - Orange', 279);
insert into product (name, price) values ( 'Cheese - Ermite Bleu', 521);
insert into product (name, price) values ( 'Chocolate - Pistoles, Lactee, Milk', 460);
insert into product (name, price) values ( 'Juice - Ocean Spray Kiwi', 255);
insert into product (name, price) values ( 'Ham - Cooked Italian', 467);
insert into product (name, price) values ( 'Water - San Pellegrino', 445);


-- make first three users as customers

insert into customer (user_id) values (1);
insert into customer (user_id) values (2);
insert into customer (user_id) values (3);

-- build orders for first customer

insert into ordr (date, customer_id) values (now(), 1);
insert into ordr (date, customer_id) values (now(), 2);
insert into ordr (date, customer_id) values (now(), 3);

-- products for first order

insert into item (count, price, order_id, product_id)
values (1, 895, 1, 1);
insert into item (count, price, order_id, product_id)
values (1, 373, 1, 2);
insert into item (count, price, order_id, product_id)
values (1, 269, 1, 3);

-- products for second order

insert into item (count, price, order_id, product_id)
values (2, 895, 2, 1);
insert into item (count, price, order_id, product_id)
values (2, 373, 2, 2);
insert into item (count, price, order_id, product_id)
values (2, 800, 2, 6);

-- products for third order

insert into item (count, price, order_id, product_id)
values (3, 895, 3, 1);
insert into item (count, price, order_id, product_id)
values (3, 269, 3, 3);
insert into item (count, price, order_id, product_id)
values (3, 850, 3, 9);