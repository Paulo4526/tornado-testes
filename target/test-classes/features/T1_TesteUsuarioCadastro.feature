# language: pt
@regressivo
Funcionalidade: Cadastro de um novo usuário
  Como usuário da API
  Quero cadastrar um novo usuário
  Para que o registro seja salvo corretamente no sistema
  Cenário: Cadastro bem-sucedido de usuário
    Dado que eu tenha os seguintes dados do usuario:
      | campo          | valor        |
      | nome           | Paulo Bueno  |
      | email          | paulobueno@hotmail.com|
      | senha          | Bueninho1!   |
      | user           | ADMIN        |
      | data           | 2024-10-05   |
    Quando eu enviar a requisição para o endpoint "/auth/usuario/cadastrar" de cadastro de usuário
    Então o status code da resposta usuario deve ser 201

  Cenário: Que meu usuario esteja autenticado
    Dados que eu tenha o email e senha do usuário:
      | campo          | valor        |
      | email          | paulobueno@hotmail.com|
      | senha          | Bueninho1!   |
    Quando eu enviar a requisição para o endPoint "/auth/usuario/login" para login do usuário
    Então o status code da resposta do login deve ser 200