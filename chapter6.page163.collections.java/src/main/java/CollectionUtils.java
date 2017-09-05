import java.util.List;

/**
 * @author PaulFrmBrn
 */
public class CollectionUtils {
    public static List<String> uppercaseAll(List<String> items) {
        for (int i = 0; i < items.size(); i++) {
            items.set(i, items.get(i).toUpperCase());
        }
        // items.add(null); // runtime error: UnsupportedOperationException for Kotlin read-only list
        return items;
    }

    public static List<String> insertNull(List<String> items) {
        items.add(null); // no error for Kotlin mutable list
        // items.add(null); // runtime error: UnsupportedOperationException for Kotlin read-only list
        return items;
    }

}
