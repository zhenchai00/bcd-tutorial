package lab7;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;

public class Symmetric {
    private Cipher cipher;

    public Symmetric(String algorithm) {
        try {
            cipher = Cipher.getInstance(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Symmetric() {
        this("AES");
    }

    public String encrypt(String data, Key key) throws Exception {
        String cipherText = "";
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherBytes = cipher.doFinal(data.getBytes());
        cipherText = Base64.getEncoder().encodeToString(cipherBytes);
        return cipherText;
    }

    public String decrypt (String cipherText, Key key) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
        byte[] dataBytes = cipher.doFinal(cipherBytes);
        return new String(dataBytes);
    }

}
