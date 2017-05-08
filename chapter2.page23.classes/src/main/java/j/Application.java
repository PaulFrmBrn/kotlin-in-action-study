package j;

/**
 * @author PaulFrmBrn
 */
public class Application {

    public static void main(String[] args) {
        Person person = new Person("Bob");
        System.out.println("person: " + person);
        System.out.println("person.name: " + person.getName());

        k.Person kPerson = new k.Person("Alex",true);
        System.out.println("kPerson: " + kPerson); // ??? where is Kotlin's default toString ?
        System.out.println("kPerson.name: " + kPerson.getName());
        System.out.println("kPerson.married: " + kPerson.isMarried());




    }

}
