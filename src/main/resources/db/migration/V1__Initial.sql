
create table category (
                          category_id integer not null auto_increment,
                          category_name varchar(255) not null,
                          image varchar(255),
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
                         primary key (id)

) engine=InnoDB;
create table user_address (
                              id integer not null auto_increment,
                              building_number varchar(5) not null,
                              city varchar(30) not null,
                              flat_number varchar(5),
                              post_code varchar(6) not null,
                              street varchar(30) not null,
                              primary key (id)
) engine=InnoDB;

create table user_addresses (
                                address_id integer not null,
                                user_id integer not null
) engine=InnoDB;

create table users (
                       id integer not null auto_increment,
                       email varchar(30) not null,
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

alter table user_addresses
    add constraint FKs3hhkal8hpn9wu8m9qp04f4tn
        foreign key (user_id)
            references user_address (id);

alter table user_addresses
    add constraint FKpw9ob333uvdenpo8k7gyy4w0v
        foreign key (address_id)
            references users (id);

Create table authorities (username varchar(100) , authority varchar(50));

INSERT INTO authorities (username, authority)
VALUES ('marcin_kol', 'Admin');
INSERT INTO authorities (username, authority)
VALUES ('michal_lan', 'Admin');
INSERT INTO authorities (username, authority)
VALUES ('jakub_lan', 'Admin');


INSERT INTO users (email, firstname, enabled, lastname, password, username)
VALUES ('marcin@onet.pl', 'Marcin', true, 'Kolodziejczyk', '$2a$10$wvVbmWzuL4Xv43TkhzRWOeHHxBQhkJBLpLJnWyPZXt9mvdWAKA4NS', 'marcin_kol');
INSERT INTO users (email, firstname, enabled, lastname, password, username)
VALUES ('michal@onet.pl', 'Michal', true, 'Langner', '$2a$10$wvVbmWzuL4Xv43TkhzRWOeHHxBQhkJBLpLJnWyPZXt9mvdWAKA4NS', 'michal_lan');
INSERT INTO users (email, firstname, enabled, lastname, password, username)
VALUES ('jakub@onet.pl', 'Jakub', true, 'Lanoszka', '$2a$10$wvVbmWzuL4Xv43TkhzRWOeHHxBQhkJBLpLJnWyPZXt9mvdWAKA4NS', 'jakub_lan');


INSERT INTO category(category_name, image)
values ('House', "/images/s2.png");
INSERT INTO category(category_name, image)
values ('Sport', "/images/s1.png");
INSERT INTO category(category_name, image)
values ('Electronic', "/images/s3.png");

INSERT INTO product(amount, name, price, category_id, image) values
                                                                 ('5', "Camera" , 160, 3, "/images/kategorie/electronic/aparat.png"),
                                                                 ('3', "Dron", 250, 3, "/images/kategorie/electronic/dron.png"),
                                                                 ('13', "Laptop", 4300, 3, "/images/kategorie/electronic/laptop.png"),
                                                                 ('8', "Vacum", 300, 3, "/images/kategorie/electronic/odkurzacz.png" ),
                                                                 ('4', "Oven", 1700.50, 3, "/images/kategorie/electronic/piekarnik.png"),
                                                                 ('25', "Powerbank", 50, 3, "/images/kategorie/electronic/powerbank.png"),
                                                                 ('15', "Headphones", 110.75, 3, "/images/kategorie/electronic/słuchawki.png"),
                                                                 ('7', "Telephone", 1150.60, 3, "/images/kategorie/electronic/telefon.png"),
                                                                 ('10', "TV", 999, 3, "/images/kategorie/electronic/tv.png"),
                                                                 ('17', "Dishwasher", 1110, 3, "/images/kategorie/electronic/zmywarka.png");
INSERT INTO product(amount, name, price, category_id, image) values
                                                                 ('52', "Carpet", 120.5, 1, "/images/kategorie/house/dywan.png"),
                                                                 ('22', "Chair", 95, 1, "/images/kategorie/house/krzeslo.png"),
                                                                 ('10', "Lamp", 280, 1, "/images/kategorie/house/lampa.png"),
                                                                 ('5', "Lamp", 450, 1, "/images/kategorie/house/lampa2.png"),
                                                                 ('17', "Mattress", 720, 1, "/images/kategorie/house/materac.png"),
                                                                 ('100', "Panels", 55.35, 1, "/images/kategorie/house/panele.png"),
                                                                 ('75', "Bedding", 80, 1, "/images/kategorie/house/posciel.png"),
                                                                 ('30', "Table", 400, 1, "/images/kategorie/house/stol.png"),
                                                                 ('14', "Table", 710.50, 1, "/images/kategorie/house/stol2.png"),
                                                                 ('80', "Wallpaper", 50.45, 1, "/images/kategorie/house/tapeta.png");
INSERT INTO product(amount, name, price, category_id, image) values
                                                                 ('9', "Treadmill", 1300.50, 2, "/images/kategorie/sport/bieznia.png"),
                                                                 ('22', "Gateaway", 200.99, 2, "/images/kategorie/sport/bramka.png"),
                                                                 ('40', "Dumbbells", 99.99, 2, "/images/kategorie/sport/hantle.png"),
                                                                 ('90', "Roller skates", 270, 2, "/images/kategorie/sport/lyzworolki.png"),
                                                                 ('150', "Mat", 50, 2, "/images/kategorie/sport/mata.png" ),
                                                                 ('6', "Tent", 430, 2, "/images/kategorie/sport/namiot.png"),
                                                                 ('80', "Ball", 140.50, 2, "/images/kategorie/sport/pilka.png"),
                                                                 ('9', "Bike", 1099, 2, "/images/kategorie/sport/rower.png"),
                                                                 ('4', "Fishing Rod", 600, 2, "/images/kategorie/sport/wedka.png"),
                                                                 ('27', "Stationary Bike", 999, 2, "/images/kategorie/sport/rower-stacjonarny.png"),
                                                                 ('11', "Stepper", 160.30, 2, "/images/kategorie/sport/stepper.png");