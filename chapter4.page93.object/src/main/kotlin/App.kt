

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("object")

    // 'object' defines a class and creates an instance

    // ways to use
    // 1. as Singleton replacement
    Payroll.allEmployees.add(Employee("John",5000))
    Payroll.allEmployees.add(Employee("Jake",5500))
    Payroll.allEmployees.add(Employee("Jim",5700))
    Payroll.calculateSalary()

    println(Employee.CaseInsensitiveComparator.compare("LoW","lOw"))

    // 2. as 'static' replacement - 'companion objects'
    println("simple = ${User.newSimpleUser("user@exmaple.com").name}")
    // newSimpleUser can be invoked via class name - like static factory
    println("subscribing = ${User.newSubscribingUser("user@exmaple.com").name}")

    // 3. companion object as regular objects
    println("new person = ${Person.create("FooBar").name}")
    println("new person = ${Person.Loader.create("FooBar").name}")
    // println("new person = ${Person.Companion.create("FooBar").name}") // if not named 'Companion' is used

    val personLoaded = loadFromString(Person.Loader, "Foo")
    println("Loaded person name = ${personLoaded.name}")
    // containing object  can be passed as an object of interface witch is implemented by companion object
    val personLoaded2 = loadFromString(Person, "Foo") //
    println("Loaded person 2 name = ${personLoaded2.name}")

    println("User created from string = ${User.fromString("FooBar").name}")

    var counter: Int = 0

    // Object expressions as Java's analogue of anonymous classes
    val somVariable = object : SomeInterface { // can implemet multiple interfaces, unlike Java's single
        override fun method1(): String {
            counter++; // unlike Java, non final variables can be accessed
            return "one"
        }

        override fun method2(): String {
            counter++;
            return "two"
        }

    }

}

fun <T>loadFromString(factory: Factory<T>, value: String): T {
     return factory.create(value)
}

data class Person(val name: String) {
    // can be named and implement interfaces
    companion object Loader : Factory<Person> {
        override fun create(value: String): Person {
            return Person(value)
        }
    }
}



interface Factory<T> {
    fun create(value: String) : T
}


data class Employee(val name: String, val salary: Int) {

    // implemented as singleton in Java.
    // So can be called from Java like this: CaseInsensitiveComparator.INSTANCE.compare

    // can implement interfaces and extend classes
    // can be nested. There will be only one instance - no matter how many instances of containing classes are
    // can not be inner - compilation error

    /*inner*/ object CaseInsensitiveComparator : Comparator<String> {
        override fun compare(first: String, second: String): Int {
            return first.toLowerCase().compareTo(second.toLowerCase())
        }

    }
}

// 'object declaration' = class declaration + single instance. Like singleton
object Payroll { // can implement interfaces
    init {
        println("Payroll created")
    }
    // properties, functions, initializers are allowed
    // constructors are forbidden - object will be created when first called
    val allEmployees = arrayListOf<Employee>()
    fun calculateSalary() {
        for (person in allEmployees) {
            println("${person.name} recieved $${person.salary}")
        }
    }
}

// perfect candidate for static factory
class User private constructor(val name: String ) { // primary constructor is private
    companion object {
        fun newSubscribingUser(email: String) = User(email.substringBefore('@'))
        fun newSimpleUser(name: String) = User(name) // has access to the private members, e.g. constructors
    }
}

// Extension functions can be used with companion objects
fun User.Companion.fromString(name: String ) : User {
    return newSimpleUser(name)
}

interface SomeInterface {
    fun method1(): String
    fun method2(): String
}
