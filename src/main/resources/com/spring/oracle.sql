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
DROP TABLE accounts;
DROP TABLE transactions;

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
 making a skeleton transactions table for now
 */
-- Create Account Table (just put here for reference)
-- CREATE TABLE accounts
-- (
--      account_id NUMBER PRIMARY KEY, -- Unique identifier for each account
--      account_name VARCHAR2(100),   -- Name of the account holder
--      account_type VARCHAR2(50),    -- Type of account (e.g., Savings, Checking)
--      created_date DATE DEFAULT SYSDATE -- Date when the account was created
-- );

-- Create Transaction Table
CREATE TABLE transactions
(
     transaction_id NUMBER PRIMARY KEY, -- Unique identifier for each transaction
     account_id NUMBER NOT NULL,        -- Links to Account table
     transaction_date DATE NOT NULL,    -- Date of the transaction
     amount NUMBER(10, 2) NOT NULL,     -- Transaction amount
     recipient_sender VARCHAR2(100),   -- Recipient or sender of the transaction
     status VARCHAR2(20),              -- Status of the transaction (e.g., initiated, completed, failed)
     description VARCHAR2(255),        -- Optional description of the transaction
     FOREIGN KEY (account_id) REFERENCES accounts(id) -- Foreign key constraint
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
-- Insert transactions into Transaction table
INSERT INTO transactions (transaction_id, account_id, transaction_date, amount, recipient_sender, status, description)
VALUES (101, 1, TO_DATE('2024-11-01', 'YYYY-MM-DD'), 200.00, 'Amazon', 'Completed', 'Payment for order #1234');
INSERT INTO transactions (transaction_id, account_id, transaction_date, amount, recipient_sender, status, description)
VALUES (102, 1, TO_DATE('2024-11-15', 'YYYY-MM-DD'), 500.00, 'Rent Payment', 'Completed', 'Monthly rent for November');
INSERT INTO transactions (transaction_id, account_id, transaction_date, amount, recipient_sender, status, description)
VALUES (103, 2, TO_DATE('2024-11-20', 'YYYY-MM-DD'), 75.00, 'Netflix', 'Completed', 'Monthly subscription fee');
INSERT INTO transactions (transaction_id, account_id, transaction_date, amount, recipient_sender, status, description)
VALUES (104, 2, TO_DATE('2024-12-01', 'YYYY-MM-DD'), 1000.00, 'Employer', 'Completed', 'Salary for November');
INSERT INTO transactions (transaction_id, account_id, transaction_date, amount, recipient_sender, status, description)
VALUES (105, 3, TO_DATE('2024-12-01', 'YYYY-MM-DD'), 150.00, 'Electricity Board', 'Completed', 'Utility bill payment');
INSERT INTO transactions (transaction_id, account_id, transaction_date, amount, recipient_sender, status, description)
VALUES (106, 3, TO_DATE('2024-12-02', 'YYYY-MM-DD'), 250.00, 'XYZ Store', 'Pending', 'Purchase of electronics');
