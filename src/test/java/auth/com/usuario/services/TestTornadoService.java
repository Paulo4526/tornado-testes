package auth.com.usuario.services;

import auth.com.usuario.Model.Tornadotestes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.cucumber.datatable.DataTable;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class TestTornadoService {

    Tornadotestes tornado = new Tornadotestes();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public Response response;

    String idTornado;

    String baseUrl = "http://localhost:8080";

    public void createTornado(String endPoint){
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(tornado);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }

    public void updateTornado(String endPoint){
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(tornado);
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(bodyToSend)
                .when()
                .put(url)
                .then()
                .extract()
                .response();
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

    public void deleteTornado (String endPoint){
        String url = String.format("%s%s/%s", baseUrl, endPoint, idTornado);
        response = given()
                .accept(ContentType.JSON)
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
