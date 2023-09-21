package stellarburgers.nomoreparties.user;

import static stellarburgers.nomoreparties.utils.Utils.randomString;

public class UserCreds {

    private String email;
    private String password;

    public UserCreds(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static UserCreds credsFrom(User user) {
        return new UserCreds(user.getEmail(), user.getPassword());
    }

    public static UserCreds credsFromNullEmail(User user) {
        return new UserCreds(null, user.getPassword());
    }

    public static UserCreds credsFromNullPassword(User user) {
        return new UserCreds(user.getEmail(),null);
    }

    public static UserCreds credsFromRandom(User user) {
        return new UserCreds(randomString(8),randomString(12));
    }
}
