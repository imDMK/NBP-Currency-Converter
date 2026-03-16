CREATE TABLE computers (
                           id BIGSERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL,
                           accounting_date DATE NOT NULL,
                           cost_usd DECIMAL(10,2) NOT NULL,
                           cost_pln DECIMAL(10,2) NOT NULL
);