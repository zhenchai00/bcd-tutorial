package lab3;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

import org.apache.commons.codec.binary.Hex;

public class SaltTutorial {
    public static void main(String[] args) {
        String salt = Base64.getEncoder().encodeToString(SaltTutorial.generate());
        System.out.println("Salt: " + new String(salt));
        System.out.println("Hashed: " + SaltTutorial.hash("password", salt.getBytes(), "SHA-256"));
    }

    public static byte[] generate() {
        int size = 64;
        byte[] b = new byte[size];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(b);
        return b;
    }

    private static String hash(String input, byte[] salt, String algorithm) {
        MessageDigest md;
        try {
            // instantiate the MD object
            md = MessageDigest.getInstance(algorithm);

            // fetch input to MD object
            md.update(input.getBytes());
            md.update(salt);

            // digest it 
            byte[] hashBytes = md.digest();
            // convert to Hex format with Hex API from Apache common
            return String.valueOf(Hex.encodeHex(hashBytes));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
