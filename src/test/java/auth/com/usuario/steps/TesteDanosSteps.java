package auth.com.usuario.steps;

import auth.com.usuario.services.TesteDanosService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
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
        features = {"classpath:features"},
        glue = "auth.com.usuario.steps",
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class TesteDanosSteps {

    String idDano;

    TesteDanosService testeDanosService = new TesteDanosService();
    @E("que eu tenha os seguintes dados do danos:")
    public void queEuTenhaOsSeguintesDadosDoDanos(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            testeDanosService.configCadastroDano(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de danos")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeDanos(String endPoint) {
        testeDanosService.createDano(endPoint);
    }


    @Então("o status code da resposta de dano deve ser {int}")
    public void oStatusCodeDaRespostaDeDanoDeveSer(int statuscode) {
        Assert.assertEquals(statuscode, testeDanosService.response.statusCode());
    }

    @Dado("que eu atualize o dano  e local:")
    public void queEuAtualizeODanoELocal(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            testeDanosService.configCadastroDano(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de atualização de cadastro de dano")
    public void euEnviarARequisiçãoParaOEndpointDeAtualizaçãoDeCadastroDeDano(String endPoint) {
        testeDanosService.updateDano(endPoint);
    }

    @Então("o status code da resposta de atualização de danos deve ser {int}")
    public void oStatusCodeDaRespostaDeAtualizaçãoDeDanosDeveSer(int statuscode) {
        Assert.assertEquals(statuscode, testeDanosService.response.statusCode());
    }

    @Dado("que eu recupere um ID já criado pelo contexto de dano")
    public void queEuRecupereUmIDJáCriadoPeloContextoDeDano() {
        testeDanosService.recuperarID();
    }

    @Quando("eu enviar uma requisição HTTP para {string} de deleção de danos")
    public void euEnviarUmaRequisiçãoHTTPParaDeDeleçãoDeDanos(String endPoint) {
        testeDanosService.deleteDano(endPoint);
    }

    @Então("o status code da resposta de danos deve ser {int}")
    public void oStatusCodeDaRespostaDeDanosDeveSer(int statuscode) {
        Assert.assertEquals(statuscode, testeDanosService.response.statusCode());
    }

    @Quando("eu enviar uma requisição HTTP para {string} de consulta de danos")
    public void euEnviarUmaRequisiçãoHTTPParaDeConsultaDeDanos(String endPoint) {
        testeDanosService.consultaDano(endPoint);
    }

    @Dado("que eu insira um id invalido para exclusão de dano {string}")
    public void queEuInsiraUmIdInvalidoParaExclusãoDeDano(String idDano) {
        this.idDano = idDano;
    }
}
