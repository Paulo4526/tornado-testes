# language: pt
@regressivo
Funcionalidade: Cadastro de um novo dano
  Como usuário da API
  Quero cadastrar um novo dano causado pelo tornado
  Para que o registro seja salvo corretamente no sistema:
  Contexto: Que meu usuario esteja autenticado
    Dados que eu tenha o email e senha do usuário:
      | campo          | valor        |
      | email          | paulobueno@hotmail.com|
      | senha          | Bueninho1!   |
    Quando eu enviar a requisição para o endPoint "/auth/usuario/login" para login do usuário
    Então o status code da resposta do login deve ser 200
    E que eu tenha os seguintes dados do danos:
    | campo          | valor        |
    | idTornado      | 1            |
    | dano           | Deslizamento |
    | local          | São Paulo    |
    Quando eu enviar a requisição para o endpoint "/dano/cadastrar" de cadastro de danos
    Então o status code da resposta de dano deve ser 201

  Cenário: Atualização bem sucedida do dano
    Dado que eu atualize o dano  e local:
      | campo          | valor        |
      | id             | 1            |
      | idTornado      | 1            |
      | dano           | desmoronamento |
      | local          | Sao Paulo    |
    Quando eu enviar a requisição para o endpoint "/dano/atualizar" de atualização de cadastro de dano
    Então o status code da resposta de atualização de danos deve ser 202

  Cenário: Deve Ser possível deletar uma dado de dano existente
    Dado que eu recupere um ID já criado pelo contexto de dano
    Quando eu enviar uma requisição HTTP para "/dano/excluir" de deleção de danos
    Então o status code da resposta de danos deve ser 204

  Cenário: Deve ser possível a consulta dos danos
    Quando eu enviar uma requisição HTTP para "/dano/listarTodos" de consulta de danos
    Então o status code da resposta de dano deve ser 200

  Cenário: Erro quando informar um dado inválido para deleção
    Dado que eu insira um id invalido para exclusão de dano "9999"
    Quando eu enviar uma requisição HTTP para "/dano/excluir" de deleção de danos
    Então o status code da resposta de dano deve ser 403
