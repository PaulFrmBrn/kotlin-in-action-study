// extension function is a static method, where receiver object is a first argument
import static string.extension.StringExtensionKt.lastChar;

/**
 * @author PaulFrmBrn
 */
public class App {

    public static void main(String[] args) {
        System.out.println(lastChar("Hello, World!"));
    }
}
