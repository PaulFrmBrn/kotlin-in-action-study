

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("dsl with infix functions and objects")

    val string = "Kotlin"

    // dsl for testing - the way to write code as sentence in English
    string should startWith("Kot")

    // more concise way
    string should start with "Kot"
    // or without infix calls
    string.should(start).with("Kot1")

}

// defining infix function to reduce syntax noise
// should() expects mather as an argument - both generic
infix fun <T> T.should(matcher: Matcher<T>) = matcher.test(this)
interface Matcher<T> {
    fun test(value: T)
}
// implementation of specific matcher for String
// class name doesn't start with capitalized letter breaking standard Code Convention, but it's often more suitable while building DSL
class startWith(val prefix: String) : Matcher<String> {
    override fun test(value: String) {
        if (!value.startsWith(prefix)) {
            throw AssertionError("String '$value' should start with '$prefix'")
        }
    }
}

// object to be used with a wrapper. also breaks the convention
object start
// should() takes the start object as an argument to make a choice of right Wrapper (further action)
// using object as a parameter id usually meaningless, but here it plays as stub to make the whole DSL grammar clean
infix fun String.should(x: start): StartWithCheckWrapper = StartWithCheckWrapper(this)
// wrapper to hold with() infix function
class StartWithCheckWrapper(val value: String) {
    // performing specific check action
    infix fun with(prefix: String) {
        if (!value.startsWith(prefix)) {
            throw AssertionError("String '$value' should start with '$prefix'")
        }
    }

}
