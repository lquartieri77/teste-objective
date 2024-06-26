CREATE TABLE IF NOT EXISTS conta (
    id SERIAL PRIMARY KEY,
    numero_conta NUMERIC(6),
    saldo NUMERIC(5,1)
);
