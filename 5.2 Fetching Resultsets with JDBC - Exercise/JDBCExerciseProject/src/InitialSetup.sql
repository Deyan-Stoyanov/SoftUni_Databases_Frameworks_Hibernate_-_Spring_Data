CREATE DATABASE IF NOT EXISTS minions;
USE minions;
CREATE TABLE towns(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50),
  country VARCHAR(50)
);
CREATE TABLE minions(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50),
  age INT(11),
  town_id INT(50),
  CONSTRAINT fk_minions_towns FOREIGN KEY(town_id) REFERENCES towns(id)
);
CREATE TABLE villains(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50),
  evilness_factor VARCHAR(50)
);
CREATE TABLE minions_villains(
  minion_id INT(11),
  villain_id INT(11),
  CONSTRAINT pk_minions_villains PRIMARY KEY (minion_id, villain_id),
  CONSTRAINT fk_minions_villains_minions FOREIGN KEY (minion_id) REFERENCES minions(id),
  CONSTRAINT fk_minions_villains_villains FOREIGN KEY (villain_id) REFERENCES villains(id)
);
INSERT INTO towns(name, country) VALUES ('Sofia', 'Bulgaria'),('Plovdiv', 'Bulgaria'),('Berlin', 'Germany'),('Pazardjik', 'Bulgaria'),('Belgrade', 'Serbia');
INSERT INTO minions(name, age, town_id) VALUES ('Pesho', 23, 1), ('Gosho', 5, 5), ('Ivan', 55, 4), ('asd', 22, 2), ('asdads', 2, 4);
INSERT INTO villains(name, evilness_factor) VALUES ('Gosho', 'good'), ('Pesho', 'bad' ), ('Ivan', 'evil'), ('adadasd', 'super evil'), ('asdasdasd', 'evil');
INSERT INTO minions_villains(minion_id, villain_id) VALUES (1, 5), (2, 2), (3, 2), (5, 1), (4, 1), (5, 2), (4, 2), (1, 1);