package lab2;

import java.security.MessageDigest;
import org.apache.commons.codec.binary.Hex;

public class hasher {
    private static String hash(String input, String Algorithm) {
        String hashCode = "";
        try {
            MessageDigest md = MessageDigest.getInstance(Algorithm);
            md.update(input.getBytes());

            // digesting ...
            byte[] hashBytes = md.digest();

            // convert the byte[] to String
            // hashCode = Base64.getEncoder().encodeToString(hashBytes);

            // hex format output - recommended!
            hashCode = Hex.encodeHexString(hashBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashCode;
    }

    public static String md5(String input) {
        return hash(input, "md5");
    }

    public static String sha256(String input) {
        return hash(input, "sha256");
    }

    public static String sha384(String input) {
        return hash(input, "sha384");
    }

    public static String sha512(String input) {
        return hash(input, "sha512");
    }
}
