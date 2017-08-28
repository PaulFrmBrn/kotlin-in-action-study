/**
 * @author paul
 */
fun main(args: Array<String>) {
    strLen("a")
    // strLen(null) // compilation error
    strLenSafe("a")
    strLenSafe(null)

    val x: String? = null
    // val y: String = x // compilation error

    // kotlin null-checks are performed at compiletime, so there is no runtime overhead

    // in Java without additional checks it is unclear what can be done with variable of reference type
    // there different options for 'null' value and the instance of certain type

    // in Java @NotNull + @Nullable or Optional can be used to avoid NPE

    // 1. 'safe-call' operator
    // return null if calling method on null-reference or result of method call otherwise
    val string: String? = null;
    var toUpperCase: String? = string?.toUpperCase() // the result type is String?
    toUpperCase = if (string == null) null else string.toUpperCase() // equivalent
    // toUpperCase = string.toUpperCase() // cimpilation error

    val manager = Employee("Foo", null)
    println(managerName(manager))
    println(managerName(Employee("Bar",manager)))
    println(Person("Dmitry",null).countryName())

    // 2. 'Elvis operator' (null-coalescing operator)
    // provides default values instead of null
    val country: String? = null
    var country2 = country?:"Unknown" // the result type is String
    country2 = if (country != null) country else "Unknown" // equivalent
    fun strLenSafe(string: String?): Int = string?.length ?: 0
    println(strLenSafe("abc"))
    println(strLenSafe(null))
    println(Person("Dmitry",null).countryNameElvis())

    val address = Address("Mira street, 1",196000,"St.Petersburg","Russia")
    val company = Company("Ajax",address)
    val dmitry = Person("Dmitry",company)
    val valdislav = Person("Valdislav",null)
    dmitry.printShippingLabel()
    // valdislav.printShippingLabel() // runtime error:
    //  but meaningful 'Exception in thread "main" java.lang.IllegalArgumentException: No address'
    // not NullPointerException

}

class Employee(val name: String, val manager: Employee?)
fun managerName(employee: Employee): String? = employee.manager?.name // using of safe-call

class Address(val street: String, val zipCode: Int, val city: String, val country: String)
class Company(val name: String, val address: Address?)
class Person(val name: String, val company: Company?)
fun Person.countryName(): String {
    val country = this.company?.address?.country // chaining safe-call operator can be convenient
    return if (country != null) country else "Unknown"
}
fun Person.countryNameElvis() =
    this.company?.address?.country ?: "Unknown" // chaining safe-call operator can be convenient and Elvis operator
fun Person.printShippingLabel() {
    val address = this.company?.address ?: throw IllegalArgumentException("No address") // 'throw' works as expression
    with (address) { // is non-null
        println(street)
        println("$zipCode $city $country")
    }
}

// all regular types are non-null by default
fun strLen(string: String) = string.length
// variables of types marked with '?' can store null references
fun strLenSafe(string: String?) =
        if (string != null) string.length else 0 // by adding null check, the code compiles
// fun strLenSafe(string: String?)= string.length // compilation error