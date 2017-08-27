/**
 * @author paul
 */
fun main(args: Array<String>) {
    println("lambdas with receivers")
    println(alphabet())
    println(alphabetWith())
    println(alphabetWith2())
    println(OuterClass("outer").alphabetWith2())
    println(alphabetApply())
    println(alphabetConcise())

    // 'apply' function is a concise way to initialize some properties of the object after its creation
    println(Person(lastName = "Smith").apply {
        firstName = "John"
        age = 30
        comment = "Who is Mr.Smith?"
    }.toString())
}

// in this case calling method on result is made repeatedly
fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'a' .. 'z') {
        result.append(letter)
    }
    result.append("\nNow i know the alphabet")
    return result.toString()
}

// 'with' is a function for two args, the second is lambda. So 'with' is not the language construct
// 'with' creates an extension function from lambda passed for the first argument. So 'this' stands for first argument object
fun alphabetWith(): String {
    val result = StringBuilder()
    return with(result) { // specifies the receiver value on which methods are called
        for (letter in 'a'..'z') {
            this.append(letter) // calling receiver method via 'this'
        }
        append("\nNow i know the alphabet") // this can be omitted
        this.toString() // return a value from lambda
    }
}

// regular function - regular lambda
// extension function - lambda with receiver

fun alphabetWith2() = with(StringBuilder()) {
    for (letter in 'a'..'z') {
        append(letter)
    }
    append("\nNow i know the alphabet")
    toString()
}

// 'apply' is the same as 'with' but returns receiver object
fun alphabetApply() = StringBuilder().apply {
    for (letter in 'a'..'z') {
        append(letter)
    }
    append("\nNow i know the alphabet")
}.append("!!!").toString() // the result of the 'apply' call is StringBuilder object

// more concise way to build s string - 'buildString' (library function)
fun alphabetConcise() = buildString {
    for (letter in 'a'..'z') {
        append(letter)
    }
    append("\nNow i know the alphabet")
}


class OuterClass(val name: String) {

    override fun toString(): String {
        return "OuterClass(name='$name')"
    }

    fun alphabetWith2() = with(StringBuilder()) {
        for (letter in 'a'..'z') {
            append(letter)
        }
        append("\nNow i know the alphabet")
        println( this@OuterClass.toString() ) // to call method of containing class use 'this@ClassName.method' construct
        toString()
    }

}

data class Person(val lastName: String,
                  var firstName: String = "firstName",
                  var age: Int = 20,
                  var comment: String = "no comment")
