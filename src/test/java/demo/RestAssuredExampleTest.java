package demo;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;

public class RestAssuredExampleTest {

    @Test
    public void testGetUser(){
        RestAssured.baseURI = "https://reqres.in/api";

        given()
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.first_name", notNullValue());



    }

    @Test
    public void testCreateUser(){
        RestAssured.baseURI = "https://reqres.in/api";
        String requestBody = "{\n" +
                "    \"name\": \"Ishani\",\n" +
                "    \"job\": \"Nuwanthika\"\n" +
                "}";

        Response response =
                given()
                        .relaxedHTTPSValidation()
                        .header("Content-Type", "application/json")
                        .body(requestBody)
                        .when()
                        .post("/users")
                        .then()
                        .log().all()   // Logs status, headers, and body
                        .extract()
                        .response();

        System.out.println("Response Body:\n" + response.asPrettyString());




    }
}
