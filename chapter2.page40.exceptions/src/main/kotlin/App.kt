import java.io.BufferedReader
import java.io.StringReader

/**
 * @author PaulFrmBrn
 */

fun main(args: Array<String>) {
    println("Hello, world!")
    readNumber(BufferedReader(StringReader("asd")))
    readNumber2(BufferedReader(StringReader("asd")))
}

// Kotlin doesn't differentiate between checked and unchecked exceptions
// no need to declare trow clause
fun readNumber(reader: BufferedReader): Int? {
    try {
        val line = reader.readLine() // throws *UNchecked* IOException
        return Integer.parseInt(line)
    } catch (e: NumberFormatException) { // but it is necessary to process it
        return null
    } finally {
        reader.close() // throws *checked* IOException
        // but it is almost always impossible to do anything with it
    }
}

// more concise syntax
fun readNumber2(reader: BufferedReader) {
    val number = try { // try - is an expression
        // the result of the last expression in the block is the result of the *try*
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        // the result of the last expression in the block is the result of the *try*
        //return // in this case *println(number)* will not be called
        null // in this case number will get the value *null* and *println(number)* will be called
    }
    println(number)
}