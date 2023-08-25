CREATE DATABASE product;

\c product;

CREATE TABLE category (
    id bigserial PRIMARY KEY,
    nome varchar(100) NOT NULL,
    dt_cadastro timestamp NOT NULL,
    ativo boolean NOT NULL DEFAULT true
);

CREATE TABLE brands (
    id bigserial PRIMARY KEY,
    nome varchar(100) NOT NULL,
    dt_cadastro timestamp NOT NULL,
    ativo boolean NOT NULL DEFAULT true
);

CREATE TABLE products (
    id bigserial PRIMARY KEY,
    nome varchar(50) NOT NULL,
    category_id bigint REFERENCES category(id),
    descricao varchar(100) NOT NULL,
    marca_id bigint REFERENCES brands(id),
    preco decimal(9,2) NOT NULL,
    quantidade int NOT NULL,
    dt_cadastro timestamp NOT NULL,
    ativo boolean NOT NULL DEFAULT true
);

INSERT INTO category (nome, ativo, dt_cadastro)
VALUES
    ('Eletronicos', true, current_timestamp),
    ('Eletrodomesticos', true, current_timestamp),
    ('Roupas', true, current_timestamp),
    ('Calcados', true, current_timestamp);

INSERT INTO brands (nome, ativo, dt_cadastro)
VALUES
    ('Apple', true, current_timestamp),
    ('Dell', true, current_timestamp),
    ('JBL', true, current_timestamp),
    ('BaristaPro', true, current_timestamp),
    ('CookMaster', true, current_timestamp),
    ('RedNose', true, current_timestamp),
    ('Fashion Trend', true, current_timestamp),
    ('Adidas', true, current_timestamp),
    ('Nike', true, current_timestamp);

