import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.nomoreparties.constants.Url;
import stellarburgers.nomoreparties.orders.Ingredients;
import stellarburgers.nomoreparties.orders.Order;
import stellarburgers.nomoreparties.orders.RandomIngredients;
import stellarburgers.nomoreparties.user.CreateUser;
import stellarburgers.nomoreparties.user.User;
import stellarburgers.nomoreparties.utils.Utils;

import javax.sound.midi.Soundbank;
import java.net.StandardSocketOptions;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertEquals;
import static stellarburgers.nomoreparties.user.UserCreds.credsFrom;
import static stellarburgers.nomoreparties.user.UserGenerator.randomUser;

public class CreateOrder {

    private String authToken;
    private List<String> idList;
    private CreateUser createUser = new CreateUser();
    private Order order = new Order();
    private RandomIngredients randomIngredients = new RandomIngredients();

    @Before
    public void setUp() {
        RestAssured.baseURI = Url.BASE_URI;

        // получение общего списка ингредиентов
        Response response = order.allIngredients();
        String jsonString = response.body().asString();
        // создание списка id ингредиентов
        Ingredients ingredients = new Ingredients();
        idList = ingredients.createListIngredients(jsonString);
    }

    @Test
    public void createOrderAuth(){
        User user = randomUser();
        createUser.create(user);
        Response loginResponse = createUser.login(credsFrom(user));
        authToken = loginResponse.path("accessToken");

    }

    @Test
    public void createOrdersWithAuth() {
        User user = randomUser();
        createUser.create(user);
        Response loginResponse = createUser.login(credsFrom(user));
        authToken = loginResponse.path("accessToken");
        // получение рандомного списка id ингредиентов
        String requestBody = randomIngredients.randomStringIngredients(idList);
        Response orderResponse = order.createOrdersWithToken(requestBody, authToken);
        orderResponse.then().assertThat().body("success", equalTo(true));
        orderResponse.then().body("order.ingredients", not(isEmptyOrNullString()));
        assertEquals("Заказ не создан", HttpStatus.SC_OK, orderResponse.statusCode());
    }

    @Test
    public void createOrdersWithAuthRandomIngredients() {
        User user = randomUser();
        createUser.create(user);
        Response loginResponse = createUser.login(credsFrom(user));
        authToken = loginResponse.path("accessToken");
        // рандомный ингредиент
        String requestBody = "\"aaaaaa012345aaaaaa012345\"";
        Response orderResponse = order.createOrdersWithToken(requestBody, authToken);
        orderResponse.then().assertThat().body("success", equalTo(false));
        orderResponse.then().body("message", equalTo("One or more ids provided are incorrect"));
        assertEquals("Заказ не создан", HttpStatus.SC_BAD_REQUEST, orderResponse.statusCode());
    }

    @Test
    public void createOrdersWithAuthNullIngredients() {
        User user = randomUser();
        createUser.create(user);
        Response loginResponse = createUser.login(credsFrom(user));
        authToken = loginResponse.path("accessToken");
        // отсутствие списка ингредиентов
        String requestBody = null;
        Response orderResponse = order.createOrdersWithToken(requestBody, authToken);
        orderResponse.then().assertThat().body("success", equalTo(false));
        orderResponse.then().body("message", equalTo("One or more ids provided are incorrect"));
        assertEquals("Заказ не создан", HttpStatus.SC_BAD_REQUEST, orderResponse.statusCode());
    }

    @Test
    public void createOrdersWithoutAuth() {
        // получение рандомного списка id ингредиентов
        String requestBody = randomIngredients.randomStringIngredients(idList);
        Response orderResponse = order.createOrdersWithoutToken(requestBody);
        orderResponse.then().assertThat().body("success", equalTo(true));
        assertEquals("Заказ не создан", HttpStatus.SC_OK, orderResponse.statusCode());
    }

    @Test
    public void createOrdersWithoutAuthRandomIngredients(){
        // рандомный ингредиент
        String requestBody = "\"aaaaaa012345aaaaaa012345\"";
        Response orderResponse = order.createOrdersWithoutToken(requestBody);
        orderResponse.then().assertThat().body("success", equalTo(false));
        orderResponse.then().body("message", equalTo("One or more ids provided are incorrect"));
        assertEquals("Заказ не создан", HttpStatus.SC_BAD_REQUEST, orderResponse.statusCode());
    }

    @Test
    public void createOrdersWithoutAuthNullIngredients(){
        // отсутствие списка ингредиентов
        String requestBody = null;
        Response orderResponse = order.createOrdersWithoutToken(requestBody);
        orderResponse.then().assertThat().body("success", equalTo(false));
        orderResponse.then().body("message", equalTo("One or more ids provided are incorrect"));
        assertEquals("Заказ не создан", HttpStatus.SC_BAD_REQUEST, orderResponse.statusCode());
    }

    @After
    public void tearDown() {
        createUser.delete(authToken);
    }

}
