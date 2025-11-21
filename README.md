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

## Futuras Melhorias na Aplicação

- Corrigir a autorização por token

## Autor
Thiago Pereira de Mattos
