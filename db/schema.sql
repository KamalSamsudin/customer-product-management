CREATE TABLE Customer (
id INT NOT NULL AUTO_INCREMENT,
first_name varchar(255) NOT NULL,
last_name varchar(255) NOT NULL,
office_email varchar(255),
personal_email varchar(255),
family_members varchar(255),
PRIMARY KEY (id)
);

CREATE TABLE Product (
id INT NOT NULL AUTO_INCREMENT,
book_title varchar(255) NOT NULL,
book_price varchar(255) NOT NULL,
book_quantity INT NOT NULL,
PRIMARY KEY (id)
);

