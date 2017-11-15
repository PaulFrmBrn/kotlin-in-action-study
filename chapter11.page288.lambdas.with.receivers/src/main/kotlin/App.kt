/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {

    println("lambdas with receivers")

    println(straightforwardBuildString {
        it.append("Hello, ") // have to use 'it'
        it.append("World!")
    })

    println(buildString /*(2)*/{
        /*(1)*/this.append("Hello, ") // using 'this' in here, also 'this' can be omitted
        append("World!")
    })

    // declaring lambda with a receiver is the way to pull one of the param types out of the parentheses an put it in front - receiver type
    // e.g. (StringBuilder)->Unit => StringBuilder.()->Unit

    // in the example above (1) stands for receiver, and (2) stands for lambda

    // lambda with a receiver can be stored in a variable
    val appendExclMark: StringBuilder.() -> Unit = { append("!") }
    val stringBuilder = StringBuilder("Hi ")
    stringBuilder.appendExclMark() // can be used as an extension function
    println(stringBuilder)
    println(buildString(appendExclMark)) // or can be passed as an argument

    // lambda body and lambda with a receiver body look exactly the same. to distinguish between then - check theirs declaration

    // apply() and with() are both functions with arguments of lambdas with a receiver type
    val map = mutableMapOf(1 to "one")
    val returnedMap = map.apply { this[2] = "two" } // apply() invokes lambda on a receiver and returns receiver
    println("map = $map")
    println("returnedMap = $returnedMap")
    with(map) { this[3] = "three" } // with() takes receiver as an ordinary param and returns the result of lambda call
    println("map = $map")

}

fun straightforwardBuildString(builderAction: (StringBuilder) -> Unit): String {
    val builder = StringBuilder()
    builderAction(builder) // passes StringBuilder as an argument
    return builder.toString()
}

// lambda with a receiver declaration structure: <receiver>.(<param types>)-><return type>
fun buildString(/*(2)*/builderAction: StringBuilder.() -> Unit): String { // param is lambda with a receiver
    val builder = StringBuilder()
    // invoking lambda with a receiver as if it were an extension function
    /*(1)*/builder.builderAction() // stringBuilder is now the receiver
    return builder.toString()
}