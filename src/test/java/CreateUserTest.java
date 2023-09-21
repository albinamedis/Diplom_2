import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.nomoreparties.CreateUser;
import stellarburgers.nomoreparties.User;
import stellarburgers.nomoreparties.constants.Url;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static stellarburgers.nomoreparties.UserGenerator.*;

public class CreateUserTest {

    private CreateUser createUser = new CreateUser();

    @Before
    public void setUp() {
        RestAssured.baseURI = Url.BASE_URI;
    }

    @Test
    public void createUser(){
        User user = randomUser();
        Response response = createUser.create(user);
        assertEquals("Неверный статус код", HttpStatus.SC_OK, response.statusCode());
    }

    @Test
    public void createUserFalse(){
        User user = randomUser();
        createUser.create(user);
        Response response = createUser.create(user);
        response.then().body("success",equalTo(false));
    }

    @Test
    public void createUserDuplicate() {
        User user = randomUser();
        createUser.create(user);
        Response response = createUser.create(user);
        response.then().body("message", equalTo("User already exists"));
        assertEquals("Неверный статус код", HttpStatus.SC_FORBIDDEN, response.statusCode());
    }

    @Test
    public void createUserWithoutEmail(){
        User user = randomUserWithoutEmail();
        Response response = createUser.create(user);
        assertEquals("Неверный статус код", HttpStatus.SC_FORBIDDEN, response.statusCode());
    }

    @Test
    public void createUserWithoutPassword(){
        User user = randomUserWithoutPassword();
        Response response = createUser.create(user);
        assertEquals("Неверный статус код", HttpStatus.SC_FORBIDDEN, response.statusCode());
    }

    @Test
    public void createUserWithoutName(){
        User user = randomUserWithoutName();
        Response response = createUser.create(user);
        assertEquals("Неверный статус код", HttpStatus.SC_FORBIDDEN, response.statusCode());
    }

}
