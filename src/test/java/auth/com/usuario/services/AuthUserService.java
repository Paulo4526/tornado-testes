package auth.com.usuario.services;

import auth.com.usuario.Model.TokenManager;
import auth.com.usuario.Model.LoginManager;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Set;

import static io.restassured.RestAssured.given;

public class AuthUserService {
    LoginManager userManager = new LoginManager();
    TokenManager tokenManager = TokenManager.getInstance();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public Response response;

    String baseUrl = "http://localhost:8080";

    public void validadeLogin(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(userManager);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Carregar o JSON Schema
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
            JsonSchema schema = factory.getSchema(
                    getClass().getResourceAsStream("/schemas/validar-token.schema.json"));

            // Realizar a requisição
            response = given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(bodyToSend)
                    .when()
                    .post(url)
                    .then()
                    .extract()
                    .response();

            // Converter a resposta em JsonNode para validar
            JsonNode responseNode = objectMapper.readTree(bodyToSend);

            // Validar o JSON de resposta contra o schema
            Set<ValidationMessage> errors = schema.validate(responseNode);

            if (!errors.isEmpty()) {
                // Erro de validação
                throw new RuntimeException("JSON response diferente do schema: " + errors);
            }

            // Extraindo o token da resposta se a validação foi bem-sucedida
            String token = response.jsonPath().getString("token");
            tokenManager.setToken(token);
            System.err.println("Token: " + token);

        } catch (Exception e) {
            // Em caso de erro
            System.err.println("Falha ao validar a Resposta JSON: " + e.getMessage());
        }
    }

    public void configLoginUsuario(String field, String value) {
        switch (field) {
            case "email" -> userManager.setEmail(value);
            case "senha" -> userManager.setSenha(value);
        }
    }
}
