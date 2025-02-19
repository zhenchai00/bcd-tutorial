package lab8;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class MyKeyPair {
    private String ALGORITHM = "RSA";
    private KeyPairGenerator keyGen;
    private KeyPair keyPair;
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    private MyKeyPair() {
        try {
            keyGen = KeyPairGenerator.getInstance(ALGORITHM);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PublicKey getPublicKey() {
        return publicKey;
    }

    public static PrivateKey getPrivateKey() {
        return privateKey;
    }

    public static void create() {
        MyKeyPair keyMaker = new MyKeyPair();

        // Generate the key pair
        keyMaker.keyPair = keyMaker.keyGen.generateKeyPair();

        // get public key 
        publicKey = keyMaker.keyPair.getPublic();

        // get private key
        privateKey = keyMaker.keyPair.getPrivate();
    }

    public static void put(byte[] keyBytes, String path) {
        File f = new File(path);
        f.getParentFile().mkdirs();
        try {
            Files.write(Paths.get(path), keyBytes, StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
