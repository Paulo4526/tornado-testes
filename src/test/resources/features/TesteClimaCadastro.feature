# language: pt
@regressivo
Funcionalidade: Cadastro de um novo tornado
Como usuário da API
Quero cadastrar um novo tornado
Para que o registro seja salvo corretamente no sistema
  Contexto: Cadastro bem-sucedido de tornado
    Dado que eu tenha os seguintes dados do clima:
    | campo          | valor        |
    | idTornado      | 1            |
    | clima          | chuvoso      |
    | temperatura    | 25           |
    | data           | 2024-08-22   |
    | local          | Arujá        |
    Quando eu enviar a requisição para o endpoint "/clima/cadastrar" de cadastro de clima
    Então o status code da resposta de clima deve ser 201

  Cenário: Atualização bem sucedida do tornado
    Dado que eu atualize o nome e a classificacao do clima:
    | campo          | valor        |
    | id             | 1            |
    | idTornado      | 1            |
    | clima          | chuvoso      |
    | temperatura    | 25           |
    | data           | 2024-08-22   |
    | local          | Arujá        |
    Quando eu enviar a requisição para o endpoint "/clima/atualizar" de atualização de cadastro de clima
    Então o status code da resposta de clima deve ser 202

  Cenário: Deve Ser possível deletar uma dado de clima por um id existente
    Dado que eu recupere um ID clima já criado pelo contexto
    Quando eu enviar uma requisição HTTP para "/clima/excluir" de deleção de clima
    Então o status code da resposta de clima deve ser 204

  Cenário: Deve ser possível a consulta dos clima
    Quando eu enviar uma requisição HTTP para "/clima/listarTodos" de consulta de clima
    Então o status code da resposta de clima deve ser 200

  Cenário: Erro quando informar um dado inválido para deleção
    Dado que eu insira um id invalido para exclusão de clima "9999"
    Quando eu enviar uma requisição HTTP para "/clima/excluir" de deleção de clima
    Então o status code da resposta de clima deve ser 403