package lab7;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;

public class RandomSecretKey {
    private static final String ALGORITHM = "AES";
    public static Key create() {
        short keySize = 256;
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
            keyGen.init(keySize, new SecureRandom());
            return keyGen.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
