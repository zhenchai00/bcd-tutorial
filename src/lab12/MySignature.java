package lab12;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class MySignature {
    public final static String ALGORITHM = "SHA256WithRSA";
    private Signature sign;

    public MySignature() {
        try {
            sign = Signature.getInstance(ALGORITHM);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getSignature(String text, PrivateKey key) {
        try {
            sign.initSign(key);
            sign.update(text.getBytes());
            return sign.sign();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isTextAndSignatureValid(String text, byte[] signature, PublicKey key) {
        try {
            sign.initVerify(key);
            sign.update(text.getBytes());
            return sign.verify(signature);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
