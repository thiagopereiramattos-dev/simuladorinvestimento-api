-- =========================================
-- SCHEMA DO BANCO - SQLite
-- Criado com base nas entidades atuais
-- =========================================

PRAGMA foreign_keys = ON;

-- ===============================
-- TABELA: tipo_produto
-- ===============================
DROP TABLE IF EXISTS tipo_produto;

CREATE TABLE tipo_produto (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL UNIQUE,
    descricao TEXT
);

-- ===============================
-- TABELA: cliente
-- ===============================
DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    cpf BIGINT NOT NULL,
    email TEXT,
    risco TEXT NOT NULL CHECK (risco IN ('CONSERVADOR','MODERADO','AGRESSIVO')),
    senha_hash TEXT NOT NULL
);

-- ===============================
-- TABELA: produto
-- ===============================
DROP TABLE IF EXISTS produto;

CREATE TABLE produto (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome_produto TEXT NOT NULL,
    tipo_produto_id INTEGER NOT NULL,
    taxa_rentabilidade REAL NOT NULL,
    risco TEXT NOT NULL CHECK (risco IN ('BAIXO','MEDIO','ALTO')),
    descricao TEXT,
    FOREIGN KEY (tipo_produto_id) REFERENCES tipo_produto(id) ON DELETE RESTRICT
);

-- ===============================
-- TABELA: simulacao_investimento
-- ===============================
DROP TABLE IF EXISTS simulacao_investimento;

CREATE TABLE simulacao_investimento (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    valor_inicial REAL NOT NULL,
    valor_final REAL NOT NULL,
    rentabilidade REAL NOT NULL,
    prazo INTEGER NOT NULL,
    data_simulacao TEXT NOT NULL,
    produto_id INTEGER NOT NULL,
    cliente_id INTEGER NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE CASCADE,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE
);
