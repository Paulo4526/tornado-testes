package auth.com.usuario.steps;

import auth.com.usuario.services.TestTornadoService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.cucumber.junit.CucumberOptions;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.util.List;
import java.util.Map;

@RunWith(Suite.class)
@CucumberOptions(
        features = {"classpath:festures"},
        glue = "auth.com.usuario.steps",
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class TesteTornadoSteps {

    String idTornado;
    TestTornadoService testTornadoService = new TestTornadoService();

    @Dado("que eu tenha os seguintes dados do tornado:")
    public void queEuTenhaOsSeguintesDadosDoTornado(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            testTornadoService.configCadastroTornado(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de tornado")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeTornado(String endPoints) {
        testTornadoService.createTornado(endPoints);
    }

    @Então("o status code da resposta deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statuscode) {
        Assert.assertEquals(statuscode, testTornadoService.response.statusCode());
    }

    @Dado("que eu atualize o nome e a classificacao do tornado:")
    public void queEuAtualizeONomeEAClassificacaoDoTornado(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            testTornadoService.configCadastroTornado(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de atualização de cadastro de tornado")
    public void euEnviarARequisiçãoParaOEndpointDeAtualizaçãoDeCadastroDeTornado(String endPoint) {
        testTornadoService.updateTornado(endPoint);
    }

    @Então("o status code da resposta de atualização deve ser {int}")
    public void oStatusCodeDaRespostaDeAtualizaçãoDeveSer(int codeStatus) {
        Assert.assertEquals(codeStatus, testTornadoService.response.statusCode());
    }

    @Dado("que eu recupere um ID já criado pelo contexto")
    public void queEuRecupereUmIDJáCriadoPeloContexto() {
        testTornadoService.recuperarID();
    }

    @Quando("eu enviar uma requisição HTTP para {string} de deleção de tornados")
    public void euEnviarUmaRequisiçãoHTTPParaDeDeleçãoDeTornados(String endPoint) {
        testTornadoService.deleteTornado(endPoint);
    }

    @Quando("eu enviar uma requisição HTTP para {string} de consulta de tornados")
    public void euEnviarUmaRequisiçãoHTTPParaDeConsultaDeTornados(String endPoint) {
        testTornadoService.consultaTornado(endPoint);
    }

    @Dado("que eu insira um id invalido para exclusão {string}")
    public void queEuInsiraUmIdInvalidoParaExclusão(String idTornado) {
        this.idTornado = idTornado;
    }


//    @E("o corpom da reposta de erro da api deve retornar a mensagem {string}")
//    public void oCorpomDaRepostaDeErroDaApiDeveRetornarAMensagem(String message) {
//        ErrorMessageModel errorMessageModel = testTornadoService.gson.fromJson(
//                testTornadoService.response.jsonPath().prettify(), ErrorMessageModel.class);
//        Assert.assertEquals(message, errorMessageModel.getMessage());
//    }


}
