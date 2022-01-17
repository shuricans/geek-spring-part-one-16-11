drop database if exists lesson_6;

create database lesson_6
    with owner postgres;

create table usr
(
    user_id bigint       not null
        constraint usr_pkey
            primary key,
    name    varchar(255) not null
        constraint uk_mkjheedol1oe4evwyjw7ixpot
            unique
);

alter table usr
    owner to postgres;

create table product
(
    product_id bigint         not null
        constraint product_pkey
            primary key,
    name       varchar(255)   not null
        constraint uk_jmivyxk9rmgysrmsqw15lqr5b
            unique,
    price      numeric(19, 2) not null
);

alter table product
    owner to postgres;

create table ordr
(
    order_id bigint    not null
        constraint ordr_pkey
            primary key,
    date     timestamp not null,
    user_id  bigint    not null
        constraint fko11n1286wqi57ikc73l16p88y
            references usr
);

alter table ordr
    owner to postgres;

create table item
(
    item_id    bigint         not null
        constraint item_pkey
            primary key,
    count      integer        not null,
    price      numeric(19, 2) not null,
    order_id   bigint         not null
        constraint fkkty9tyryy8i41gvie1cjjwtgl
            references ordr,
    product_id bigint         not null
        constraint fkd1g72rrhgq1sf7m4uwfvuhlhe
            references product
);

alter table item
    owner to postgres;

create sequence items_sequence;

alter sequence items_sequence owner to postgres;

create sequence orders_sequence;

alter sequence orders_sequence owner to postgres;

create sequence products_sequence;

alter sequence products_sequence owner to postgres;

create sequence users_sequence;

alter sequence users_sequence owner to postgres;

insert into usr (user_id, name) values (1, 'Hendrika Bonde');
insert into usr (user_id, name) values (2, 'Had Fateley');
insert into usr (user_id, name) values (3, 'Mateo McCabe');
insert into usr (user_id, name) values (4, 'Alwin Crittal');
insert into usr (user_id, name) values (5, 'Brooke Meddows');
insert into usr (user_id, name) values (6, 'Gloriane Bemrose');
insert into usr (user_id, name) values (7, 'Melessa Goodding');
insert into usr (user_id, name) values (8, 'Redford Aldcorne');
insert into usr (user_id, name) values (9, 'Julissa Lohmeyer');
insert into usr (user_id, name) values (10, 'Gaylord Dedney');

insert into product (product_id, name, price) values (1, 'Chicken - Whole Fryers', 895);
insert into product (product_id, name, price) values (2, 'Grouper - Fresh', 373);
insert into product (product_id, name, price) values (3, 'Wine - Ej Gallo Sonoma', 269);
insert into product (product_id, name, price) values (4, 'Veal - Striploin', 200);
insert into product (product_id, name, price) values (5, 'Wine - Sherry Dry Sack, William', 455);
insert into product (product_id, name, price) values (6, 'Spring Roll Veg Mini', 800);
insert into product (product_id, name, price) values (7, 'Chocolate - Sugar Free Semi Choc', 809);
insert into product (product_id, name, price) values (8, 'Drambuie', 850);
insert into product (product_id, name, price) values (9, 'Turkey - Breast, Boneless Sk On', 272);
insert into product (product_id, name, price) values (10, 'Creme De Menthe Green', 132);
insert into product (product_id, name, price) values (11, 'Juice - Orangina', 969);
insert into product (product_id, name, price) values (12, 'Beef - Tenderloin Tails', 881);
insert into product (product_id, name, price) values (13, 'Wine - White, Antinore Orvieto', 761);
insert into product (product_id, name, price) values (14, 'Soda Water - Club Soda, 355 Ml', 824);
insert into product (product_id, name, price) values (15, 'Carbonated Water - Orange', 279);
insert into product (product_id, name, price) values (16, 'Cheese - Ermite Bleu', 521);
insert into product (product_id, name, price) values (17, 'Chocolate - Pistoles, Lactee, Milk', 460);
insert into product (product_id, name, price) values (18, 'Juice - Ocean Spray Kiwi', 255);
insert into product (product_id, name, price) values (19, 'Ham - Cooked Italian', 467);
insert into product (product_id, name, price) values (20, 'Water - San Pellegrino', 445);








