package lab7;

import java.security.Key;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class lab7main {
    public static void main(String[] args) throws Exception {
        Symmetric symm = new Symmetric();

        System.out.println("Predefined secret key method");
        Key secretKey = PredefinedCharsSecretKey.create();
        String secretKeyString = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println("Secret key: " + secretKeyString);

        String msg = "thankyou so much!";

        String encrypted = symm.encrypt(msg, secretKey);
        System.out.println("Encrypted: " + encrypted);

        TimeUnit.SECONDS.sleep(2);

        String decrypted = symm.decrypt(encrypted, secretKey);
        System.out.println("Decrypted: " + decrypted);


        System.out.println("\nRandom secret key method");
        Key randomKey = RandomSecretKey.create();
        String randomKeyString = Base64.getEncoder().encodeToString(randomKey.getEncoded());
        System.out.println("Secret key: " + randomKeyString);

        String msg2 = "thankyou so much!";
        String encrypted2 = symm.encrypt(msg2, randomKey);
        System.out.println("Encrypted: " + encrypted2);

        TimeUnit.SECONDS.sleep(2);

        String decrypted2 = symm.decrypt(encrypted2, randomKey);
        System.out.println("Decrypted: " + decrypted2);
    }
}
