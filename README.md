# Finance API 

API RESTful desenvolvida em Java com Spring Boot, projetada para o gerenciamento eficiente de Gestores e Fundos de Investimento.

##  Sobre o Projeto
O Finance API é uma solução de backend focada na organização e persistência de dados do mercado financeiro. O projeto segue rigorosamente os padrões de arquitetura corporativa, garantindo um código limpo, desacoplado e de fácil manutenção.

##  Tecnologias Utilizadas
* **Java 21**
* **Spring Boot 3.2.x**
* **Spring Data JPA/Hibernate:** Para mapeamento objeto-relacional (ORM) e persistência de dados.
* **PostgreSQL:** Banco de dados relacional para armazenamento dos registros.
* **Bean Validation:** Para garantia da integridade dos dados na camada de entrada (DTOs).

##  Arquitetura de Software
O projeto foi estruturado em camadas para garantir a separação de responsabilidades:
* **Controller:** Recepcionista da API, lida com requisições HTTP.
* **Service:** O coração do sistema, onde residem as regras de negócio.
* **Model:** Representação das entidades de negócio e tabelas do banco.
* **Repository:** A camada de acesso aos dados (DAO).
* **DTO:** Objetos de transferência de dados, garantindo a segurança e performance da comunicação.
* **Infra:** Camada de infraestrutura, incluindo o tratamento global de erros (Exception Handling).

##  Como rodar
1. Clone este repositório.
2. Configure uma instância do PostgreSQL e atualize as credenciais no `application.properties`.
3. Execute a classe `FinanceApplication`.

##  Endpoints (Manual de Operações)

### 1. Gestores (Gestão de Gestoras)

* **Criar Gestor (POST)**
  `POST http://localhost:8080/gestores`
  ```json
  {
      "nome": "Alpha Investimentos",
      "cnpj": "99.999.999/0001-00"
  }
Listar Gestores (GET)
GET http://localhost:8080/gestores

Atualizar Gestor (PUT)
PUT http://localhost:8080/gestores/{id}

JSON
{
"nome": "Alpha Investimentos Atualizada",
"cnpj": "99.999.999/0001-00"
}
Deletar Gestor (DELETE)
DELETE http://localhost:8080/gestores/{id}

2. Fundos (Gestão de Fundos de Investimento)
   Criar Fundo (POST)
   POST http://localhost:8080/fundos

JSON
{
"nome": "Fundo Tech Brasil",
"patrimonioLiquido": 250000.00,
"gestorId": "COLE_O_ID_DO_GESTOR_AQUI"
}
Atualizar Fundo (PUT)
PUT http://localhost:8080/fundos/{id}

JSON
{
"nome": "Fundo Tech Brasil v2",
"patrimonioLiquido": 300000.00,
"gestorId": "COLE_O_ID_DO_GESTOR_AQUI"
}
Deletar Fundo (DELETE)
DELETE http://localhost:8080/fundos/{id}
