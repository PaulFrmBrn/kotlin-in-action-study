/**
 * @author paul
 */

fun main(args: Array<String>) {
    println("Hello")

    val list = listOf(1, 2, 3)
    println(list) // Java's standard tpString is not customizable out of th box. Guava / Apache commons solve this problem

    println(joinTostring(list,"-","<",">")) // custom but verbose toString

    // problem: hard to understand for the which param stands each value - 'Boolean flags Problem'
    // solution (Java):
    println(joinTostring(/*collection*/list,/*separator*/"-",/*prefix*/"<",/*postfix*/">"))
    // solution (Kotlin): named params
    println(joinTostring(collection = list, separator = "-", prefix = "<", postfix = ">"))
    // NB can not be used to call Java Methods

    // problem: huge amount of overloaded methods in Java - overloading. Example {@link Thread} - 8 constructors
    // Overloading - for convenience of API, compatibility, etc. But the result - duplication
    // solution: default values
    println(joinTostring(list))
    // without named arguments only trailing args can be omitted
    println(joinTostring(list,"-"))
    // with named args - any
    println(joinTostring(list,prefix = "["))
    // NB default values are encoded at the called side, not  calling.
    // NB So recompilation with new defaults will implicitly change the behavior of the calling side
    // NB2 Calling Kotlin from Java all param values have to be specified explicitly. See @JvmOverloads instead

}

// toString() customizing function
fun <T> joinTostring(
        collection: Collection<T>,
        separator: String = "-", // default value
        prefix: String = "<",
        postfix: String = ">"
): String {
    val builder = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0)
            builder.append(separator)
        builder.append(element)
    }

    builder.append(postfix)
    return builder.toString()

}
