import java.util.ArrayList;
import java.util.List;
import lab2.*;

public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");
        String str1 = "alice";
        String str2 = "bob";

        // hashcode for String object
        System.out.println("Hash code for String Object");
        System.out.println(String.join(":", str1, String.valueOf(str1.hashCode())));
        System.out.println(String.join(":", str2, String.valueOf(str2.hashCode())));

        System.out.println("\n------------\n");

        // string collection
        List<String> list = new ArrayList<>();
        list.add(str1);
        list.add(str2);
        System.out.println("List = " + list);
        System.out.println("List hash code = " + list.hashCode());

        System.out.println("\n------------\n");
        System.out.println("Hashing Algorithms individual");
        String input = "username5";
        System.out.println("unsalted hash = MD5|" + hasher.md5(input));
        System.out.println("unsalted hash = sha256|" + hasher.sha256(input));
        System.out.println("unsalted hash = sha384|" + hasher.sha384(input));
        System.out.println("unsalted hash = sha512|" + hasher.sha512(input));
    }

}
