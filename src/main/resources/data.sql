-- Script de dados iniciais para tabela produto
-- Usado para simulações de investimento

INSERT INTO produto (id, nome_produto, tipo_produto, taxa_rentabilidade, nivel_risco) VALUES
(1, 'Poupança Plus', 'Renda Fixa', 0.005, 'Baixo'),
(2, 'CDB 12 Meses', 'Renda Fixa', 0.012, 'Médio'),
(3, 'Tesouro Selic', 'Renda Fixa', 0.008, 'Baixo'),
(4, 'Fundo Ações Tech', 'Renda Variável', 0.025, 'Alto'),
(5, 'Fundo Multimercado', 'Multimercado', 0.015, 'Médio'),
(6, 'LCI Banco XYZ', 'Renda Fixa', 0.010, 'Baixo'),
(7, 'Ações Blue Chip', 'Renda Variável', 0.020, 'Alto');
