-- Owners

INSERT INTO owners (first_name, last_name, phone) VALUES('Agni', 'Van Altena', NULL);
INSERT INTO owners (first_name, last_name, phone) VALUES('Terzo', 'Kappel', '3289933774');
INSERT INTO owners (first_name, last_name, phone) VALUES('Businge', 'Foster', '3993773854');
INSERT INTO owners (first_name, last_name, phone) VALUES('Gisella', 'Ignatov', NULL);
INSERT INTO owners (first_name, last_name, phone) VALUES('Torø', 'Stern', '6473992610');
INSERT INTO owners (first_name, last_name, phone) VALUES('Kęstas', 'Huang', '2193556407');
INSERT INTO owners (first_name, last_name, phone) VALUES('Irene', 'Császár', NULL);
INSERT INTO owners (first_name, last_name, phone) VALUES('Anush', 'Tschida', '7669583995');
INSERT INTO owners (first_name, last_name, phone) VALUES('Aristaios', 'Scola', '1338462775');
INSERT INTO owners (first_name, last_name, phone) VALUES('Roth', 'Bev',NULL);

-- Food
INSERT INTO food (brand, food_description) VALUES('Lucy', 'Cat Food');
INSERT INTO food (brand, food_description) VALUES('Pidegree', 'Dog Food');
INSERT INTO food (brand, food_description) VALUES('Whiskas', 'Cat Food');


-- Pet
INSERT INTO pets (owner_fk, pet_name, pet_type) VALUES('1', 'Memeng','Cat');
INSERT INTO pets (owner_fk, pet_name, pet_type) VALUES('2', 'Blackie','Cat');
INSERT INTO pets (owner_fk, pet_name, pet_type) VALUES('3', 'Browny','Dog');

-- Food Intake
INSERT INTO food_intake (pet_fk, food_fk) VALUES('1', '1');
INSERT INTO food_intake (pet_fk, food_fk) VALUES('1', '2');
INSERT INTO food_intake (pet_fk, food_fk) VALUES('4', '1');

-- Pet Features
INSERT INTO features (pet_fk, color, breed, gender, weight) VALUES ('1', 'White', 'Persian Cat', 'Female', '10');
INSERT INTO features (pet_fk, color, breed, gender, weight) VALUES ('2', 'Black', 'Bengal Cat', 'Female', '10');
INSERT INTO features (pet_fk, color, breed, gender, weight) VALUES ('3', 'White', 'Dalmatian', 'Male', '20');
