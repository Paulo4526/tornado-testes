package auth.com.usuario.services;

import auth.com.usuario.Model.DanosTestes;
import auth.com.usuario.Model.TokenManager;
import auth.com.usuario.Model.Tornadotestes;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static io.restassured.RestAssured.given;

public class TesteDanosService {


    DanosTestes danosTestes = new DanosTestes();
    TokenManager tokenManager = TokenManager.getInstance();

    public Response response;
    String idDano;
    String baseUrl = "http://localhost:8080";

    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();



    public void createDano(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(danosTestes);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Carregar o JSON Schema
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
            JsonSchema schema = factory.getSchema(
                    getClass().getResourceAsStream("/schemas/cadastrar-dano.json"));

            // Realizar a requisição
            response = given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .header("Authorization", "Bearer " + tokenManager.getToken())
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

    public void configCadastroDano(String field, String value) {
        switch (field) {
            case "id" -> danosTestes.setId(Long.parseLong(value));
            case "idTornado" -> danosTestes.setIdTornado(Long.parseLong(value));
            case "dano" -> danosTestes.setDano(value);
            case "local" -> danosTestes.setLocal(value);
        }
    }

    public void updateDano(String endPoint){
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(danosTestes);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Carregar o JSON Schema
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
            JsonSchema schema = factory.getSchema(
                    getClass().getResourceAsStream("/schemas/atualizar-dano.json"));

            // Realizar a requisição
            response = given()
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .header("Authorization", "Bearer " + tokenManager.getToken())
                    .body(bodyToSend)
                    .when()
                    .put(url)
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


    public void deleteDano (String endPoint){
        String url = String.format("%s%s/%s", baseUrl, endPoint, idDano);
        response = given()
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + tokenManager.getToken())
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }

    public void consultaDano(String endPoint){
        String url =  baseUrl + endPoint;
        response = given()
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + tokenManager.getToken())
                .when()
                .get(url)
                .then()
                .extract()
                .response();
    }

    public void recuperarID (){
        idDano = String.valueOf(gson.fromJson(response.jsonPath().prettify(), DanosTestes.class).getId());
    }
}
