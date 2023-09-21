import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.nomoreparties.constants.Url;
import stellarburgers.nomoreparties.user.CreateUser;
import stellarburgers.nomoreparties.user.User;
import stellarburgers.nomoreparties.user.UserCreds;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertEquals;
import static stellarburgers.nomoreparties.user.UserCreds.*;
import static stellarburgers.nomoreparties.user.UserGenerator.*;

public class UserLoginTest {

    private CreateUser createUser = new CreateUser();
    private String authToken;

    @Before
    public void setUp() {
        RestAssured.baseURI = Url.BASE_URI;
    }

    @Test
    public void userLoginStatus200() {
        User user = randomUser();
        Response response = createUser.create(user);
        assertEquals("Неверный статус код", HttpStatus.SC_OK, response.statusCode());
        Response loginResponse = createUser.login(credsFrom(user));
        authToken = response.path("accessToken");
        assertEquals("Пользователь не авторизован", HttpStatus.SC_OK, loginResponse.statusCode());
        loginResponse.then().assertThat().body("success", equalTo(true));
        loginResponse.then().assertThat().body("accessToken", not(isEmptyOrNullString()));
    }

    @Test
    public void userLoginWithoutLogin() {
        User user = randomUser();
        Response response = createUser.create(user);
        Response loginResponse = createUser.login(credsFromNullEmail(user));
        authToken = response.path("accessToken");
        assertEquals("Неверный статус код", HttpStatus.SC_UNAUTHORIZED, loginResponse.statusCode());
    }

    @Test
    public void userLoginWithoutPassword() {
        User user = randomUser();
        Response response = createUser.create(user);
        Response loginResponse = createUser.login(credsFromNullPassword(user));
        authToken = response.path("accessToken");
        assertEquals("Неверный статус код", HttpStatus.SC_UNAUTHORIZED, loginResponse.statusCode());
    }

    @Test
    public void userLoginRandom() {
        User user = randomUser();
        Response response = createUser.create(user);
        Response loginResponse = createUser.login(credsFromRandom(user));
        authToken = response.path("accessToken");
        assertEquals("Неверный статус код", HttpStatus.SC_UNAUTHORIZED, loginResponse.statusCode());
    }

    @After
    public void tearDown() {
        createUser.delete(authToken);
    }
}
