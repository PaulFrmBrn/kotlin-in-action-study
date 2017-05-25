/**
 * @author PaulFrmBrn
 */

package string.extension

// extension method
// works with any *.class (even if there is no source)

// doesn't break encapsulation - no access to the private or protected members

// String - receiver type
// this - receiver objects
fun String.lastChar(): Char = this.get(this.length - 1)

fun Collection<String>.join(
        separator: String = "-", // default value
        prefix: String = "<",
        postfix: String = ">"
) = joinToString(separator,prefix,postfix)
