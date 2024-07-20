CREATE TABLE person (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    gender VARCHAR(255),
    age INT,
    identification VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(255)
);

CREATE TABLE client (
    id BIGSERIAL PRIMARY KEY,
    client_id VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    status VARCHAR(255),
    person_id BIGINT,
    FOREIGN KEY (person_id) REFERENCES person (id)
);

CREATE TABLE account (
    id BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(255) NOT NULL,
    account_type VARCHAR(255),
    initial_balance DOUBLE PRECISION,
    status VARCHAR(255),
    client_id BIGINT,
    FOREIGN KEY (client_id) REFERENCES client (id)
);

CREATE TABLE transaction (
    id BIGSERIAL PRIMARY KEY,
    account_id BIGINT,
    amount DOUBLE PRECISION,
    date TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES account (id)
);
