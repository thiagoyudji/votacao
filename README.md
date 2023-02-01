# Projeto de Votação e Associados
Projeto de votação e associados (Desafio Técnico).

Foi utilizada uma simple clean architecture para garantir uma boa manutenibilidade do projeto, as regras de negócio estão encapsuladas dentro das entidades. O código foi escrito tentando seguir ao máximo os principios **SOLID**.

#### Observações

É necessário ter instalado a JDK 17 e o Docker.
Atenção a porta da fila de messageria/banco no arquivo de configurações, qualquer problema com o docker, reiniciar o container e baixar a imagem novamente.

## Rodando localmente


Clone o projeto

```bash
  git clone https://github.com/thiagoyudji/votacao.git
```

Entre no diretório do projeto

```bash
  cd votacao
```

Suba o container docker (Você deve ter o docker instalado para executar):

```bash
  docker compose up -d
```

Rode a classe main do projeto

```bash
  VotacaoApplication.java
```

Print para facilitar (IntelliJ IDE):
![image](https://user-images.githubusercontent.com/38568016/215602701-5a7cf0c7-a33f-48e9-8cb9-91ba3d821059.png)
## Documentação da API




#### Cria nova pauta.

```http
  POST /v1/pauta/nova?nome=${nome}
```

| Query Param   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `nome` | `String` | **Obrigatório**. Nome da pauta |

#### Cria nova sessão.
```http
  PUT /v1/pauta/nova-sessao?nome=${nome}&data_expiracao=${data_expiracao}
```

| Query Param   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `nome`      | `String` | **Obrigatório**. Nome da pauta existente |
| `data_expiracao`      | `LocalDateTime` | **Obrigatório**. Data de expiração da Sessão, formato (dd/MM/aaaa hh:MM:ss) |

#### Cria novo associado.

```http
  POST /v1/associado/novo?cpf=${cpf}
```

| Query Param   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `cpf` | `String` | **Obrigatório**. CPF do associado |

#### Cria novo voto.
```http
  POST /v1/voto/novo?associado_id=${associado_id}&voto=${voto}&pauta_id=${pauta_id}
```

| Query Param   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `associado_id`      | `String` | **Obrigatório**. ID associado existente |
| `voto`      | `String` | **Obrigatório**. voto (SIM/NAO) |
| `pauta_id`      | `String` | **Obrigatório**. ID pauta existente |


#### Swagger UI

```bash
  http://localhost:8080/v1/swagger-ui.html
```
## Tecnologias utilizadas

 - [Java 17](hhttps://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
 - [Gradle](https://gradle.org/)
 - [SpringBoot](https://spring.io/projects/spring-boot)
 - [Spring Data JDBC](https://spring.io/projects/spring-data-jdbc)
 - [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
 - [Spring Cloud OpenFeign](https://spring.io/projects/spring-cloud-openfeign)
 - [Spring Cloud Zookeper](https://spring.io/projects/spring-cloud-zookeeper)
 - [Spring Doc Open API](https://springdoc.org/)
 - [Spring + Kafka](https://spring.io/projects/spring-kafka)
 - [PostgreSQL](https://www.postgresql.org/docs/)
 - [Lombok](https://projectlombok.org/)
 - [Hibernate + Validator](https://hibernate.org/validator/)
 - [Docker](https://www.docker.com/)

### Tecnologias para testes (integração e unitário)
 - [JUnit](https://junit.org/junit5/)
 - [Mockito](https://site.mockito.org/)
 - [JMeter](https://jmeter.apache.org/)

