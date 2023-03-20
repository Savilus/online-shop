create table category (
                          category_id integer not null auto_increment,
                          category_name varchar(255) not null,
                          image varchar(255),
                          enabled bit not null,
                          primary key (category_id)
) engine=InnoDB;



create table orders (
                        id integer not null auto_increment,
                        value decimal(38,2),
                        primary key (id)
) engine=InnoDB;


create table product (
                         id integer not null auto_increment,
                         amount integer not null,
                         image varchar(255) not null,
                         name varchar(135) not null,
                         price decimal(38,2) not null,
                         category_id integer not null,
                         order_id integer,
                         enabled bit not null,
                         primary key (id)

) engine=InnoDB;




create table users (
                       id integer not null auto_increment,
                       email varchar(100) not null,
                       enabled bit not null,
                       firstname varchar(30) not null,
                       lastname varchar(45) not null,
                       password varchar(100) not null,
                       username varchar(30) not null,
                       primary key (id)
) engine=InnoDB;


alter table product
    add constraint FK1mtsbur82frn64de7balymq9s
        foreign key (category_id)
            references category (category_id);


alter table product
    add constraint FK18j7hot76crqfb6x6xn7mlxt6
        foreign key (order_id)
            references orders (id);


Create table authorities (username varchar(100) , authority varchar(50));

INSERT INTO authorities (username, authority)
VALUES ('superAdminDef', 'SUPER_ADMIN');
INSERT INTO authorities (username, authority)
VALUES ('adminDef', 'ADMIN');
INSERT INTO authorities (username, authority)
VALUES ('userDef', 'USER');


INSERT INTO users (email, firstname, enabled, lastname, password, username)
VALUES ('superadmindef@onet.pl', 'Tom', true, 'Jones', '$2a$10$H1WEkjK3i3aibtlAYs1zCO6Cawjk3ll38vhUWLe6H3.2f2NDNeCX.', 'superAdminDef');
INSERT INTO users (email, firstname, enabled, lastname, password, username)
VALUES ('admindef@onet.pl', 'Tom', true, 'Jones', '$2a$10$H1WEkjK3i3aibtlAYs1zCO6Cawjk3ll38vhUWLe6H3.2f2NDNeCX.', 'adminDef');
INSERT INTO users (email, firstname, enabled, lastname, password, username)
VALUES ('user@onet.pl', 'Tom', true, 'Jones', '$2a$10$H1WEkjK3i3aibtlAYs1zCO6Cawjk3ll38vhUWLe6H3.2f2NDNeCX.', 'userDef');


INSERT INTO category(category_name, image, enabled)
values ('House', "/images/s2.png", true);
INSERT INTO category(category_name, image, enabled)
values ('Sport', "/images/s1.png", true);
INSERT INTO category(category_name, image, enabled)
values ('Electronic', "/images/s3.png", true);

INSERT INTO product(amount, name, price, category_id, image, enabled) values
                                                                 ('5', "Camera" , 160, 3, "/images/kategorie/electronic/aparat.png", true),
                                                                 ('3', "Dron", 250, 3, "/images/kategorie/electronic/dron.png", true),
                                                                 ('13', "Laptop", 4300, 3, "/images/kategorie/electronic/laptop.png", true),
                                                                 ('8', "Vacum", 300, 3, "/images/kategorie/electronic/odkurzacz.png", true ),
                                                                 ('4', "Oven", 1700.50, 3, "/images/kategorie/electronic/piekarnik.png", true),
                                                                 ('25', "Powerbank", 50, 3, "/images/kategorie/electronic/powerbank.png", true),
                                                                 ('15', "Headphones", 110.75, 3, "/images/kategorie/electronic/słuchawki.png", true),
                                                                 ('7', "Telephone", 1150.60, 3, "/images/kategorie/electronic/telefon.png", true),
                                                                 ('10', "TV", 999, 3, "/images/kategorie/electronic/tv.png", true),
                                                                 ('17', "Dishwasher", 1110, 3, "/images/kategorie/electronic/zmywarka.png", true);
INSERT INTO product(amount, name, price, category_id, image, enabled) values
                                                                 ('52', "Carpet", 120.5, 1, "/images/kategorie/house/dywan.png", true),
                                                                 ('22', "Chair", 95, 1, "/images/kategorie/house/krzeslo.png", true),
                                                                 ('10', "Lamp", 280, 1, "/images/kategorie/house/lampa.png", true),
                                                                 ('5', "Lamp", 450, 1, "/images/kategorie/house/lampa2.png", true),
                                                                 ('17', "Mattress", 720, 1, "/images/kategorie/house/materac.png", true),
                                                                 ('100', "Panels", 55.35, 1, "/images/kategorie/house/panele.png", true),
                                                                 ('75', "Bedding", 80, 1, "/images/kategorie/house/posciel.png", true),
                                                                 ('30', "Table", 400, 1, "/images/kategorie/house/stol.png", true),
                                                                 ('14', "Table", 710.50, 1, "/images/kategorie/house/stol2.png", true),
                                                                 ('80', "Wallpaper", 50.45, 1, "/images/kategorie/house/tapeta.png", true);
INSERT INTO product(amount, name, price, category_id, image, enabled) values
                                                                 ('9', "Treadmill", 1300.50, 2, "/images/kategorie/sport/bieznia.png", true),
                                                                 ('22', "Gateaway", 200.99, 2, "/images/kategorie/sport/bramka.png", true),
                                                                 ('40', "Dumbbells", 99.99, 2, "/images/kategorie/sport/hantle.png", true),
                                                                 ('90', "Roller skates", 270, 2, "/images/kategorie/sport/lyzworolki.png", true),
                                                                 ('150', "Mat", 50, 2, "/images/kategorie/sport/mata.png", true ),
                                                                 ('6', "Tent", 430, 2, "/images/kategorie/sport/namiot.png", true),
                                                                 ('80', "Ball", 140.50, 2, "/images/kategorie/sport/pilka.png", true),
                                                                 ('9', "Bike", 1099, 2, "/images/kategorie/sport/rower.png", true),
                                                                 ('4', "Fishing Rod", 600, 2, "/images/kategorie/sport/wedka.png", true),
                                                                 ('27', "Stationary Bike", 999, 2, "/images/kategorie/sport/rower-stacjonarny.png", true),
                                                                 ('11', "Stepper", 160.30, 2, "/images/kategorie/sport/stepper.png", true);