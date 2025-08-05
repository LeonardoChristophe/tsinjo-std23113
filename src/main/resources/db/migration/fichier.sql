CREATE TABLE donor (
                       email VARCHAR(255) PRIMARY KEY,
                       full_name VARCHAR(255) NOT NULL
);

CREATE TABLE beneficiary (
                             email VARCHAR(255) PRIMARY KEY,
                             full_name VARCHAR(255) NOT NULL
);

CREATE TABLE payment (
                         id VARCHAR(255) PRIMARY KEY,
                         payment_method VARCHAR(255) NOT NULL,
                         amount DECIMAL(15,2) NOT NULL,
                         date TIMESTAMP NOT NULL,
                         status VARCHAR(50) NOT NULL
);

CREATE TABLE donation (
                          id VARCHAR(255) PRIMARY KEY,
                          donor_email VARCHAR(255) REFERENCES donor(email),
                          payment_id VARCHAR(255) REFERENCES payment(id)
);

CREATE TABLE help (
                      id VARCHAR(255) PRIMARY KEY,
                      beneficiary_email VARCHAR(255) REFERENCES beneficiary(email),
                      payment_id VARCHAR(255) REFERENCES payment(id),
                      accident_description TEXT NOT NULL
);