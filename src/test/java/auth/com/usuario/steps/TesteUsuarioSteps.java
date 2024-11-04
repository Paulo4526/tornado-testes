package auth.com.usuario.steps;
import auth.com.usuario.Model.TokenManager;
import auth.com.usuario.services.AuthUserService;
import auth.com.usuario.services.TesteUsuarioService;
import io.cucumber.java.pt.*;
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
public class TesteUsuarioSteps {

    TesteUsuarioService testeUsuarioService = new TesteUsuarioService();
    AuthUserService authUserService = new AuthUserService();

    @Dado("que eu tenha os seguintes dados do usuario:")
    public void queEuTenhaOsSeguintesDadosDoUsuario(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            testeUsuarioService.configCadastroUsuario(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastro de usuário")
    public void euEnviarARequisiçãoParaOEndpointDeCadastroDeUsuário(String endPoint) {
        testeUsuarioService.createUsuario(endPoint);
    }

    @Então("o status code da resposta usuario deve ser {int}")
    public void oStatusCodeDaRespostaUsuarioDeveSer(int statuscode) {
        Assert.assertEquals(statuscode, testeUsuarioService.response.statusCode());
    }

    @Dados("que eu tenha o email e senha do usuário:")
    public void queEuTenhaOEmailESenhaDoUsuário(List<Map<String, String>> rows) {
        for (Map<String, String> columns : rows) {
            authUserService.configLoginUsuario(columns.get("campo"), columns.get("valor"));
        }
    }

    @Quando("eu enviar a requisição para o endPoint {string} para login do usuário")
    public void euEnviarARequisiçãoParaOEndPointParaLoginDoUsuário(String endPoint) {
        authUserService.validadeLogin(endPoint);

    }

    @Então("o status code da resposta do login deve ser {int}")
    public void oStatusCodeDaRespostaDoLoginDeveSer(int statuscode) {
        Assert.assertEquals(statuscode, authUserService.response.statusCode());
    }
}
