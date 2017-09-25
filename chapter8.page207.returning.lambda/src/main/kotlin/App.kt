/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {

    println("returning functions from functions")

    val calculator = getShippingCostCalculator(Delivery.EXPEDITED) // stores return function in a variable
    println("Shipping costs: ${calculator(Order(3))}") // invoking returned function

    val contacts = listOf(Person("John", "Doe", "555-55-55"),
            Person("Jane", "Smith", null))
    val contactsFilter = ContactListFilter()
    with(contactsFilter) {
        // executing 'block' (lambda) on with 'contactsFilter' as a receiver
        prefix = "Jo"
        onlyWithPhoneNumbers = true
    }
    println(contacts.filter(contactsFilter.getPredicate())) // passes function to 'contactsFilter()'

    println("removing duplication")

    val log = listOf(
            SiteVisit("/", 34.0, OS.WINDOWS),
            SiteVisit("/", 22.0, OS.MAC),
            SiteVisit("/login", 12.0, OS.WINDOWS),
            SiteVisit("/signup", 8.0, OS.IOS),
            SiteVisit("/", 16.03, OS.ANDROID)
    )

    val averageWindowsSessionDuration = log
            .filter { it.os == OS.WINDOWS }
            .map( SiteVisit::duration )
            .average()
    println("averageWindowsSessionDuration = $averageWindowsSessionDuration")

    // extracting platform as a parameter - common code operates with 'os: OS' param
    fun List<SiteVisit>.averageDurationFor(os: OS) =
            this.filter { it.os == os }.map( SiteVisit::duration ).average()

    println("averageDurationFor WIN = ${log.averageDurationFor(OS.WINDOWS)}")
    println("averageDurationFor MAC = ${log.averageDurationFor(OS.MAC)}")

    // extracting platform as a parameter - common code operates with 'predicate: (SiteVisit) -> Boolean' param
    fun List<SiteVisit>.averageDurationForPredicate(predicate: (SiteVisit) -> Boolean) =
            this.filter ( predicate ).map( SiteVisit::duration ).average()
    println("averageDurationFor mobile platforms = ${log.averageDurationForPredicate { it.os in setOf(OS.ANDROID,OS.IOS) }}" )
    println("averageDurationFor IOS signups = ${log.averageDurationForPredicate { it.os == OS.IOS && it.path == "/signup" }}" )

}

enum class OS { WINDOWS, LINUX, MAC, IOS, ANDROID }
data class SiteVisit(val path: String, val duration: Double, val os: OS)


data class Person(val firstName: String, val lastName: String, val phoneNumber: String?)
class ContactListFilter {
    var prefix: String = ""
    var onlyWithPhoneNumbers = false

    fun getPredicate(): (Person) -> Boolean {
        val startsWithPrefix =
                { person: Person -> person.firstName.startsWith(prefix) || person.lastName.startsWith(prefix) }
        if (!onlyWithPhoneNumbers) {
            return startsWithPrefix // return a variable of function type
        }
        return { startsWithPrefix(it) && it.phoneNumber != null } // returns lambda
    }

}

enum class Delivery { STANDARD, EXPEDITED }
class Order(val itemCount: Int)

fun getShippingCostCalculator(delivery: Delivery): (Order) -> Double { // declaring function returning a function

    if (delivery == Delivery.EXPEDITED) {
        return { order -> 6 + 2.1 * order.itemCount } // return first lambda from function
    }
    return { order -> 1.2 * order.itemCount } // return second lambda from function

}

