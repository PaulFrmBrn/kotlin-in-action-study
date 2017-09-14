/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("delegation")

    val john = Person("John")
    println(john.emails)

    val jane = Person("Jane")
    println(jane.emailsViaDelegate)

}

data class Email(val theme: String)

data class Person(val name: String) {

    // Kotlin's language support for implementing delegation
    // concise and not threadsafe
    // --------------------------------
    // lazy() - library function witch return threadsafe Delegate object with getValue() method
    val emailsViaDelegate: List<Email> by lazy { loadEmail(this)}

    // implementing delegation by hands
    // verbouse and not threadsafe
    // --------------------------------

    // backing property
    private var _emails: List<Email>? = null // Person class delegates retrieving email to this property

    // this property provides access to delegate property
    val emails: List<Email>
        get() {
            if (_emails == null) {
                _emails = loadEmail(this) // loads the data on first access
            }
            return _emails!!
        }

}

fun loadEmail(person: Person): List<Email> {
    val emailsFromDB = listOf(Email("first"), Email("second"))
    println("Loading Emails for person $person...")
    return emailsFromDB
}
