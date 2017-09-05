import java.util.List;

/**
 * @author PaulFrmBrn
 */
interface DataParser<T> {
    void parseData(String input,
                   List<T> output,
                   List<String> errors);
}
