DROP DATABASE IF EXISTS lesson_12 WITH (FORCE);
CREATE DATABASE lesson_12 ENCODING = 'UTF8';

ALTER DATABASE lesson_12 OWNER TO postgres;

-- Roles

CREATE SEQUENCE roles_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE roles_sequence
    OWNER TO postgres;

create table roles
(
    role_id bigint       not null default nextval('roles_sequence') primary key,
    name    varchar(255) not null
        constraint uk_role_name unique
);

alter table roles
    owner to postgres;


-- Users

CREATE SEQUENCE users_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE users_sequence
    OWNER TO postgres;

create table users
(
    user_id  bigint       not null default nextval('users_sequence') primary key,
    login    varchar(255) not null
        constraint uk_user_login unique,
    password varchar(255) not null
);

alter table users
    owner to postgres;


-- Users_Roles

create table users_roles
(
    user_id bigint not null
        constraint fk_users_roles_user references users,
    role_id bigint not null
        constraint fk_users_roles_role references roles,
    primary key (user_id, role_id)
);

alter table users_roles
    owner to postgres;


-- Category

CREATE SEQUENCE categories_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE categories_sequence
    OWNER TO postgres;

CREATE TABLE category
(
    category_id BIGINT       NOT NULL DEFAULT nextval('categories_sequence') PRIMARY KEY,
    name        VARCHAR(255) NOT NULL UNIQUE
);

ALTER TABLE category
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
    product_id  bigint                 NOT NULL DEFAULT nextval('products_sequence') PRIMARY KEY,
    name        character varying(255) NOT NULL UNIQUE,
    description varchar(255),
    price       numeric(19, 2)         NOT NULL,
    category_id bigint
        constraint fk_product_category_category_id references category
);

ALTER TABLE product
    OWNER TO postgres;

-- insert 4 categories

insert into category (name)
values ('Pasta & risotto'),
       ('Salad'),
       ('Soup'),
       ('Pizza');

-- insert 20 products

insert into product (name, description, price, category_id)
values ('Liners - Baking Cups', 'fusce consequat', 326.43, 3);
insert into product (name, description, price, category_id)
values ('Irish Cream - Baileys', 'ligula pellentesque ultrices', 635.69, 2);
insert into product (name, description, price, category_id)
values ('Chickensplit Half', 'pellentesque', 415.37, 4);
insert into product (name, description, price, category_id)
values ('Mince Meat - Filling', 'vestibulum eget', 872.16, 1);
insert into product (name, description, price, category_id)
values ('Table Cloth 120 Round White', 'fusce consequat', 492.4, 3);
insert into product (name, description, price, category_id)
values ('Celery', 'sodales sed', 249.47, 4);
insert into product (name, description, price, category_id)
values ('Pastry - Apple Large', 'ultrices', 313.38, 2);
insert into product (name, description, price, category_id)
values ('Kellogs Special K Cereal', 'nonummy', 753.38, 2);
insert into product (name, description, price, category_id)
values ('Flour - Teff', 'mi', 739.64, 4);
insert into product (name, description, price, category_id)
values ('Juice Peach Nectar', 'nisl venenatis', 474.97, 2);
insert into product (name, description, price, category_id)
values ('Rabbit - Frozen', 'ultrices libero', 481.96, 2);
insert into product (name, description, price, category_id)
values ('Herb Du Provence - Primerba', 'dapibus augue vel', 935.05, 3);
insert into product (name, description, price, category_id)
values ('Sugar - Cubes', 'rutrum', 239.21, 2);
insert into product (name, description, price, category_id)
values ('Nut - Walnut, Chopped', 'praesent lectus vestibulum', 446.0, 1);
insert into product (name, description, price, category_id)
values ('Onions - Cippolini', 'nulla ultrices aliquet', 529.37, 2);
insert into product (name, description, price, category_id)
values ('Drambuie', 'consequat', 694.61, 2);
insert into product (name, description, price, category_id)
values ('Danishes - Mini Cheese', 'fermentum justo nec', 295.16, 3);
insert into product (name, description, price, category_id)
values ('Amarula Cream', 'turpis donec posuere', 302.0, 2);
insert into product (name, description, price, category_id)
values ('Pasta - Fusili, Dry', 'curae', 214.0, 1);
insert into product (name, description, price, category_id)
values ('Mackerel Whole Fresh', 'magnis', 603.66, 1);


-- insert roles

insert into roles(name)
values ('ROLE_MANAGER'),
       ('ROLE_ADMIN'),
       ('ROLE_SUPER_ADMIN');

-- insert super admin

insert into users(login, password)
values ('super_admin', '$2a$10$O5.7glPyPxWXe2f4c0E.LeOj//sAkhObGrJLxslqsxRj0chwIlfDC');

insert into users_roles(user_id, role_id)
values (1, 2),
       (1, 3);


