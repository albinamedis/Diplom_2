package stellarburgers.nomoreparties;

import java.util.Random;

import static stellarburgers.nomoreparties.utils.Utils.randomString;

public class UserGenerator {

    public static User randomUser() {
        return new User()
                .withEmail(randomString(6)+"@gmail.com")
                .withPassword(randomString(12))
                .withName(randomString(10));
    }

    public static User randomUserWithoutEmail() {
        return new User()
                .withPassword(randomString(12))
                .withName(randomString(10));
    }

    public static User randomUserWithoutPassword() {
        return new User()
                .withEmail(randomString(6)+"@gmail.com")
                .withName(randomString(10));
    }

    public static User randomUserWithoutName() {
        return new User()
                .withEmail(randomString(6)+"@gmail.com")
                .withPassword(randomString(12));
    }
}
