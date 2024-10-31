package auth.com.usuario.services;

import auth.com.usuario.Model.ClimaTestes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

@Service
public class TesteClimaService {

    private final ClimaTestes climaTestes = new ClimaTestes();
    public final Gson gson = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public Response response;

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
            case "idTornado" -> climaTestes.setIdTornado(Long.parseLong(value));
            case "clima" -> climaTestes.setClima(value);
            case "temperatura" -> climaTestes.setTemperatura(Integer.parseInt(value));
            case "data" -> climaTestes.setData(value);
            case "local" -> climaTestes.setLocal(value);
        }
    }
}
