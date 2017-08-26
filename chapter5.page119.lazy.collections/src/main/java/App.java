import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author paul
 */
public class App {

    public static void main(String[] args) {

        System.out.println("Streams");

        System.out.println("\n" + Stream.of(1,2,3,4)
                .map(element -> {
                    System.out.print("\nmap(" + element + ") ");
                    return element * element;
                })
                .filter(element -> {
                    System.out.print("filter(" + element + ") ");
                    return element > 3;
                })
                .map(element -> {
                    System.out.print("map2(" + element + ") ");
                    return element+1;
                })
                .collect(Collectors.toList())
        );

    }

}
