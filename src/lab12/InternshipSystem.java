package lab12;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import lab8.MyKeyPair;

public class InternshipSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Generate key pair
        System.out.println("Generating key pair...");
        MyKeyPair.create();
        MyKeyPair.put(MyKeyPair.getPublicKey().getEncoded(), "src/lab12/resource/keys/public.key");
        MyKeyPair.put(MyKeyPair.getPrivateKey().getEncoded(), "src/lab12/resource/keys/private.key");
        System.out.println("Key pair generated and saved.");

        // Step 2: Applicant sends job application
        System.out.println("Enter your job application text:");
        String applicationText = scanner.nextLine();
        MySignature signer = new MySignature();
        byte[] signature = signer.getSignature(applicationText, MyKeyPair.getPrivateKey());
        System.out.println("Application signed successfully.");

        // Save application and signature to file
        try {
            Files.write(Paths.get("src/lab12/resource/application.txt"), applicationText.getBytes());
            Files.write(Paths.get("src/lab12/resource/signature.txt"), signature);
            System.out.println("Application and signature saved.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Step 3: Officer verifies application
        System.out.println("Officer: Verifying application...");
        try {
            String loadedApplication = Files.readString(Paths.get("src/lab12/resource/application.txt"));
            byte[] loadedSignature = Files.readAllBytes(Paths.get("src/lab12/resource/signature.txt"));

            boolean isValid = signer.isTextAndSignatureValid(loadedApplication, loadedSignature, MyKeyPair.getPublicKey());
            if (isValid) {
                System.out.println("Application is valid.");
                System.out.println("Do you want to accept the application? (yes/no)");
                String decision = scanner.nextLine();
                if (decision.equalsIgnoreCase("yes")) {
                    System.out.println("Application accepted.");
                } else {
                    System.out.println("Application rejected.");
                }
            } else {
                System.out.println("Application is invalid. Rejecting application.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
