# Projeto de Gestão de Produtos

Este é um projeto de backend desenvolvido com Spring Boot, responsável por gerenciar produtos em um sistema de loja. Ele implementa as operações CRUD (Criar, Ler, Atualizar e Deletar) para produtos utilizando uma API RESTful.

## Tecnologias Utilizadas

- **Java** (versão 11 ou superior)
- **Spring Boot** (framework principal)
- **Spring Data JPA** (para acesso a dados)
- **H2 Database** (banco de dados em memória, utilizado para testes)
- **JUnit 5** e **Mockito** (para testes automatizados)

## Funcionalidades

- **Criar Produto**: Criação de um novo produto com informações como nome, descrição, preço e quantidade em estoque.
- **Ler Produto**: Obter informações de um produto pelo ID.
- **Ler Todos os Produtos**: Listar todos os produtos cadastrados.
- **Atualizar Produto**: Alterar informações de um produto existente.
- **Deletar Produto**: Remover um produto do sistema pelo ID.

## Como Executar o Projeto

### Pré-requisitos

- **Java 11 ou superior**
- **Maven** (para gerenciamento de dependências e build)
- **IDE** (como IntelliJ IDEA, Eclipse ou VS Code)

## Como Rodar

1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/nome-repositorio.git
2. Navegue até o diretório do projeto:
   ```bash
    cd nome-repositorio
3. Compile e execute o projeto usando Maven:
   ```bash
    mvn spring-boot:run
  A aplicação estará rodando em http://localhost:8080.
  
## Endpoints

- POST /produtos - Criar um novo produto
- GET /produtos/{id} - Buscar um produto pelo ID
- GET /produtos - Listar todos os produtos
- PUT /produtos/{id} - Atualizar um produto
- DELETE /produtos/{id} - Deletar um produto

## Testes

Os testes para o projeto estão localizados no diretório src/test/java/com/example/atvd16. Para executar os testes, use o seguinte comando:

   ```bash
   mvn test
