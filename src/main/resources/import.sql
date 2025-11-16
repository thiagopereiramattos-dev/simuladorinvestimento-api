-- ===========================
-- INSERIR TIPOS DE PRODUTO
-- ===========================
INSERT INTO tipo_produto (id, nome, descricao) VALUES
(1, 'CDB', 'Certificado de Depósito Bancário'),
(2, 'Renda Fixa', 'Investimentos de renda fixa com menor volatilidade'),
(3, 'Renda Variável', 'Investimentos sujeitos a variação do mercado'),
(4, 'Multimercado', 'Fundos que investem em diferentes mercados'),
(5, 'Crédito Privado', 'Debêntures e títulos privados'),
(6, 'Fundo Imobiliário', 'Fundos que investem em imóveis'),
(7, 'ETF', 'Fundos de índice negociados em bolsa'),
(8, 'Criptomoeda', 'Ativos digitais como Bitcoin');

-- ===========================
-- INSERIR NÍVEIS DE RISCO
-- ===========================
INSERT INTO risco (id, nivel, descricao) VALUES
(1, 'Baixo', 'Baixa volatilidade, menor chance de perda'),
(2, 'Médio', 'Moderada volatilidade, risco equilibrado'),
(3, 'Alto', 'Alta volatilidade, maior chance de ganho ou perda');

-- ===========================
-- INSERIR PRODUTOS
-- ===========================

-- PRODUTOS - RISCO BAIXO
INSERT INTO produto (id, nome_produto, tipo_produto_id, taxa_rentabilidade, risco_id, descricao) VALUES
(1, 'CDB 12 Meses', 1, 0.012, 1, 'CDB com prazo de 12 meses'),
(2, 'CDB Caixa 2026', 1, 0.12, 1, 'CDB emitido pela Caixa com vencimento em 2026'),
(3, 'Poupança Plus', 2, 0.005, 1, 'Conta de poupança com rendimento superior ao tradicional'),
(4, 'Tesouro Selic', 2, 0.008, 1, 'Título público atrelado à taxa Selic'),
(5, 'LCI Banco XYZ', 2, 0.010, 1, 'Letra de Crédito Imobiliário com isenção de IR');

-- PRODUTOS - RISCO MEDIO
INSERT INTO produto (id, nome_produto, tipo_produto_id, taxa_rentabilidade, risco_id, descricao) VALUES
(6, 'Fundo Multimercado', 4, 0.015, 2, 'Fundo que aplica em diversas classes de ativos'),
(7, 'Debênture Incentivada XYZ', 5, 0.015, 2, 'Debênture emitida por empresa, com incentivos fiscais'),
(8, 'FII Lajes Prime', 6, 0.010, 2, 'Fundo imobiliário com imóveis comerciais'),
(9, 'Fundo Crédito Privado Alpha', 5, 0.012, 2, 'Fundo que investe em títulos privados de crédito'),
(10, 'ETF BOVA11', 7, 0.014, 2, 'ETF que replica índice Bovespa');

-- PRODUTOS - RISCO ALTO
INSERT INTO produto (id, nome_produto, tipo_produto_id, taxa_rentabilidade, risco_id, descricao) VALUES
(11, 'Fundo Ações Tech', 3, 0.025, 3, 'Fundo de ações focado em tecnologia'),
(12, 'Ações Blue Chip', 3, 0.020, 3, 'Ações de grandes empresas consolidadas'),
(13, 'Criptomoeda BTC Custodiado', 8, 0.035, 3, 'Investimento em Bitcoin com custódia segura');