package auth.com.usuario.services;

import auth.com.usuario.Model.ClimaTestes;
import auth.com.usuario.Model.DanosTestes;
import auth.com.usuario.Model.TokenManager;
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

import java.util.Set;

import static io.restassured.RestAssured.given;

public class TesteClimaService {

    ClimaTestes climaTestes = new ClimaTestes();
    TokenManager tokenManager = TokenManager.getInstance();

    public Response response;
    String idClima;
    private String baseUrl = "http://localhost:8080";

    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public void createClima(String endPoint){
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(climaTestes);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Carregar o JSON Schema
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
            JsonSchema schema = factory.getSchema(
                    getClass().getResourceAsStream("/schemas/cadastrar-clima.json"));

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

    public void configCadastroClima(String field, String value){
        switch (field){
            case "id" -> climaTestes.setId(Long.parseLong(value));
            case "idTornado" -> climaTestes.setIdTornado(Long.parseLong(value));
            case "clima" -> climaTestes.setClima(value);
            case "temperatura" -> climaTestes.setTemperatura(Integer.parseInt(value));
            case "data" -> climaTestes.setData(value);
            case "local" -> climaTestes.setLocal(value);
        }
    }

    public void updateClima(String endPoint){
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(climaTestes);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Carregar o JSON Schema
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
            JsonSchema schema = factory.getSchema(
                    getClass().getResourceAsStream("/schemas/atualizar-clima.json"));

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


    public void deleteClima (String endPoint){
        String url = String.format("%s%s/%s", baseUrl, endPoint, idClima);
        response = given()
                .accept(ContentType.JSON)
                .header("Authorization", "Bearer " + tokenManager.getToken())
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }

    public void consultaClima(String endPoint){
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
        idClima = String.valueOf(gson.fromJson(response.jsonPath().prettify(), ClimaTestes.class).getId());
    }
}
