package auth.com.usuario.steps;

import auth.com.usuario.services.TesteClimaService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class TesteCadastroClima {
    String idClima;

    TesteClimaService testeClimaService = new TesteClimaService();

    @Dado("que eu tenha os seguintes dados do clima:")
    public void queEuTenhaOsSeguintesDadosDoClima(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            testeClimaService.configCadastroClima(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de clima")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeClima(String endPoint) {
        testeClimaService.createClima(endPoint);
    }

    @Então("o status code da resposta de clima deve ser {int}")
    public void oStatusCodeDaRespostaDeClimaDeveSer(int statuscode) {
        Assert.assertEquals(statuscode, testeClimaService.response.statusCode());
    }

    @Dado("que eu atualize o nome e a classificacao do clima:")
    public void queEuAtualizeONomeEAClassificacaoDoClima(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            testeClimaService.configCadastroClima(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de atualização de cadastro de clima")
    public void euEnviarARequisiçãoParaOEndpointDeAtualizaçãoDeCadastroDeClima(String endPoint) {
        testeClimaService.updateClima(endPoint);
    }

    @Dado("que eu recupere um ID clima já criado pelo contexto")
    public void queEuRecupereUmIDClimaJáCriadoPeloContexto() {
        testeClimaService.recuperarID();
    }

    @Quando("eu enviar uma requisição HTTP para {string} de deleção de clima")
    public void euEnviarUmaRequisiçãoHTTPParaDeDeleçãoDeClima(String endPoint) {
        testeClimaService.deleteClima(endPoint);
    }

    @Quando("eu enviar uma requisição HTTP para {string} de consulta de clima")
    public void euEnviarUmaRequisiçãoHTTPParaDeConsultaDeClima(String endPoint) {
        testeClimaService.consultaClima(endPoint);
    }

    @Dado("que eu insira um id invalido para exclusão de clima {string}")
    public void queEuInsiraUmIdInvalidoParaExclusãoDeClima(String idClima) {
        this.idClima = idClima;
    }
}
