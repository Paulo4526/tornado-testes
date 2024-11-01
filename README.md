# Projeto BDD com Cucumber
Aplicação em Java Usando ferramentas e conceitos como:
- Rest e RestFull
- Migrations
- Lombok
- JWT
- SpringBoot(JPA, Data, Validations, Secutiry...)
- Docker e Docker Compose
- Git Actions e Git
- Swagger
- Testes de Comportamento com Cucumber e Junit
- JSON Schemas

## Pré-requisitos

- Java SDK 21
- Docker
- Git
- Maven

## Build da aplicação / Inicialização da aplicação

```sh
docker compose up --build
```

## Ierarquia de uso
- Cadastrar um novo Tornado.
- Cadastrar informações de Clima com relacionamento ao tornado cadastrado
- Cadastrar informações de Danos com relacionamento ao tornado cadastrado

OBS: Necessário criar um cadastro de tornado, caso tente criar clima ou danos terá retorno 403,
pois, há relacionamentos entre tornado e seus registros de clima e danos causados.

## Documentação online Swagger

link para acessar o swagger: http://localhost:8080/swagger-ui/index.html#/

![](/.templates/images/swagger.png)


