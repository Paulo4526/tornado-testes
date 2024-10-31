package auth.com.usuario.steps;

import auth.com.usuario.services.TesteClimaService;
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
public class TesteClimaSteps {
    TesteClimaService testeClimaService = new TesteClimaService();
    @Dado("que eu tenha os seguintes dados do climas:")
    public void queEuTenhaOsSeguintesDadosDoClimas(List<Map<String, String>> rows) {
        for(Map<String, String> columns : rows) {
            testeClimaService.configCadastroClima(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de climas")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeClimas(String endPoints) {
        testeClimaService.createClima(endPoints);
    }

    @Então("o status code da resposta para climas deve ser {int}")
    public void oStatusCodeDaRespostaParaClimasDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, testeClimaService.response.statusCode());
    }
}
