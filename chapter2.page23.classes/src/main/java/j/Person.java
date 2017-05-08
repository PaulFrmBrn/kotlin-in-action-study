package j;

/**
 * @author PaulFrmBrn
 */
// value object concept - only data with no code
public class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
