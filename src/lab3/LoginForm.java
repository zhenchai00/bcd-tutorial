package lab3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LoginForm {
    private static final String PASSWORD_FILE = "src/lab3/hashed_password.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to login system!");
        File file = new File(PASSWORD_FILE);

        if (!file.exists()) {
            System.out.println("No registered user found. Please register.");
            System.out.println("Enter your password for registration:");
            String password = scanner.nextLine();

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            writeHashedPasswordToFile(hashedPassword);
            System.out.println("Registered successfully!");
        } else {
            System.out.println("Enter your username:");
            String username = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();

            if (authenticate(username, password)) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed!");
            }
        }
        scanner.close();
    }

    private static void writeHashedPasswordToFile(String hashedPassword) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PASSWORD_FILE));
            writer.write(hashedPassword);
            writer.newLine();
            System.out.println("Hashed password saved to file.");
            writer.close();
        } catch (IOException e) {
            System.err.println("Error saving hashed password to file: " + e.getMessage());
        }
    }

    public static boolean authenticate(String username, String password) {
        String storedHashedPassword = readHashedPasswordFromFile();
        if (storedHashedPassword == null) {
            return false;
        }
        return BCrypt.checkpw(password, storedHashedPassword);
    }

    private static String readHashedPasswordFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PASSWORD_FILE));
            String hashedPassword = reader.readLine();
            reader.close();
            return hashedPassword;
        } catch (IOException e) {
            System.err.println("Error reading hashed password from file: " + e.getMessage());
            return null;
        }
    }
}
