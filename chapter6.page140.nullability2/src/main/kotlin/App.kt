import java.awt.event.ActionEvent
import javax.swing.AbstractAction
import javax.swing.JList

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("nullability 2")

    // 'as?' operator
    // tries to cast to a specific type and return null, if value isn't of proper type
    val person1 = Person("John","Smith")
    val person2 = Person("John","Smith")
    println(person1 == person2)
    println(person1.equals(42))

    // not-null assertions
    // Convert variable of nullable type to non-null type. throws KotlinNPE, if variable value is null
    fun ignoreNulls(nullable: String?) {
        val nonNullString: String = nullable!! // KotlinNPE points the assertion line[18]
        println(nonNullString) // not the line that triggered real NPE
    }
    ignoreNulls("Hello, world!")
    // ignoreNulls(null) // runtime error
    CopyRowAction(JList())
    // NB constructions like this make unclear the reason of KotlinNPE - it's better to avoid using them
    // person.company!!.address!!.country

    // let function
    // converts object into parameter of lambda. can be used together with safe-call operator
    fun sendEmailTo(email: String) {
        println("Sending email to $email")
    }
    var email: String? = "email@example.com"
    email?.let { email -> sendEmailTo(email) } // safe-call operator converts String? to String and inside lambda there can not be a null value
    email?.let { sendEmailTo(it) }
    if (email != null) sendEmailTo(email) // analogue
    email = null
    email?.let { sendEmailTo(it) } // lambda won't be called at all - no exceptions raised, neither output printed

    // late-initialized properties
    // ome frameworks (testing, DI, etc.) initialize variables/properties not through the constructor but calling special
    // initialization methods. So such variables should be declared of nullable types
    val myTest = MyTest()
    myTest.setUp() // initialization of myService
    myTest.testAction() // so myService inside MyTest shuold now use '!!.' or '?.'
    // 'lateinit' helps avoid using null checks

    val myTestLateinit = MyTestLateinit()
    // myTestLateinit.testAction() // runtime exception if lateinit var is not initialized and called
    // UninitializedPropertyAccessException: lateinit property myService has not been initialized
    myTestLateinit.setUp()
    myTestLateinit.testAction()

}

class MyService {
    fun performanceAction() = "foo"
}

class MyTest {
    private var myService: MyService? = null // of nullable type. always a var
    // backing field has the same visibility as lateinit property
    fun setUp() {
        myService = MyService()
    }
    fun testAction() {
        if (myService!!.performanceAction() == "foo") {
            println("Test is OK")
        }
    }
}

class MyTestLateinit {
    private lateinit var myService: MyService // variable of non-null type without initialization
    fun setUp() {
        myService = MyService()
    }
    fun testAction() {
        if (myService.performanceAction() == "foo") { // no null checks
            println("Test is OK")
        }
    }
}

class Person(val firstName: String, val lastName: String) {
    override fun equals(other: Any?): Boolean {
        // return null if other is not of type Person and then Elvis operator returns false
        val otherPerson = other as? Person ?: return false
        return otherPerson.firstName == this.firstName && other.lastName == this.lastName
    }
    override fun hashCode(): Int = firstName.hashCode() * 37 + lastName.hashCode()
}

class CopyRowAction(val list: JList<String>): AbstractAction() {

    override fun isEnabled(): Boolean = list.selectedValue != null

    override fun actionPerformed(e: ActionEvent) {
        // actionPerformed() is called only isEnabled() = true. programmer knows that, but compiler don't
        // not-null assertion is the way to ensure the compiler that operation is null-safe
        val value = list.selectedValue!!
        println(value)
    }

}