-- ===========================
-- TIPOS DE PRODUTO (não repetem entre produtos)
-- ===========================
INSERT INTO tipo_produto (id, nome, descricao) VALUES
(1, 'CDB', 'Certificado de Depósito Bancário'),
(2, 'LCI', 'Letra de Crédito Imobiliário'),
(3, 'LCA', 'Letra de Crédito do Agronegócio'),
(4, 'Tesouro Direto', 'Títulos públicos federais'),
(5, 'Fundo Multimercado', 'Fundo com diversas classes de ativos'),
(6, 'Fundo Imobiliário', 'Fundos que investem em imóveis'),
(7, 'ETF', 'Fundos de índice negociados em bolsa'),
(8, 'Criptomoeda', 'Ativos digitais como Bitcoin'),
(9, 'Fundo de Ações', 'Fundos que investem majoritariamente em ações');

-- ===========================
-- PRODUTOS
-- ===========================
INSERT INTO produto (id, nome_produto, tipo_produto_id, taxa_rentabilidade, risco, descricao) VALUES
(1, 'CDB Premium 12M',           1, 0.012, 'BAIXO', 'CDB com prazo de 12 meses'),
(2, 'LCI Banco XYZ',             2, 0.010, 'BAIXO', 'LCI com isenção de IR'),
(3, 'LCA AgroMax',               3, 0.011, 'BAIXO', 'LCA vinculada ao agronegócio'),
(4, 'Tesouro Selic 2028',        4, 0.008, 'BAIXO', 'Título público pós-fixado'),
(5, 'Fundo Multimercado Alpha',  5, 0.015, 'MEDIO', 'Fundo com estratégia diversificada'),
(6, 'FII Lajes Prime',           6, 0.010, 'MEDIO', 'Fundo imobiliário focado em imóveis comerciais'),
(7, 'ETF BOVA11',                7, 0.014, 'MEDIO', 'ETF que replica índice Bovespa'),
(8, 'Bitcoin Custodiado',        8, 0.035, 'ALTO', 'Criptomoeda com custódia segura'),
(9, 'Fundo Ações Tech Growth',   9, 0.028, 'ALTO', 'Fundo de ações de empresas de tecnologia');


-- ===========================
-- CLIENTES (senha padrão: 123456)
-- Hash: $2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u
-- ===========================

INSERT INTO cliente (id, nome, cpf, email, risco, senha_hash) VALUES
(1,  'Ana Souza',              12345678901, 'ana.souza@example.com', 'CONSERVADOR', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),
(2,  'Bruno Almeida',          23456789012, 'bruno.almeida@example.com', 'CONSERVADOR', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),
(3,  'Carla Mendes',           34567890123, 'carla.mendes@example.com', 'CONSERVADOR', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),
(4,  'Diego Santos',           45678901234, 'diego.santos@example.com', 'CONSERVADOR', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),
(5,  'Eduarda Martins',        56789012345, 'eduarda.martins@example.com', 'CONSERVADOR', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),
(6,  'Felipe Carvalho',        67890123456, 'felipe.carvalho@example.com', 'CONSERVADOR', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),
(7,  'Gabriela Castro',        78901234567, 'gabriela.castro@example.com', 'CONSERVADOR', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),
(8,  'Henrique Rocha',         89012345678, 'henrique.rocha@example.com', 'CONSERVADOR', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),

(9,  'Isabela Ribeiro',        90123456789, 'isabela.ribeiro@example.com', 'MODERADO', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),
(10, 'João Barros',            12345678790, 'joao.barros@example.com', 'MODERADO', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),
(11, 'Karen Azevedo',          11122233344, 'karen.azevedo@example.com', 'MODERADO', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),
(12, 'Lucas Ferreira',         22233344455, 'lucas.ferreira@example.com', 'MODERADO', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),
(13, 'Mariana Lopes',          33344455566, 'mariana.lopes@example.com', 'MODERADO', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),
(14, 'Nelson Moreira',         44455566677, 'nelson.moreira@example.com', 'MODERADO', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),
(15, 'Olivia Santos',          55566677788, 'olivia.santos@example.com', 'MODERADO', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),
(16, 'Paulo Aragão',           66677788899, 'paulo.aragao@example.com', 'MODERADO', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),

(17, 'Queila Duarte',          77788899900, 'queila.duarte@example.com', 'AGRESSIVO', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),
(18, 'Rafael Monteiro',        88899900011, 'rafael.monteiro@example.com', 'AGRESSIVO', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),
(19, 'Sabrina Faria',          99900011122, 'sabrina.faria@example.com', 'AGRESSIVO', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u'),
(20, 'Adriano Freitas',        10120230340, 'adriano.freitas@example.com', 'AGRESSIVO', '$2a$10$Z4rS4iZpHQPfzLmc9.lLJeGLumZ5qX6Ch12.hXqT8vmUMBiXx2E.u');


