# language: pt
@regressivo
Funcionalidade: Cadastro de um novo tornado
  Como usuário da API
  Quero cadastrar um novo tornado
  Para que o registro seja salvo corretamente no sistema
  Contexto: Cadastro bem-sucedido de tornado
    Dado que eu tenha os seguintes dados do tornado:
      | campo          | valor        |
      | nome           | Catrina      |
      | classificacao  | F5           |
      | local          | Orleans      |
      | data           | 2024-08-22   |
    Quando eu enviar a requisição para o endpoint "/tornado/cadastrar" de cadastro de tornado
    Então o status code da resposta deve ser 201

  Cenário: Atualização bem sucedida do tornado
    Dado que eu atualize o nome e a classificacao do tornado:
      | campo          | valor        |
      | id             | 13           |
      | nome           | Murilo       |
      | classificacao  | F4           |
      | local          | Orleans      |
      | data           | 2024-08-22   |
    Quando eu enviar a requisição para o endpoint "/tornado/atualizar" de atualização de cadastro de tornado
    Então o status code da resposta de atualização deve ser 202

  Cenário: Deve Ser possível deletar uma entrega por um id existente
    Dado que eu recupere um ID já criado pelo contexto
    Quando eu enviar uma requisição HTTP para "/tornado/excluir" de deleção de tornados
    Então o status code da resposta deve ser 204

  Cenário: Deve ser possível a consulta dos tornados
    Quando eu enviar uma requisição HTTP para "/tornado/listarTodos" de consulta de tornados
    Então o status code da resposta deve ser 200

  Cenário: Erro quando informar um dado inválido para deleção
    Dado que eu insira um id invalido para exclusão "9999"
    Quando eu enviar uma requisição HTTP para "/tornado/excluir" de deleção de tornados
    Então o status code da resposta deve ser 403

