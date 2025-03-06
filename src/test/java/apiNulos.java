package apiModules;
import org.testng.Assert;
import org.testng.annotations.Test;
import  io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.Map;

public class apiNulos {

    // Validar se o campo first_name, last_name e email são não nulos/vazios - GET

    @Test
    public void validarNULOS(){

        Response response = get("https://reqres.in/api/users?page=2");
        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode,200);


        Map<String, Object> map = response.jsonPath().getMap("data[0]");

        String firstname = (String) map.get("first_name");
        Assert.assertNotNull(firstname, "firstname não pode ser nulo");
        Assert.assertFalse(firstname.isEmpty(), "firstname não pode ser vazio");

        String lastname = (String) map.get("last_name");
        Assert.assertNotNull(lastname, "last_name não pode ser nulo");
        Assert.assertFalse(lastname.isEmpty(), "firstname não pode ser vazio");

        String email = (String) map.get("email");
        Assert.assertNotNull(email, "email não pode ser nulo");
        Assert.assertFalse(email.isEmpty(), "email não pode ser vazio");

    }

}
