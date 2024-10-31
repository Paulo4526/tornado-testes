package auth.com.usuario.steps;

import auth.com.usuario.services.TesteDanosService;
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
public class TesteDanosSteps {
    TesteDanosService testeDanosService = new TesteDanosService();
    @Dado("que eu tenha os seguintes dados do danos:")
    public void queEuTenhaOsSeguintesDadosDoDanos(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            testeDanosService.configCadastroDano(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de danos")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeDanos(String endPoint) {
        testeDanosService.createDano(endPoint);
    }

    @Então("o status code da resposta para danos deve ser {int}")
    public void oStatusCodeDaRespostaParaDanosDeveSer(int statuscode) {
        Assert.assertEquals(statuscode, testeDanosService.response.statusCode());
    }


}
