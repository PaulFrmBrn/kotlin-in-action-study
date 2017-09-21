

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("Calling Kotlin lambdas from Java")

    // Kotlin lambda or variable of function type is implementation of interface FunctionN,
    // e.g. Function0<R>, Function1<P1, R>, etc.
    // each defining single invoke() method
    SomeJavaClass().callingKotlinLambda() // calling Kotlin lambda from Java
    // calling Kotlin lambda from Java with implementing anonymous class
    SomeJavaClass().callingKotlinLambdaWithAnonymousClass()
    SomeJavaClass().usginKotlinExtensionFunction() // calling Kotlin extension function with lambda from Java


    println("default and null values")

    val letters = listOf("Alpha", "Betta")

    // using default value of 'transform'
    println(letters.joinToString())
    // using explicit value for the 'transform' passing it outside of the parentheses
    println(letters.joinToString { it.toLowerCase() })
    // using explicit value for the 'transform' passing with named param
    println(letters.joinToString(separator = "! ", transform = {it.toUpperCase()}))

    // using default value of 'transform'
    println(letters.joinToStringNUllableTransform())
    // using explicit value for the 'transform' passing it outside of the parentheses
    println(letters.joinToStringNUllableTransform { it.toLowerCase() })
    // using explicit value for the 'transform' passing with named param
    println(letters.joinToStringNUllableTransform(separator = "! ", transform = {it.toUpperCase()}))

}

fun processTheAnswer(f: (Int) -> Int) {
    println(f(42))
}

fun <T> Collection<T>.joinToStringNUllableTransform(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = "",
        transform: ((T) -> String)? = null  // default value for the nullable variable of function type
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (index > 0) {
            result.append(separator)
        }
        // calling the transform function if it is passed or using toString() as a default
        val str = transform?.invoke(element) ?: element.toString()
        result.append(str)
    }
    result.append(postfix)
    return result.toString()
}

fun <T> Collection<T>.joinToString(
        separator: String = ", ",
        prefix: String = "",
        postfix: String = "",
        transform: (T) -> String = { it.toString() } // default value for the variable of function type
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (index > 0) {
            result.append(separator)
        }
        result.append(transform(element)) // calling the transform function
    }
    result.append(postfix)
    return result.toString()
}