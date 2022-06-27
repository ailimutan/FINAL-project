USE pet;

DROP TABLE IF EXISTS owners;
DROP TABLE IF EXISTS pets;
DROP TABLE IF EXISTS features;
DROP TABLE IF EXISTS food_intake;
DROP TABLE IF EXISTS food;

CREATE TABLE owners (
  owner_pk int unsigned NOT NULL AUTO_INCREMENT,
  first_name varchar(45) NOT NULL, 
  last_name varchar(45) NOT NULL,
  phone varchar(20),
  PRIMARY KEY (owner_pk)
);

CREATE TABLE pets (
  pet_pk int unsigned NOT NULL AUTO_INCREMENT,
  owner_fk int unsigned NOT NULL,
  pet_name varchar(45) NOT NULL, 
  pet_type varchar(45) NOT NULL, 
  PRIMARY KEY (pet_pk),
  FOREIGN KEY (owner_fk) REFERENCES owners (owner_pk)
);

CREATE TABLE features (
  feature_pk int unsigned NOT NULL AUTO_INCREMENT,
  pet_fk int unsigned NOT NULL,
  color varchar(40) NOT NULL,
  breed varchar(40) NOT NULL,
  gender varchar(40) NOT NULL,
  weight int NOT NULL,
  PRIMARY KEY (feature_pk),
  FOREIGN KEY (pet_fk) REFERENCES pets (pet_pk),
  UNIQUE KEY (pet_fk)
);

CREATE TABLE food (
  food_pk int unsigned NOT NULL AUTO_INCREMENT,
  brand varchar(45) NOT NULL, 
  food_description varchar(45) NOT NULL,
  PRIMARY KEY (food_pk)
);

CREATE TABLE food_intake (
  food_intake_pk int unsigned NOT NULL AUTO_INCREMENT,
  pet_fk int unsigned NOT NULL,
  food_fk int unsigned NOT NULL,
  PRIMARY KEY (food_intake_pk),
  FOREIGN KEY (pet_fk) REFERENCES pets (pet_pk),
  FOREIGN KEY (food_fk) REFERENCES food (food_pk));