import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class ApiTest {

    String baseurl = "https://reqres.in";

    @Test
    void create() {

        given()
                .contentType(JSON)
                .body("{\"name\": \"newuser\",\"job\": \"worker\"}")
                .when()
                .post(baseurl + "/api/users")
                .then()
                .statusCode(201)
                .body("name", is("newuser"), "job", is("worker"));

    }

    @Test
    void listUsersTotal() {
        given()
                .when()
                .get(baseurl + "/api/users?page=1")
                .then()
                .statusCode(200)
                .body("total", is (12));
    }

    @Test
    void listUsersSomeData() {
        given()
                .when()
                .get(baseurl + "/api/users?page=1")
                .then()
                .statusCode(200)
                .body("data.email", hasItem ("george.bluth@reqres.in"));
    }


    @Test
    void singleUser() {
        given()
                .when()
                .get(baseurl + "/api/users/1")
                .then()
                .statusCode(200)
                .body("data.email", is ("george.bluth@reqres.in"));
    }

    @Test
    void update() {
        given()
                .contentType(JSON)
                .body("{\"name\": \"newuser\",\"job\": \"worker\"}")
                .when()
                .put(baseurl + "/api/users/1")
                .then()
                .statusCode(200)
                .body("updatedAt", containsString ("2021-08"));
    }

}
