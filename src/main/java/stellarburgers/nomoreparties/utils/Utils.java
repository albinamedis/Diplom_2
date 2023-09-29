package stellarburgers.nomoreparties.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class Utils {
    public static String randomString (int length) {
        Random random = new Random();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString  = new StringBuilder(length);

        for (int i=0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString .append(characters.charAt(index));
        }

        return randomString .toString();
    }

}
