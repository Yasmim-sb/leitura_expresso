CREATE TABLE customer (
  id BIGSERIAL PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL CHECK (LENGTH(first_name) >= 3),
  last_name VARCHAR(255) NOT NULL CHECK (LENGTH(last_name) >= 3),
  sex VARCHAR(255),
  cpf VARCHAR(255) UNIQUE,
  birthdate DATE,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL CHECK (LENGTH(password) >= 6),
  active BOOLEAN DEFAULT TRUE,
);