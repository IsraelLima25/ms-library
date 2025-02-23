CREATE TABLE if NOT EXISTS tbl_book(
    id serial PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    name_author VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    price NUMERIC(38, 2) NOT NULL
);