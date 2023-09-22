package stellarburgers.nomoreparties.user;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateUser {
    private static final String CREATE_URL = "/api/auth/register";
    private static final String LOGIN_URL = "/api/auth/login";
    private static final String DELETE_URL = "/api/auth/user";

    public Response create(User user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(CREATE_URL);
    }

    public Response login(UserCreds userCreds) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(userCreds)
                .when()
                .post(LOGIN_URL);
    }

    public Response update(UserCreds userCreds, String authToken) {
        return given()
                .header("Content-type", "application/json")
                .and().header("Authorization", authToken)
                .and()
                .body(userCreds)
                .when()
                .patch(DELETE_URL);
    }

    public Response updateWithoutAuth(UserCreds userCreds) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(userCreds)
                .when()
                .patch(DELETE_URL);
    }

    public Response delete(String authToken) {
        return given()
                .header("Authorization", "Bearer " + authToken)
                .when()
                .delete(DELETE_URL);
    }
}
