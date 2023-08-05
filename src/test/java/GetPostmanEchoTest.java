import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetPostmanEchoTest {

    @Test
    void shouldReturnSomething() {

        given()
                .baseUri("https://postman-echo.com")
                .body("hi guys")
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("data", equalTo("hi guys!"))
        ;
    }
}
