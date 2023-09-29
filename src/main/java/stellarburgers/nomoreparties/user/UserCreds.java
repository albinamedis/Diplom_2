package stellarburgers.nomoreparties.user;

import static stellarburgers.nomoreparties.utils.Utils.*;

public class UserCreds {

    private String email;
    private String password;
    private String name;

    public UserCreds(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserCreds(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static UserCreds credsFrom(User user) {
        return new UserCreds(user.getEmail(), user.getPassword());
    }

    public static UserCreds credsFromUser(User user) {
        return new UserCreds(user.getEmail(), user.getPassword(), user.getName());
    }

    public static UserCreds credsFromNullEmail(User user) {
        return new UserCreds(null, user.getPassword());
    }

    public static UserCreds credsFromNullPassword(User user) {
        return new UserCreds(user.getEmail(),null);
    }

    public static UserCreds credsFromRandom(User user) {
        return new UserCreds(randomString(8)+"@gmail.com",randomString(12));
    }

    public static UserCreds credsFromRandomEmail(User user) {
        return new UserCreds(randomString(6)+"@gmail.com", user.getPassword(), user.getName());
    }

    public static UserCreds credsFromRandomPassword(User user) {
        return new UserCreds(user.getEmail(), randomString(10), user.getName());
    }

    public static UserCreds credsFromRandomName(User user) {
        return new UserCreds(user.getEmail(), user.getPassword(), randomString(8));
    }

    public static UserCreds credsFromRandomUser(User user) {
        return new UserCreds(randomString(8)+"@gmail.com",randomString(12), randomString(7));
    }
}
