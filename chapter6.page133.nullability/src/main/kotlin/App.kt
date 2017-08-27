/**
 * @author paul
 */
fun main(args: Array<String>) {
    strLen("a")
    // strLen(null) // compilation error
    strLenSafe("a")
    strLenSafe(null)

    val x: String? = null
    // val y: String = x // compilation error
}

// all regular types are non-null by default
fun strLen(string: String) = string.length
// variables of types marked with '?' can store null references
fun strLenSafe(string: String?) =
        if (string != null) string.length else 0 // by adding null check, the code compiles
// fun strLenSafe(string: String?)= string.length // compilation error