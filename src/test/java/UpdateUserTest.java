import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.nomoreparties.constants.Url;
import stellarburgers.nomoreparties.user.CreateUser;
import stellarburgers.nomoreparties.user.User;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertEquals;
import static stellarburgers.nomoreparties.user.UserCreds.*;
import static stellarburgers.nomoreparties.user.UserGenerator.randomUser;

public class UpdateUserTest {

    private CreateUser createUser = new CreateUser();
    private String authToken;

    @Before
    public void setUp() {
        RestAssured.baseURI = Url.BASE_URI;
    }

    @Test
    public void updateEmail(){
        User user = randomUser();
        Response response = createUser.create(user);
        authToken = response.path("accessToken");
        Response updateResponse = createUser.update(credsFromRandomEmail(user), authToken);
        updateResponse.then().body("success", equalTo(true));
        updateResponse.then().body("user.email", not(isEmptyOrNullString()));
        updateResponse.then().body("user.name", not(isEmptyOrNullString()));
        assertEquals("Email не обновлен. Неверный статус код", HttpStatus.SC_OK, updateResponse.statusCode());
    }

    @Test
    public void updateEmailUseDublicate(){
        User userDublicate = randomUser();
        createUser.create(userDublicate);

        User user = randomUser();
        Response response = createUser.create(user);
        authToken = response.path("accessToken");
        Response updateResponse = createUser.update(credsFromUser(userDublicate), authToken);
        updateResponse.then().body("success", equalTo(false));
        updateResponse.then().body("message", equalTo("User with such email already exists"));
        assertEquals("Email не обновлен. Неверный статус код", HttpStatus.SC_FORBIDDEN, updateResponse.statusCode());
    }

    @Test
    public void updateName(){
        User user = randomUser();
        Response response = createUser.create(user);
        authToken = response.path("accessToken");
        Response updateResponse = createUser.update(credsFromRandomName(user), authToken);
        updateResponse.then().body("success", equalTo(true));
        updateResponse.then().body("user.email", not(isEmptyOrNullString()));
        updateResponse.then().body("user.name", not(isEmptyOrNullString()));
        assertEquals("Email не обновлен. Неверный статус код", HttpStatus.SC_OK, updateResponse.statusCode());
    }

    @Test
    public void updatePassword(){
        User user = randomUser();
        Response response = createUser.create(user);
        authToken = response.path("accessToken");
        Response updateResponse = createUser.update(credsFromRandomPassword(user), authToken);
        updateResponse.then().body("success", equalTo(true));
        updateResponse.then().body("user.email", not(isEmptyOrNullString()));
        updateResponse.then().body("user.name", not(isEmptyOrNullString()));
        assertEquals("Email не обновлен. Неверный статус код", HttpStatus.SC_OK, updateResponse.statusCode());
    }

    @Test
    public void updateEmailWithoutAuth(){
        User user = randomUser();
        Response response = createUser.create(user);
        authToken = response.path("accessToken");
        Response updateResponse = createUser.updateWithoutAuth(credsFromRandomEmail(user));
        updateResponse.then().body("success", equalTo(false));
        updateResponse.then().body("message", equalTo("You should be authorised"));
        assertEquals("Email не обновлен. Неверный статус код", HttpStatus.SC_UNAUTHORIZED, updateResponse.statusCode());
    }

    @Test
    public void updatePasswordWithoutAuth(){
        User user = randomUser();
        Response response = createUser.create(user);
        authToken = response.path("accessToken");
        Response updateResponse = createUser.updateWithoutAuth(credsFromRandomPassword(user));
        updateResponse.then().body("success", equalTo(false));
        updateResponse.then().body("message", equalTo("You should be authorised"));
        assertEquals("Email не обновлен. Неверный статус код", HttpStatus.SC_UNAUTHORIZED, updateResponse.statusCode());
    }

    @Test
    public void updateNameWithoutAuth(){
        User user = randomUser();
        Response response = createUser.create(user);
        authToken = response.path("accessToken");
        Response updateResponse = createUser.updateWithoutAuth(credsFromRandomName(user));
        updateResponse.then().body("success", equalTo(false));
        updateResponse.then().body("message", equalTo("You should be authorised"));
        assertEquals("Email не обновлен. Неверный статус код", HttpStatus.SC_UNAUTHORIZED, updateResponse.statusCode());
    }

    @After
    public void tearDown() {
        createUser.delete(authToken);
    }
}
