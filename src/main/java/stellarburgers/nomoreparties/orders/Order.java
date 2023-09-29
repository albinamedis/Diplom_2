package stellarburgers.nomoreparties.orders;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Order {
    private static final String INGREDIENT_URL = "/api/ingredients";
    private static final String CREATE_URL = "/api/orders";

    public Response allIngredients() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(INGREDIENT_URL);
    }

    public Response createOrdersWithoutToken(String listIngredients) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body("{\"ingredients\": ["+listIngredients+"]}")
                .when()
                .post(CREATE_URL);
    }

    public Response createOrdersWithToken(String listIngredients,String authToken) {
        return given()
                .header("Content-type", "application/json")
                .and().header("Authorization", authToken)
                .and()
                .body("{\"ingredients\": ["+listIngredients+"]}")
                .when()
                .post(CREATE_URL);
    }

    public Response getOrdersWithoutToken() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(CREATE_URL);
    }

    public Response getOrdersWithToken(String authToken) {
        return given()
                .header("Content-type", "application/json")
                .and().header("Authorization", authToken)
                .when()
                .get(CREATE_URL);
    }


}
