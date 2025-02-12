-- Script para criar a tabela tb_usuario
CREATE TABLE tb_usuario (
                            id SERIAL PRIMARY KEY,
                            nome VARCHAR(100) NOT NULL,
                            email VARCHAR(100) NOT NULL UNIQUE,
                            senha VARCHAR(255) NOT NULL,
                            perfil VARCHAR(20) NOT NULL
);
