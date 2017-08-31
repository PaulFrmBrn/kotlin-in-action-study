/**
 * @author PaulFrmBrn
 */
public class StringProcessorRunner {

    public static void runWithNull() {

        StringProcessor stringPrinter = new StringPrinter();
        // IllegalArgumentException: Parameter specified as non-null is null: method StringPrinter.process, parameter value
        stringPrinter.process(null);

    }

}
