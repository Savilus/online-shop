
CREATE TABLE authorities(username varchar(100) not null, authority varchar(50) not null);

INSERT INTO users (email, firstname, enabled, lastname, password, username)
VALUES ('marcin@onet.pl', 'Marcin', true, 'Kolodziejczyk', '{noop}pass123', 'marcin_kol');
INSERT INTO users (email, firstname, enabled, lastname, password, username)
VALUES ('michal@onet.pl', 'Michal', true, 'Langner', '{noop}pass123', 'michal_lan');
INSERT INTO users (email, firstname, enabled, lastname, password, username)
VALUES ('jakub@onet.pl', 'Jakub', true, 'Lanoszka', '{noop}pass123', 'jakub_lan');

