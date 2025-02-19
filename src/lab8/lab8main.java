package lab8;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class lab8main {
    public static void main(String[] args) throws Exception {
        MyKeyPair.create();
        
        byte[] publicKey = MyKeyPair.getPublicKey().getEncoded();
        byte[] privateKey = MyKeyPair.getPrivateKey().getEncoded();

        System.out.println("Public key: " + Base64.getEncoder().encodeToString(publicKey));
        System.out.println("Private key: " + Base64.getEncoder().encodeToString(privateKey));

        MyKeyPair.put(publicKey, "keys/PublicKey");
        MyKeyPair.put(privateKey, "keys/PrivateKey");

        System.out.println("\n\n");

        PublicKey pubKey = KeyAccess.getPublicKey("keys/PublicKey");
        PrivateKey privKey = KeyAccess.getPrivateKey("keys/PrivateKey");

        System.out.println("Public key: " + Base64.getEncoder().encodeToString(pubKey.getEncoded()));
        System.out.println("Private key: " + Base64.getEncoder().encodeToString(privKey.getEncoded()));

        System.out.println("\n\n");

        Asymmetric rsa = new Asymmetric();
        String plainText = "Hello, World!";
        System.out.println("Plain Text: " + plainText);
        String encryptedText = rsa.encrypt(plainText, pubKey);
        System.out.println("Encrypted: " + encryptedText);
        String decryptedText = rsa.decrypt(encryptedText, privKey);
        System.out.println("Decrypted: " + decryptedText);

    }
}
