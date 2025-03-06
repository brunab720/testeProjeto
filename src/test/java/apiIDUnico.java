import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertNotEquals;

public class apiIDUnico {

    // Validar criação de ID unico - POST

    @Test
    public void validarIDUnico() {

        Map<String, Object> map = new HashMap<String,Object>();

        JSONObject request1 = new JSONObject(map);
        request1.put("name","João");
        request1.put("job","Software Engineer");

        baseURI = "https://reqres.in/api";
        Response response1 = given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body(request1).
                when().
                post("users").
                then().
                statusCode(201).
                log().all().
                extract().response();


        JSONObject request2 = new JSONObject(map);
        request2.put("name", "Bruna");
        request2.put("job", "Software Engineer");

        Response response2 = given().
                header("Content-Type", "application/json").
                contentType(ContentType.JSON).
                body(request2).
                when().
                post("users").
                then().
                statusCode(201).
                log().all().
                extract().response();

        String Id1 = response1.jsonPath().getString("id");
        String Id2 = response2.jsonPath().getString("id");

        assertNotEquals(Id1,Id2, "Os IDs não podem ser iguais. Devem ser IDs únicos."); // Verifica se ID 1 não é igual ao ID 2

    }

}
