DROP TABLE IF EXISTS transaction CASCADE;
DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS customer CASCADE;
DROP TABLE IF EXISTS person CASCADE;

CREATE TABLE person (
    person_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(20),
    age INTEGER,
    identification VARCHAR(20) UNIQUE NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20)
);
CREATE TABLE customer (
    customer_id SERIAL PRIMARY KEY,
    person_id INTEGER NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    status BOOLEAN NOT NULL,
    FOREIGN KEY (person_id) REFERENCES person(person_id)
);
CREATE TABLE account (
    account_id BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(255) UNIQUE NOT NULL,
    account_type VARCHAR(255),
    initial_balance NUMERIC(19, 2),
    status BOOLEAN,
    customer_name VARCHAR(255),
    customer_id BIGINT
);
CREATE TABLE transaction (
    transaction_id BIGSERIAL PRIMARY KEY,
    transaction_date DATE NOT NULL,
    transaction_type VARCHAR(50) NOT NULL,
    amount NUMERIC(19, 2) NOT NULL,
    balance_before_transaction NUMERIC(19, 2) NOT NULL,
    balance_after_transaction NUMERIC(19, 2) NOT NULL,
    account_id BIGINT NOT NULL,
    CONSTRAINT fk_transaction_account FOREIGN KEY (account_id) REFERENCES account(account_id)
);

INSERT INTO person (name, address, phone, identification)
VALUES 
  ('Jose Lema', 'Otavalo sn y principal', '098254785', '0101'),
  ('Marianela Montalvo', 'Amazonas y NNUU', '097548965', '0102'),
  ('Juan Osorio', '13 junio y Equinoccial', '098874587', '0103');

INSERT INTO customer (person_id, password, status)
VALUES 
  (1, '1234', true),
  (2, '5678', true),
  (3, '1245', true);

INSERT INTO account (account_number, account_type, initial_balance, status, customer_name, customer_id)
VALUES
  ('478758', 'SAVING', 2000, true, 'Jose Lema', 1),
  ('225487', 'CHECKING', 100, true, 'Marianela Montalvo', 2),
  ('495878', 'SAVING', 0, true, 'Juan Osorio', 3),
  ('496825', 'SAVING', 540, true, 'Marianela Montalvo', 2);

INSERT INTO account (account_number, account_type, initial_balance, status, customer_name, customer_id)
VALUES ('585545', 'CHECKING', 1000, true, 'Jose Lema', 1);


INSERT INTO transaction (
  transaction_date, transaction_type, amount, balance_before_transaction, balance_after_transaction, account_id)
VALUES (
  '2025-08-01', 'WITHDRAWAL', -575, 2000, 1425, 1);

INSERT INTO transaction (
  transaction_date, transaction_type, amount, balance_before_transaction, balance_after_transaction, account_id)
VALUES (
  '2025-08-02', 'DEPOSIT', 600, 100, 700, 2);

INSERT INTO transaction (
  transaction_date, transaction_type, amount, balance_before_transaction, balance_after_transaction, account_id)
VALUES (
  '2025-08-02', 'DEPOSIT', 150, 0, 150, 3);

INSERT INTO transaction (
  transaction_date, transaction_type, amount, balance_before_transaction, balance_after_transaction, account_id)
VALUES (
  '2025-08-02', 'WITHDRAWAL', -540, 540, 0, 4);

