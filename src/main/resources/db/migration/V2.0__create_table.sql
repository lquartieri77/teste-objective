CREATE TABLE transacao (
    id SERIAL PRIMARY KEY,
    conta_id BIGINT NOT NULL,
    tipo_transacao CHAR(1) NOT NULL,
    valor NUMERIC(5,1)
);

ALTER TABLE transacao
ADD CONSTRAINT fk_conta
FOREIGN KEY (conta_id) REFERENCES conta (id);