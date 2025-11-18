— Simulador de Investimentos (Backend)

Visão Geral

Este projeto implementa uma API REST para simular investimentos financeiros. Ele foi desenvolvido em Java 21 com Quarkus 3+, seguindo princípios de arquitetura limpa, boas práticas de modelagem de domínio e um tratamento global e padronizado de exceções.

A API recebe dados de simulação, valida regras de negócio, consulta entidades persistidas e retorna um resultado consolidado para o front-end.

Tecnologias Utilizadas

Java 21

Quarkus 3+ (RESTEasy Reactive, Hibernate ORM)

JAX-RS para endpoints REST

SQLite ou SQL Server (driver configurável)

Lombok

SLF4J para logs

Estrutura de Pacotes (simplificada)

br.com.mattos.simuladorinvestimento
│
├── application
│   ├── controller
│   ├── dto
│   ├── exception          <-- GlobalExceptionMapper + ApiErrorResponse
│   └── service
│
├── domain
│   ├── entity
│   ├── exception          <-- Exceções de negócio (ex: ProdutoNaoEncontradoException)
│   └── model
│
└── infrastructure
├── persistence
└── repository


### 📌 Por que usei `id_cliente` e não `client_id` nas foreign key da entidade SimulacaoInvestimento?

O Hibernate, quando usado com SQLite, estava com um comportamento inconsistente
específico relacionado às colunas de relacionamento (`@ManyToOne` /
`@JoinColumn`). O problema Não ocorre com colunas simples (`@Column`), apenas
com colunas de chave estrangeira.

#### 🔍 O que acontece?

1. O Hibernate **coloca todas as FKs primeiro** ao gerar o DDL.
2. Essas FKs são **ordenadas alfabeticamente**.
3. Somente depois ele adiciona:
    - a PK (`id`)
    - e os demais campos simples.

Esse comportamento causa o problema:

- Se o nome da FK vier antes de `id` na ordem alfabética,
- o Hibernate gera um DDL inválido,
- deixando o campo `id` sem tipo (ex.: `"id,"`) ou quebrando a ordem da tabela.

Exemplo de nomes que **causam erro** em SQLite + Hibernate:

- `client_id`
- `cliente_id`
- `aproduto_id`

#### ✅ A solução

Padronizamos todas as FK da entity com esse problema para usar no nome da coluna o id como :
id_entidade
- `id_cliente`
- `id_produto`

