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
DROP SEQUENCE TRANSACTIONS_SEQ;
CREATE SEQUENCE TRANSACTIONS_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE;


DROP TABLE customers;
DROP TABLE transactions;
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

CREATE TABLE transactions
(
     transaction_id NUMBER PRIMARY KEY,
     account_id NUMBER NOT NULL,
     transaction_date DATE NOT NULL,
     amount NUMBER(10, 2) NOT NULL,
     recipient_sender VARCHAR2(100),
     status VARCHAR2(20),
     description VARCHAR2(255),
     type VARCHAR2(255),
     FOREIGN KEY (account_id) REFERENCES accounts(id)
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

/*
 init transactions
 */
INSERT INTO transactions (transaction_id, account_id, transaction_date, amount, recipient_sender, status, description, type)
VALUES (TRANSACTIONS_SEQ.nextval, 101, TO_DATE('2024-11-01', 'YYYY-MM-DD'), 200.00, 'Amazon', 'Processing', 'Payment for order #1234', 'Shopping');
INSERT INTO transactions (transaction_id, account_id, transaction_date, amount, recipient_sender, status, description, type)
VALUES (TRANSACTIONS_SEQ.nextval, 101, TO_DATE('2024-11-15', 'YYYY-MM-DD'), 500.00, 'Rent Payment', 'Completed', 'Monthly rent for November', 'Bills');
INSERT INTO transactions (transaction_id, account_id, transaction_date, amount, recipient_sender, status, description, type)
VALUES (TRANSACTIONS_SEQ.nextval, 102, TO_DATE('2024-11-20', 'YYYY-MM-DD'), 75.00, 'Netflix', 'Completed', 'Monthly subscription fee', 'Bills');
INSERT INTO transactions (transaction_id, account_id, transaction_date, amount, recipient_sender, status, description, type)
VALUES (TRANSACTIONS_SEQ.nextval, 102, TO_DATE('2024-12-01', 'YYYY-MM-DD'), 1000.00, 'Employer', 'Failed', 'Salary for November', 'Salary');
INSERT INTO transactions (transaction_id, account_id, transaction_date, amount, recipient_sender, status, description, type)
VALUES (TRANSACTIONS_SEQ.nextval, 102, TO_DATE('2024-12-01', 'YYYY-MM-DD'), 1000.00, 'Employer', 'Completed', 'Salary for November', 'Salary');
INSERT INTO transactions (transaction_id, account_id, transaction_date, amount, recipient_sender, status, description, type)
VALUES (TRANSACTIONS_SEQ.nextval, 103, TO_DATE('2024-12-01', 'YYYY-MM-DD'), 150.00, 'Electricity Board', 'Completed', 'Utility bill payment', 'Bills');
INSERT INTO transactions (transaction_id, account_id, transaction_date, amount, recipient_sender, status, description, type)
VALUES (TRANSACTIONS_SEQ.nextval, 103, TO_DATE('2024-12-02', 'YYYY-MM-DD'), 250.00, 'XYZ Store', 'Initiated', 'Purchase of electronics', 'Shopping');
