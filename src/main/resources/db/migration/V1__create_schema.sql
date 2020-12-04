CREATE TABLE IF NOT EXISTS employees (
    PRIMARY KEY(id),
        id  SERIAL NOT NULL,
    first_name VARCHAR(25),
    last_name VARCHAR(25),
    email VARCHAR(25),
    keywords VARCHAR(250)
);
INSERT INTO employees(first_name, last_name, email, keywords) VALUES('ahmed', 'moustafa','ahmed@email.com','lunch,dinner');