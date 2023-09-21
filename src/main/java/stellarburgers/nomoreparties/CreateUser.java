package stellarburgers.nomoreparties;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateUser {
    private static final String CREATE_URL = "api/auth/register";

    public Response create(User user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(CREATE_URL);
    }
}
