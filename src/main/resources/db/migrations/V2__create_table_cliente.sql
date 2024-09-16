CREATE TABLE IF NOT EXISTS cliente (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     nome VARCHAR(200) NOT NULL,
     email VARCHAR(255) NOT NULL,
     telefone VARCHAR(11) NOT NULL,
     dtaNascimento DATE NOT NULL,
     dtaCadastro DATE NOT NULL,
     segmentoMercado VARCHAR(50) NOT NULL,
     dtaUltimaInteracao DATE NOT NULL,
     endereco_id BIGINT,
     historico_interesse_id BIGINT,

     FOREIGN KEY (endereco_id) REFERENCES Endereco(id),
     FOREIGN KEY (historico_interesse_id) REFERENCES HistoricoInteresse(id),

     CONSTRAINT chk_telefone CHECK (LENGTH(telefone) BETWEEN 10 AND 11),
     CONSTRAINT chk_nome CHECK (LENGTH(nome) BETWEEN 3 AND 200),
     CONSTRAINT chk_segmento CHECK (LENGTH(segmentoMercado) BETWEEN 3 AND 50),
     CONSTRAINT chk_dataNascimento CHECK (dtaNascimento < CURRENT_DATE),
     CONSTRAINT chk_dataCadastro CHECK (dtaCadastro <= CURRENT_DATE),
     CONSTRAINT chk_dataUltimaInteracao CHECK (dtaUltimaInteracao <= CURRENT_DATE)
);