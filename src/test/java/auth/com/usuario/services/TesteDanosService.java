package auth.com.usuario.services;

import auth.com.usuario.Model.DanosTestes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

@Service
public class TesteDanosService {


    final DanosTestes danosTestes = new DanosTestes();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public Response response;

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
            case "idTornado" -> danosTestes.setIdTornado(Long.valueOf(value));
            case "dano" -> danosTestes.setDano(value);
            case "local" -> danosTestes.setLocal(value);
        }
    }
}
