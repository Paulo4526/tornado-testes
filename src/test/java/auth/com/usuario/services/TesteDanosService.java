package auth.com.usuario.services;

import auth.com.usuario.Model.DanosTestes;
import auth.com.usuario.Model.Tornadotestes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

@Service
public class TesteDanosService {


    DanosTestes danosTestes = new DanosTestes();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public Response response;

    String idDano;

    String baseUrl = "http://localhost:8080";

    public void createDano(String endPoint) {
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(danosTestes);


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


    public void deleteDano (String endPoint){
        String url = String.format("%s%s/%s", baseUrl, endPoint, idDano);
        response = given()
                .accept(ContentType.JSON)
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
