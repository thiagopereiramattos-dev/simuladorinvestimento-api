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
-- INSERIR NÍVEIS DE RISCO
-- ===========================
INSERT INTO risco (id, nivel, descricao) VALUES
(1, 'Baixo', 'Baixa volatilidade, menor chance de perda'),
(2, 'Médio', 'Moderada volatilidade, risco equilibrado'),
(3, 'Alto', 'Alta volatilidade, maior chance de ganho ou perda');

-- ===========================
-- PRODUTOS (cada tipo é usado apenas 1 vez)
-- ===========================
INSERT INTO produto (id, nome_produto, tipo_produto_id, taxa_rentabilidade, risco_id, descricao) VALUES
(1, 'CDB Caixa 2026', 1, 0.12, 1, 'CDB com prazo de 12 meses'),
(2, 'LCI Banco XYZ', 2, 0.010, 1, 'LCI com isenção de IR'),
(3, 'LCA AgroMax', 3, 0.011, 1, 'LCA vinculada ao agronegócio'),
(4, 'Tesouro Selic 2028', 4, 0.008, 1, 'Título público pós-fixado'),
(5, 'Fundo Multimercado Alpha', 5, 0.015, 2, 'Fundo com estratégia diversificada'),
(6, 'FII Lajes Prime', 6, 0.010, 2, 'Fundo imobiliário focado em imóveis comerciais'),
(7, 'ETF BOVA11', 7, 0.014, 2, 'ETF que replica índice Bovespa'),
(8, 'Bitcoin Custodiado', 8, 0.035, 3, 'Criptomoeda com custódia segura'),
(9, 'Fundo Ações Tech Growth', 9, 0.028, 3, 'Fundo de ações focado em empresas de tecnologia de alto crescimento');

-- ===========================
-- CLIENTES
-- ===========================
INSERT INTO cliente (id, nome, CPF, email) VALUES
(1,  'Ana Souza',              12345678901, 'ana.souza@example.com'),
(2,  'Bruno Almeida',          23456789012, 'bruno.almeida@example.com'),
(3,  'Carla Mendes',           34567890123, 'carla.mendes@example.com'),
(4,  'Diego Santos',           45678901234, 'diego.santos@example.com'),
(5,  'Eduarda Martins',        56789012345, 'eduarda.martins@example.com'),
(6,  'Felipe Carvalho',        67890123456, 'felipe.carvalho@example.com'),
(7,  'Gabriela Castro',        78901234567, 'gabriela.castro@example.com'),
(8,  'Henrique Rocha',         89012345678, 'henrique.rocha@example.com'),
(9,  'Isabela Ribeiro',        90123456789, 'isabela.ribeiro@example.com'),
(10, 'João Barros',            12345678790, 'joao.barros@example.com'),
(11, 'Karen Azevedo',          11122233344, 'karen.azevedo@example.com'),
(12, 'Lucas Ferreira',         22233344455, 'lucas.ferreira@example.com'),
(13, 'Mariana Lopes',          33344455566, 'mariana.lopes@example.com'),
(14, 'Nelson Moreira',         44455566677, 'nelson.moreira@example.com'),
(15, 'Olivia Santos',          55566677788, 'olivia.santos@example.com'),
(16, 'Paulo Aragão',           66677788899, 'paulo.aragao@example.com'),
(17, 'Queila Duarte',          77788899900, 'queila.duarte@example.com'),
(18, 'Rafael Monteiro',        88899900011, 'rafael.monteiro@example.com'),
(19, 'Sabrina Faria',          99900011122, 'sabrina.faria@example.com'),
(20, 'Adriano Freitas',        10120230340, 'adriano.freitas@example.com');
