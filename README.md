# 📚 Sistema de Gerenciamento de Biblioteca (API REST)

Este projeto é uma API robusta para controle de acervo literário, desenvolvida para demonstrar competências em **Java 17+**, **Spring Boot** e persistência de dados com **MySQL**.

---

## 🛠️ Tecnologias e Ferramentas
* **Java 17** (Utilizando Records para DTOs)
* **Spring Boot 3.x** (Web, Data JPA, Validation)
* **MySQL 8.0** (Banco de Dados Relacional)
* **Hibernate** (Mapeamento Objeto-Relacional)
* **SpringDoc OpenAPI (Swagger)** (Documentação da API)
* **Maven** (Gerenciamento de Dependências)

---

## 🏗️ Arquitetura e Padrões
O projeto foi estruturado seguindo o padrão de camadas (Layered Architecture), garantindo que a lógica de negócio esteja separada da exposição dos dados:

1. **Entities**: Representação fiel das tabelas do banco de dados.
2. **Repositories**: Interfaces JPA para operações de CRUD.
3. **Services**: Onde reside a inteligência do sistema e validações.
4. **Controllers**: Endpoints seguindo os padrões REST.
5. **DTOs (Records)**: Objetos imutáveis para trânsito seguro de dados entre cliente e servidor.

---

## 🚀 Como Utilizar (Endpoints)

### Autores
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| **POST** | `/api/autores` | Cadastra um novo autor |
| **GET** | `/api/autores` | Lista todos os autores |

### Livros
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| **POST** | `/api/livros` | Cadastra um livro (necessita ID do autor) |
| **GET** | `/api/livros` | Lista todos os livros ativos |

---

## 🌟 Funcionalidades de Destaque

### 1. Soft Delete (Exclusão Lógica)
Em vez de apagar registros permanentemente, utilizamos um sistema de "desativação". Isso permite manter a integridade referencial e o histórico de dados sem poluir as consultas principais.

### 2. Integração com Data API
Os livros possuem o campo `dataPublicacao` utilizando a classe `LocalDate`, garantindo a manipulação correta de datas conforme as normas modernas do Java.

### 3. Documentação Automática
A API está totalmente documentada via Swagger. Com a aplicação rodando, acesse:
`http://localhost:8080/swagger-ui.html`

## 🗄️ Configuração do Banco de Dados
O sistema utiliza o Hibernate com a estratégia `update`, garantindo que as tabelas sejam criadas automaticamente sem apagar os dados existentes a cada reinicialização:

```properties
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/ProjetoCrud