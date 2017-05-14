import java.util.*

/**
 * @author PaulFrmBrn
 */

fun main(args: Array<String>) {
    println("Hello, World!")

    val binaryReps = TreeMap<Char,String>()

    for (c in 'A'..'F') { // ranges can e used not only for numbers
        val binary = Integer.toBinaryString(c.toInt())
        binaryReps[c] = binary // // shorthand syntax for map key-value interaction
    }

    // for loop unpacks element of the collection into variables
    for ((letter,binary) in binaryReps) { // letter & binary are variable names for key and value of the map entry
        println("$letter = $binary")
    }

    val list = arrayListOf("10","11","101")
    for ((index,element) in list.withIndex()) { // index can be obtained ou of the box
        println("$index = $element")
    }

    println(recognize('8'))

    // ranges aren't restricted with digits and chars. Comparable interface implementations are used
    println("Kotlin" in "Java" .. "Scala") // comparing string alphabetically
    println("Kotlin" in setOf("Java","Scala")) // comparing belonging

}

fun recognize (c: Char) =
        when (c) {
            in '0' .. '9' -> "It's a digit"
            in 'a' .. 'z', in 'A' .. 'Z' -> "it's a letter'"
            else -> "Who knows"
        }
