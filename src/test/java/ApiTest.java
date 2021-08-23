import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ApiTest {

    @Test
    void create (){

             given ()
                .contentType (JSON)
                .body ("{\"name\": \"newuser\"," +
                        "\"job\": \"worker\"}")
                .when ()
                .post ("https://reqres.in/api/users")
                .then ()
                .statusCode (201)
                .body ("name", is ("newuser"), "job", is ("worker"));

    }

}
