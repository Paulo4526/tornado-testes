# language: pt
@regressivo
Funcionalidade: Cadastro de um novo clima
  Como usuário da API
  Quero cadastrar um novo dano causado pelo clima
  Para que o registro seja salvo corretamente no sistema:
  @padrão
  Cenário: Cadastro bem-sucedido de climas
    Dado que eu tenha os seguintes dados do climas:
      | campo          | valor        |
      | idTornado      | 1            |
      | clima          | Chuvoso      |
      | temperatura    | 25           |
      | data           | 2024-08-22   |
      | local          | Itaquaquecetuba |
    Quando eu enviar a requisição para o endpoint "/clima/cadastrar" de cadastro de climas
    Então o status code da resposta para climas deve ser 201