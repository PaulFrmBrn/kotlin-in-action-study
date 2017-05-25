/**
 * @author PaulFrmBrn
 */

package collection.extension

fun <T> Collection<T>.joinToString(
        separator: String = "-", // default value
        prefix: String = "<",
        postfix: String = ">"
): String {
    val builder = StringBuilder(prefix)

    for ((index, element) in this.withIndex()) {
        if (index > 0)
            builder.append(separator)
        builder.append(element)
    }

    builder.append(postfix)
    return builder.toString()

}
