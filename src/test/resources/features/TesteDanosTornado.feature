# language: pt
@regressivo
Funcionalidade: Cadastro de um novo dano
  Como usuário da API
  Quero cadastrar um novo dano causado pelo tornado
  Para que o registro seja salvo corretamente no sistema:
  Cenário: Cadastro bem-sucedido de danos
    Dado que eu tenha os seguintes dados do danos:
      | campo          | valor        |
      | idTornado      | 1            |
      | dano           | Deslizamento |
      | local          | São Paulo    |
    Quando eu enviar a requisição para o endpoint "/dano/cadastrar" de cadastro de danos
    Então o status code da resposta para danos deve ser 201
