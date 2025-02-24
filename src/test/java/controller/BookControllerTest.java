package controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class BookControllerTest {

    // TODO: tests in quarkus
    @Test
    void shouldBookFindWhenReturnStatusOK() {
        given()
                .when().get("/books/my-store")
                .then()
                .statusCode(200);
    }
}