/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {

    println("primary constructor an initializer")
    val user1 = User1("Trinity")
    println("user1 = ${user1.nickname}")

    val user3 = User3("Morpheus")
    println("user3 = ${user3.nickname}")

    val user4 = User4("Neo")
    println("user4 = ${user4.nickname}")
    println("user4 = ${user4.name}")

    var mailUser = MailUser("Alice")
    println("mailUser: name = ${mailUser.nickname}, isSubscribed = ${mailUser.isSubscribed}")
    mailUser = MailUser("Bob",false)
    println("mailUser: name = ${mailUser.nickname}, isSubscribed = ${mailUser.isSubscribed}")
    mailUser = MailUser("Carol",isSubscribed = false)
    println("mailUser: name = ${mailUser.nickname}, isSubscribed = ${mailUser.isSubscribed}")
    val twitterUser = TwitterUser("Johan")
    println("twitterUser: name = ${twitterUser.nickname}, isSubscribed = ${twitterUser.isSubscribed}")

}

// class with concise primary constructor
class User1(val nickname: String) // val refers to the property generated fo the param

// more verbouse constructor
class User3(_nickname: String) { // constructor with one param initialization
    val nickname = _nickname // property initialization with the param
}

// class with verbose primary constructor
class User4 constructor(_nickname: String) {
    var nickname: String
    val name: String
    // initializer block
    // this code is executed when class is created and is intended to be used with primary constructor
    // used to implement non trivial initialization logic fo the property
    init {
        nickname = _nickname + " The One"
    }
    // class can contain several initializers
    init {
        // underscore in param name is used to distinguish the name of the property nad  param name
        // Java like 'this' keyword pattern is also applicable in this case underscore can be omitted
        this.nickname = _nickname + " The Last One"
        name = "Tom"
    }
}

// class with concise primary constructor
// compiler generates all additional constructors without params that uses all the default values
open class MailUser(val nickname: String,
                    val isSubscribed: Boolean = true) // param with default value

// if class has a superclass, primary constructor needs to initialize superclass
class TwitterUser(nickname: String) : MailUser (nickname)

// class with no explicit constructors has indeed default constructor
open class Button

// and inheritors should explicitly invoke constructor, that's why empty parentheses are used in this case
class EadioButton : Button()

// to forbid instating objects of the class private outside the current file constructors can be used
// NB singletons and static utility classes in Java are represented in Kotlin via another language features - not via private constructors
class Secretive private constructor() {}

