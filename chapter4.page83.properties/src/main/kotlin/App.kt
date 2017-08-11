/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("properties")

    println(PrivateUser("Foo").nickname)
    println(SubscribingUser("Bar@baz.qux").nickname)
    println(FacebookUser(123456).nickname)

    val person = Person("John")
    println("name = ${person.name}, address = ${person.address}")
    person.address = "Earth"
    println("name = ${person.name}, address = ${person.address}")

    val lengthCounter = LengthCounter()
    println("counter = ${lengthCounter.counter}")
    lengthCounter.addWord("foo")
    // lengthCounter.counter = 5 // compile error - private setter
    println("counter = ${lengthCounter.counter}")
}

interface User {
    val nickname: String // abstract property declaration, not a backing field - there is no state in the interface still
    // class implementing this interface need to provide way to obtain the value of this property
}

// #1 way: primary constructor property
class PrivateUser(override val nickname: String) : User // overrides abstract property from superclass

// #2 way: custom getter
class SubscribingUser(val email: String) : User {
    override val nickname: String // there is no backing field to store a value for the property - result is calculated
        get() = email.substringBefore('@')
    // in this case another property is used to provide value for nickname without overriding email
}

// #3: property initializer
class FacebookUser(val accountId: Int) : User {
    override val nickname = "id".plus(accountId.toString()) // backing field is used
}

interface AnotherUser {
    val email: String
    val name: String // properties with getters and setter are also valid until there is no backing field in th interface
        get() = email.substringBefore('@')
    // get() = field.substringBefore('@') compile error - backing filed reference
}

// backing field will be genereted if
// - 'field' is used in the custom accessor
// - default accessor implementation is used fo the property
class Person(val name: String) { // val - constant property
    var address: String = "unspecified" // var - mutable property
        // can only read the value
        get() {
            return "Milky way, ".plus(field)
        }
        // can both read an modify
        set(value) {
            println("Address was changed for $name: $field -> $value") // field stands for backing filed current value
            field = value // updating backing field
        }
}

class LengthCounter {
    var counter: Int = 0
        // private get // compile error - getter visibility must be the same as property visibility
        private set // making setter 'private'

    fun addWord(word: String) {
        counter += word.length
    }
}
