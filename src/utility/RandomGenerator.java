package utility;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Class for generating random string
 */
public class RandomGenerator {
    /**
     *Генерирует рандомную строку
     */
    public static String generateRandomString(){
        SecureRandom secureRandom = new SecureRandom();
        return new BigInteger(100, secureRandom).toString(32);
    }
}
