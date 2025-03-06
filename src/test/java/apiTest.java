import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class apiTest {

    @Test
    public void testAPI() {
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
                int statusCode = response.getStatusCode();

        assertEquals(200, statusCode);
    }

}


