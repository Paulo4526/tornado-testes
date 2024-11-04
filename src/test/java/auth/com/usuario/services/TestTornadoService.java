package auth.com.usuario.services;

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

import java.util.Set;

import static io.restassured.RestAssured.given;

public class TestTornadoService {

    Tornadotestes tornado = new Tornadotestes();
    TokenManager tokenManager = TokenManager.getInstance();

    public Response response;
    String idTornado;
    String baseUrl = "http://localhost:8080";

    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();


    public void createTornado(String endPoint){
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(tornado);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Carregar o JSON Schema
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
            JsonSchema schema = factory.getSchema(
                    getClass().getResourceAsStream("/schemas/cadastrar-tornado.json"));

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

    public void configCadastroTornado(String field, String value) {
        switch (field) {
            case "id" -> tornado.setId(Long.parseLong(value));
            case "nome" -> tornado.setNome(value);
            case "classificacao" -> tornado.setClassificacao(value);
            case "local" -> tornado.setLocal(value);
            case "data" -> tornado.setData(value);
        }
    }

    public void updateTornado(String endPoint){
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(tornado);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Carregar o JSON Schema
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
            JsonSchema schema = factory.getSchema(
                    getClass().getResourceAsStream("/schemas/atualizar-tornado.json"));

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


    public void deleteTornado (String endPoint){
        String url = String.format("%s%s/%s", baseUrl, endPoint, idTornado);
        response = given()
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + tokenManager.getToken())
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }

    public void consultaTornado (String endPoint){
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
        idTornado = String.valueOf(gson.fromJson(response.jsonPath().prettify(), Tornadotestes.class).getId());
    }

}
