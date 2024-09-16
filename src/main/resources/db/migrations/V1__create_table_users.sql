CREATE TABLE IF NOT EXISTS users (
    id bigint NOT NULL auto_increment,
    name varchar(100),
    email varchar(100),
    password varchar(100),
    role varchar(50),
    created_at datetime,
    primary key (id)
    );

INSERT INTO users (name, email, password, role, created_at)
VALUES ('Jonh','john@example.com', '$2a$10$EoZ0FVOdZX7O0N5lzSPpfuPbIeuGhr/QRkxki5VxgKmT.Kn1iUS2u', 'ADMIN', '2024-09-15 12:00:00');