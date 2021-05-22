CREATE TABLE IF NOT EXISTS user(
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username varchar(100) NOT NULL,
    password varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS floor_level(
    floor_level_id INT AUTO_INCREMENT PRIMARY KEY,
    floor_level_name varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS furnishing(
    furnishing_id INT AUTO_INCREMENT PRIMARY KEY,
    furnishing_type varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS address(
    address_id INT AUTO_INCREMENT PRIMARY KEY,
    full_address varchar(350) NOT NULL,
    post_code INT DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS property (
   property_id INT AUTO_INCREMENT  PRIMARY KEY,
   property_name VARCHAR(250) NOT NULL,
   buildup_size INT NOT NULL,
   bedroom INT NOT NULL,
   bathroom INT NOT NULL,
   parking INT NOT NULL,
   floor_level_id INT NOT NULL,
   furnishing_id INT NOT NULL,
   address_id INT NOT NULL,
   status smallint NOT NULL ,
   created_by INT NOT NULL,
   creation_date datetime default curdate(),
   FOREIGN KEY (floor_level_id) REFERENCES floor_level(floor_level_id),
   FOREIGN KEY (furnishing_id) REFERENCES furnishing(furnishing_id),
   FOREIGN KEY (address_id) REFERENCES address(address_id),
   FOREIGN KEY (created_by) REFERENCES user(user_id)
);
