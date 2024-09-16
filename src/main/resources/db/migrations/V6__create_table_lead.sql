CREATE TABLE lead (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    dtInteracao DATE NOT NULL,
    historico_interesse_id BIGINT,

    FOREIGN KEY (historico_interesse_id) REFERENCES HistoricoInteresse(id),

    CONSTRAINT chk_nome CHECK (LENGTH(nome) BETWEEN 3 AND 200),
    CONSTRAINT chk_telefone CHECK (LENGTH(telefone) BETWEEN 10 AND 11),
    CONSTRAINT chk_dtInteracao CHECK (dtInteracao <= CURRENT_DATE)
);
