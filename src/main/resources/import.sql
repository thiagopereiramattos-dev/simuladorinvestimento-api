-- ===========================
-- TIPOS DE PRODUTO
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
(1, 'CDB Premium 12M', 1, 0.012, 'BAIXO', 'CDB com prazo de 12 meses'),
(2, 'LCI Banco XYZ', 2, 0.010, 'BAIXO', 'LCI com isenção de IR'),
(3, 'LCA AgroMax', 3, 0.011, 'BAIXO', 'LCA vinculada ao agronegócio'),
(4, 'Tesouro Selic 2028', 4, 0.008, 'BAIXO', 'Título público pós-fixado'),
(5, 'Fundo Multimercado Alpha', 5, 0.015, 'MEDIO', 'Fundo com estratégia diversificada'),
(6, 'FII Lajes Prime', 6, 0.010, 'MEDIO', 'Fundo imobiliário focado em imóveis comerciais'),
(7, 'ETF BOVA11', 7, 0.014, 'MEDIO', 'ETF que replica índice Bovespa'),
(8, 'Bitcoin Custodiado', 8, 0.035, 'ALTO', 'Criptomoeda com custódia segura'),
(9, 'Fundo Ações Tech Growth', 9, 0.028, 'ALTO', 'Fundo de ações de empresas de tecnologia');

-- ===========================
-- PERFIL DE RISCO
-- ===========================
INSERT INTO perfil_risco (id, nome, pontuacao, descricao) VALUES
(1, 'CONSERVADOR', 30, 'Baixa movimentação, foco em liquidez'),
(2, 'MODERADO', 65, 'Equilíbrio entre segurança e rentabilidade'),
(3, 'AGRESSIVO', 90, 'Busca por alta rentabilidade, maior risco');

-- ===========================
-- CLIENTES (Senha SHA-256)
-- CONSERVADOR → senha: cons123
-- MODERADO    → senha: mod456
-- AGRESSIVO   → senha: agr789
-- ===========================

-- HASHES
-- CONSERVADOR: af09326cfb13a1029ec97076655a1524334bc90d7f7bb2e8ec17e56e84cc04db   === cons123
-- MODERADO:    92732499cdbcf188fbb074843cbc28442f6e34ac373b2eb5ca924932c24a262d   === mod456
-- AGRESSIVO:   b67e5ebed604a24d9d6ec24e5fee8e0f60c3c3d2ad123dc42589704043ba2817   === agr789

INSERT INTO cliente (id, nome, cpf, email, perfil_risco_id, senha_hash) VALUES
(1,  'Ana Souza Teste',       12345678901, 'ana.souza@example.com',        1, 'af09326cfb13a1029ec97076655a1524334bc90d7f7bb2e8ec17e56e84cc04db'),
(2,  'Bruno Almeida Teste',   23456789012, 'bruno.almeida@example.com',     1, 'af09326cfb13a1029ec97076655a1524334bc90d7f7bb2e8ec17e56e84cc04db'),
(3,  'Carla Mendes Teste',    34567890123, 'carla.mendes@example.com',      1, 'af09326cfb13a1029ec97076655a1524334bc90d7f7bb2e8ec17e56e84cc04db'),
(4,  'Diego Santos Teste',    45678901234, 'diego.santos@example.com',      1, 'af09326cfb13a1029ec97076655a1524334bc90d7f7bb2e8ec17e56e84cc04db'),
(5,  'Eduarda Martins Teste', 56789012345, 'eduarda.martins@example.com',   1, 'af09326cfb13a1029ec97076655a1524334bc90d7f7bb2e8ec17e56e84cc04db'),
(6,  'Felipe Carvalho Teste', 67890123456, 'felipe.carvalho@example.com',   1, 'af09326cfb13a1029ec97076655a1524334bc90d7f7bb2e8ec17e56e84cc04db'),
(7,  'Gabriela Castro Teste', 78901234567, 'gabriela.castro@example.com',   1, 'af09326cfb13a1029ec97076655a1524334bc90d7f7bb2e8ec17e56e84cc04db'),
(8,  'Henrique Rocha Teste',  89012345678, 'henrique.rocha@example.com',    1, 'af09326cfb13a1029ec97076655a1524334bc90d7f7bb2e8ec17e56e84cc04db'),

(9,  'Isabela Ribeiro AAAAA',       90123456789, 'isabela.ribeiro@example.com',   2, '92732499cdbcf188fbb074843cbc28442f6e34ac373b2eb5ca924932c24a262d'),
(10, 'João Barros',          12345678790, 'joao.barros@example.com',       2, '92732499cdbcf188fbb074843cbc28442f6e34ac373b2eb5ca924932c24a262d'),
(11, 'Karen Azevedo',        11122233344, 'karen.azevedo@example.com',     2, '92732499cdbcf188fbb074843cbc28442f6e34ac373b2eb5ca924932c24a262d'),
(12, 'Lucas Ferreira',       22233344455, 'lucas.ferreira@example.com',    2, '92732499cdbcf188fbb074843cbc28442f6e34ac373b2eb5ca924932c24a262d'),
(13, 'Mariana Lopes',        33344455566, 'mariana.lopes@example.com',     2, '92732499cdbcf188fbb074843cbc28442f6e34ac373b2eb5ca924932c24a262d'),
(14, 'Nelson Moreira',       44455566677, 'nelson.moreira@example.com',    2, '92732499cdbcf188fbb074843cbc28442f6e34ac373b2eb5ca924932c24a262d'),
(15, 'Olivia Santos',        55566677788, 'olivia.santos@example.com',     2, '92732499cdbcf188fbb074843cbc28442f6e34ac373b2eb5ca924932c24a262d'),
(16, 'Paulo Aragão',         66677788899, 'paulo.aragao@example.com',      2, '92732499cdbcf188fbb074843cbc28442f6e34ac373b2eb5ca924932c24a262d'),

(17, 'Queila Duarte',        77788899900, 'queila.duarte@example.com',     3, 'b67e5ebed604a24d9d6ec24e5fee8e0f60c3c3d2ad123dc42589704043ba2817'),
(18, 'Rafael Monteiro',      88899900011, 'rafael.monteiro@example.com',   3, 'b67e5ebed604a24d9d6ec24e5fee8e0f60c3c3d2ad123dc42589704043ba2817'),
(19, 'Sabrina Faria',        99900011122, 'sabrina.faria@example.com',     3, 'b67e5ebed604a24d9d6ec24e5fee8e0f60c3c3d2ad123dc42589704043ba2817'),
(20, 'Adriano Freitas',     10120230340, 'adriano.freitas@example.com',    3, 'b67e5ebed604a24d9d6ec24e5fee8e0f60c3c3d2ad123dc42589704043ba2817');
