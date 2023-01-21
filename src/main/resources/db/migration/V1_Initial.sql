
CREATE TABLE authorities(
                            username varchar(100) not null,
                            authority varchar(50) not null,
                            constraint fk_authorities_users foreign key(username) references users(username)
);

INSERT INTO users (email, firstname, enabled, lastname, password, username)
VALUES ('marcin@onet.pl', 'Marcin', true, 'Kolodziejczyk', 'pass123', 'marcin_kol');
INSERT INTO users (email, firstname, enabled, lastname, password, username)
VALUES ('michal@onet.pl', 'Michal', true, 'Langner', 'pass123', 'michal_lan');
INSERT INTO users (email, firstname, enabled, lastname, password, username)
VALUES ('jakub@onet.pl', 'Jakub', true, 'Lanoszka', 'pass123', 'jakub_lan');

