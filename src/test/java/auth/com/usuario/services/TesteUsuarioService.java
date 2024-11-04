package auth.com.usuario.services;

import auth.com.usuario.Model.DanosTestes;
import auth.com.usuario.Model.TokenManager;
import auth.com.usuario.Model.User;
import auth.com.usuario.Model.UserRole;
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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.Set;

import static io.restassured.RestAssured.given;

public class TesteUsuarioService {

    User user = new User();
    TokenManager tokenManager = TokenManager.getInstance();

    public Response response;
    String idUsuario;
    String baseUrl = "http://localhost:8080";

    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();



    public void createUsuario(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(user);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Carregar o JSON Schema
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
            JsonSchema schema = factory.getSchema(
                    getClass().getResourceAsStream("/schemas/cadastrar-usuario.json"));

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

        } catch (Exception e) {
            // Em caso de erro
            System.err.println("Falha ao validar a Resposta JSON: " + e.getMessage());
        }

    }

    public void configCadastroUsuario(String field, String value) {
        switch (field) {
            case "id" -> user.setId(Long.parseLong(value));
            case "nome" -> user.setNome(value);
            case "email" -> user.setEmail(value);
            case "senha" -> user.setSenha(value);
            case "user" -> user.setUser(UserRole.fromRole(value));
            case "data" -> user.setData(value);
        }
    }
}
