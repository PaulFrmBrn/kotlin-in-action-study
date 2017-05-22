@file:JvmName("StringFunctions") // specifies custom class name
package strings

/**
 * @author PaulFrmBrn
 */

// It will be generated JinnKt.class with corresponding class with static method joinToString

var opCount = 1 // will have setter and getter
val UNIX_LINE_SEPARATOR = "\n" // only getter
const val PUBLIC_STATIC_FINAL_LINE_SEPARATOR = "-" // Kotlin's analogue for public static final with no getter


fun <T> joinToString(
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