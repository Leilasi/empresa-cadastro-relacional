-- Script para criar a tabela tb_endereco
CREATE TABLE tb_endereco (
                             id SERIAL PRIMARY KEY,
                             rua VARCHAR(100) NOT NULL,
                             numero VARCHAR(10) NOT NULL,
                             complemento VARCHAR(255),
                             bairro VARCHAR(100) NOT NULL,
                             cidade VARCHAR(50) NOT NULL,
                             estado VARCHAR(2) NOT NULL,
                             cep VARCHAR(10) NOT NULL
);

-- Script para criar a tabela tb_empresa com UNIQUE no email
CREATE TABLE tb_empresa (
                            id SERIAL PRIMARY KEY,
                            cnpj VARCHAR(20) NOT NULL,
                            razao_social VARCHAR(150) NOT NULL,
                            nome_fantasia VARCHAR(150) NOT NULL,
                            telefone VARCHAR(20),
                            data_fundacao DATE NOT NULL,
                            email VARCHAR(100) NOT NULL UNIQUE,
                            situacao_empresa VARCHAR(50) NOT NULL,
                            endereco_id BIGINT NOT NULL,
                            CONSTRAINT fk_endereco FOREIGN KEY (endereco_id) REFERENCES tb_endereco(id)
);
