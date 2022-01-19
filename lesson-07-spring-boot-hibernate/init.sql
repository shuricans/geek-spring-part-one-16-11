DROP DATABASE IF EXISTS lesson_7 WITH (FORCE);
CREATE DATABASE lesson_7 ENCODING = 'UTF8';

ALTER DATABASE lesson_7 OWNER TO postgres;

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
    product_id  bigint                 NOT NULL DEFAULT nextval('products_sequence') PRIMARY KEY,
    name        character varying(255) NOT NULL UNIQUE,
    description varchar(255),
    price       numeric(19, 2)         NOT NULL
);

ALTER TABLE product
    OWNER TO postgres;


-- insert 20 products

insert into product (name, description, price) values ('Syrup - Monin - Blue Curacao', 'sapien cursus vestibulum', 884);
insert into product (name, description, price) values ('Fruit Mix - Light', 'tempus vivamus', 908);
insert into product (name, description, price) values ('Pasta - Fettuccine, Egg, Fresh', 'cursus vestibulum proin eu mi', 820);
insert into product (name, description, price) values ('Broom - Angled', 'vitae consectetuer eget', 335);
insert into product (name, description, price) values ('Butter Balls Salted', 'sapien varius ut blandit', 940);
insert into product (name, description, price) values ('Napkin - Beverge, White 2 - Ply', 'dolor quis odio consequat', 313);
insert into product (name, description, price) values ('Soup - Knorr, Ministrone', 'at feugiat non pretium quis', 860);
insert into product (name, description, price) values ('Sauce - Apple, Unsweetened', 'ultrices vel augue', 409);
insert into product (name, description, price) values ('True - Vue Containers', 'neque aenean auctor gravida', 619);
insert into product (name, description, price) values ('Peas - Pigeon, Dry', 'interdum eu tincidunt in', 568);
insert into product (name, description, price) values ('Coffee Decaf Colombian', 'erat tortor sollicitudin mi sit', 461);
insert into product (name, description, price) values ('Tomato - Plum With Basil', 'viverra eget congue', 837);
insert into product (name, description, price) values ('Creamers - 10%', 'a ipsum integer a', 194);
insert into product (name, description, price) values ('Coffee - Frthy Coffee Crisp', 'sapien non mi integer', 153);
insert into product (name, description, price) values ('Tart Shells - Savory, 3', 'nullam porttitor lacus', 242);
insert into product (name, description, price) values ('Grapefruit - White', 'ultrices phasellus', 170);
insert into product (name, description, price) values ('Crackers - Graham', 'augue vestibulum', 645);
insert into product (name, description, price) values ('Danishes - Mini Cheese', 'sapien arcu', 848);
insert into product (name, description, price) values ('Tea Leaves - Oolong', 'blandit mi', 453);
insert into product (name, description, price) values ('Bagel - Whole White Sesame', 'vestibulum ante ipsum primis in', 307);



