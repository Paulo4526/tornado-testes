package auth.com.usuario.services;

import auth.com.usuario.Model.ClimaTestes;
import auth.com.usuario.Model.DanosTestes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

@Service
public class TesteClimaService {

    ClimaTestes climaTestes = new ClimaTestes();

    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public Response response;

    String idClima;

    private String baseUrl = "http://localhost:8080";

    public void createClima(String endPoint){
        String url = baseUrl + endPoint;
        String bodyToSend = gson.toJson(climaTestes);
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


    public void deleteClima (String endPoint){
        String url = String.format("%s%s/%s", baseUrl, endPoint, idClima);
        response = given()
                .accept(ContentType.JSON)
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
