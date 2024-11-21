DROP SEQUENCE CUSTOMERS_SEQ;
CREATE SEQUENCE CUSTOMERS_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
DROP SEQUENCE ACCOUNTS_SEQ;
CREATE SEQUENCE ACCOUNTS_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE;
DROP TABLE customers;
DROP TABLE accounts;

CREATE TABLE accounts
(
    id              NUMBER PRIMARY KEY,
    balance         BINARY_DOUBLE,
    withdraw_limit  BINARY_DOUBLE,
    overdraft_limit BINARY_DOUBLE
);

CREATE TABLE customers
(
    id       NUMBER PRIMARY KEY,
    name     VARCHAR(30),
    username VARCHAR(30),
    email    VARCHAR(100),
    password VARCHAR(50),
    account  NUMBER NOT NULL,
    CONSTRAINT fk_account FOREIGN KEY (account) REFERENCES accounts (id),
    CONSTRAINT email_format_check
        CHECK (REGEXP_LIKE(email, '^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$'))
);

/*
 init accounts
 */
INSERT INTO accounts (id, balance, withdraw_limit, overdraft_limit)
VALUES (101, 1500.00, 500.00, 200.00);
INSERT INTO accounts (id, balance, withdraw_limit, overdraft_limit)
VALUES (102, 3000.50, 1000.00, 500.00);
INSERT INTO accounts (id, balance, withdraw_limit, overdraft_limit)
VALUES (103, 2500.75, 800.00, 300.00);
INSERT INTO accounts (id, balance, withdraw_limit, overdraft_limit)
VALUES (104, 1200.00, 400.00, 150.00);
INSERT INTO accounts (id, balance, withdraw_limit, overdraft_limit)
VALUES (105, 5000.00, 2000.00, 1000.00);

/*
 init customers
 */
INSERT INTO customers (id, name, username, email, password, account)
VALUES (CUSTOMERS_SEQ.nextval, 'John Doe', 'johndoe', 'john.doe@example.com', 'password123', '101');
INSERT INTO customers (id, name, username, email, password, account)
VALUES (CUSTOMERS_SEQ.nextval, 'Jane Smith', 'janesmith', 'jane.smith@example.com', 'securepass', '102');
INSERT INTO customers (id, name, username, email, password, account)
VALUES (CUSTOMERS_SEQ.nextval, 'Alice Johnson', 'alicej', 'alice.j@example.com', 'alice1234', '103');
INSERT INTO customers (id, name, username, email, password, account)
VALUES (CUSTOMERS_SEQ.nextval, 'Bob Brown', 'bobb', 'bob.brown@example.com', 'bobsecure', '104');
INSERT INTO customers (id, name, username, email, password, account)
VALUES (CUSTOMERS_SEQ.nextval, 'Charlie Green', 'charlieg', 'charlie.g@example.com', 'greenpass', '105');