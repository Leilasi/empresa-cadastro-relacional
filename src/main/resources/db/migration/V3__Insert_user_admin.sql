-- Script para inserir um usuário admin na tabela tb_usuario, se não existir
INSERT INTO tb_usuario (nome, email, senha, perfil)
SELECT 'Administrador', 'admin@gmail.com', '$2a$10$zhIfqiOBXlZ/nhjORddCtuKN3k4mHk4MK1fR7Qt2cYI8iGfrtLoji', 'ADMIN'
    WHERE NOT EXISTS (SELECT 1 FROM tb_usuario WHERE email = 'admin@gmail.com');
