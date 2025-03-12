package lab12;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import lab8.MyKeyPair;

public class lab12main {
    public static void main(String[] args) {
        MyKeyPair.create();
        PublicKey publicKey = MyKeyPair.getPublicKey();
        PrivateKey privateKey = MyKeyPair.getPrivateKey();
        MySignature mySignature = new MySignature();
        String text = "simple digital signature demo";

        System.out.println("Text: " + text + "\n\n");

        byte[] signature = mySignature.getSignature(text, privateKey);
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));
        
        System.out.println("\n\n");

        boolean isSignatureValid = mySignature.isTextAndSignatureValid(text, signature, publicKey);
        System.out.println("Is signature valid? " + isSignatureValid);
    }
}
