CREATE TABLE users(
                      user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
                      first_name VARCHAR(50) NOT NULL,
                      last_name VARCHAR(50) NOT NULL,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      token VARCHAR(255) NULL,
                      code INT NULL,
                      verified INT DEFAULT 0,
                      verified_at DATETIME,
                      created_at TIMESTAMP,
                      updated_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE accounts(
                         account_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         user_id INT,
                         account_number VARCHAR(100) NOT NULL,
                         account_name VARCHAR(50) NOT NULL,
                         account_type VARCHAR(50) NOT NULL,
                         balance DECIMAL(18, 2) DEFAULT 0.00,
                         created_at TIMESTAMP,
                         updated_at TIMESTAMP DEFAULT NOW(),
                         FOREIGN KEY(user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE transaction_history(
                                    transaction_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                    account_id INT,
                                    transaction_type VARCHAR(50) NOT NULL,
                                    amount DECIMAL(18, 2),
                                    source VARCHAR(50) NULL,
                                    status VARCHAR(50) NULL,
                                    reason_code VARCHAR(100) NULL,
                                    created_at TIMESTAMP,
                                    FOREIGN KEY(account_id) REFERENCES accounts(account_id) ON DELETE CASCADE
);

CREATE VIEW v_transaction_history
AS
SELECT
    t.transaction_id,
    a.account_id,
    u.user_id,
    t.transaction_type,
    t.amount,
    t.source,
    t.status,
    t.reason_code,
    t.created_at
FROM
    transaction_history AS t
        INNER JOIN
    accounts AS a
    ON
            t.account_id = a.account_id
        INNER JOIN
    users as u
    ON
            a.user_id = u.user_id;
