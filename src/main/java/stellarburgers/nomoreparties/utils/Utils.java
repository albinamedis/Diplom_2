package stellarburgers.nomoreparties.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class Utils {
    public static String randomString (int length) {
        Random random = new Random();
        int leftLimit = 97;
        int rightLimit = 122;
        StringBuilder buffer = new StringBuilder(length);

        for (int i=0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int)(random.nextFloat() * (float)(rightLimit));
            buffer.append(Character.toChars(randomLimitedInt));
        }

        return buffer.toString();
    }

}
