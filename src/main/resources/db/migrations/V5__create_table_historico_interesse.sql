CREATE TABLE historicoInteresse (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    interesse VARCHAR(500) NOT NULL,
    dtInteracao DATE NOT NULL,

    CONSTRAINT chk_interesse CHECK (LENGTH(interesse) BETWEEN 5 AND 500),
    CONSTRAINT chk_dtInteracao CHECK (dtInteracao <= CURRENT_DATE)
);