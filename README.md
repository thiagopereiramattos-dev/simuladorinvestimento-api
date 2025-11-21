— Simulador de Investimentos (Backend)

Visão Geral

Este projeto implementa uma API REST para simular investimentos financeiros. Ele foi desenvolvido em Java 21 com Quarkus 3+ com banco SQLite, seguindo princípios de arquitetura limpa, boas práticas de modelagem de domínio e um tratamento global e padronizado de exceções.

A API recebe dados de simulação, valida regras de negócio, consulta entidades persistidas e retorna um resultado consolidado para o front-end.

Tecnologias Utilizadas

Java 21

Quarkus 3+ (RESTEasy Reactive, Hibernate ORM)

JAX-RS para endpoints REST

SQLite

Lombok

SLF4J para logs

---

## Estrutura de Pacotes
```yaml
│
├── application
│   ├── controller
│   ├── dto
│   ├── exception          <-- GlobalExceptionMapper + ApiErrorResponse
│   └── service
    └── mapper
│
├── domain
    ├── exception         
│   └── model
    └── service
    └── repository
│
└── infrastructure
    ├── persistence
    └── repositoryImpl
    └── entity

````

## Application Properties

Existem diferentes arquivos de propriedades para diferentes ambientes:

   - `application.properties` → Configuração padrão (Docker/prod)
   - `application-dev.properties` → Configuração para ambiente de desenvolvimento local
   - `application-docker.properties` → Configuração específica para Docker, se necessário

O **profile ativo** pode ser configurado via variável de ambiente:

environment:
   - QUARKUS_PROFILE=dev
   - QUARKUS_PROFILE=docker
````
````
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

Coloquei todas as FK da entity com esse problema para usar no nome da coluna o id como :
id_entidade
- `id_cliente`
- `id_produto`


---

## Executando Aplicação

```yaml
LOCAL
1° clonar repositório -- git clone https://github.com/thiagopereiramattos-dev/simuladorinvestimento-api.git
cd simuladorinvestimento-api
2° Executar via Maven: ./mvnw clean quarkus:dev

O serviço ficará disponível em: http://localhost:8080

DOCKER

1° Construir a imagem tendo o dockerFile e dockerCompose
   - docker compose build --no-cache
2° Subir Container  
   - docker compose up

A API estará disponível em: http://localhost:8080
````

Scripts e Banco de Dados

Banco SQLite armazenado em data/

Scripts de inicialização podem ser configurados em import.sql 
   (executados automaticamente) em profile dev)

OpenAPI / Swagger
Documentação acessível em: http://localhost:8080/swagger-ui

OpenAPI endpoint: http://localhost:8080/openapi

---

## Fluxo de vida de uma Requisição
```yaml
Fluxo de Vida de uma Requisição

   Resource (Controller / Endpoint)
   A requisição HTTP chega no Resource, que é responsável por expor os endpoints da API.

   Ele recebe os dados e chama o mapper para montar uma entidade de negocio para usar na camada domain .

   Realiza a validação básica (via DTOs e anotações de validação).

   Mapeia os dados para o formato interno do sistema.

   Service (Lógica de Negócio)
   O Service recebe os dados já mapeados e trata a lógica de negócio.

   Processa regras específicas do domínio.

   Pode chamar outros serviços ou realizar transformações adicionais.

   Prepara os dados para persistência ou consulta.

   Repository / RepositoryImpl (Acesso a Dados)
   O RepositoryImpl é responsável por acessar o banco de dados.

   Interage diretamente com a Entity correspondente.

   Executa operações de CRUD ou consultas específicas.

   Retorna os dados processados de volta para o Service.

   Retorno da Resposta

   O Service envia os dados processado de volta ao Resource que chama mapper para 

   Converte os dados para DTOs de resposta e retorna a resposta final para o cliente.
````

## Sobre as Exceptions
```yaml

O tratamento de exceções é baseado em ExceptionMappers especializados
Um para erros de validação dos campos, outro para erros de domínio
e um erro global para falhas inesperadas (500). 
Todos os handlers utilizam uma classe base responsável por montar respostas JSON padronizadas, 
garantindo consistência, rastreabilidade via logs e clareza para o consumidor da API.”

````

## Sobre os dados do Insert foram usado esses dados
```yaml

Caso de problema no Import dos dados no docker, realizar os insert abaixo:

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

INSERT INTO perfil_risco (id, nome, pontuacao, descricao) VALUES
(1, 'CONSERVADOR', 30, 'Baixa movimentação, foco em liquidez'),
(2, 'MODERADO', 65, 'Equilíbrio entre segurança e rentabilidade'),
(3, 'AGRESSIVO', 90, 'Busca por alta rentabilidade, maior risco');

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

````

## Futuras Melhorias na Aplicação

- Corrigir a autorização por token

## Autor
Thiago Pereira de Mattos
