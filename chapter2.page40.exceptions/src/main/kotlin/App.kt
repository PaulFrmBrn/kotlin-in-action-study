import java.io.BufferedReader
import java.io.StringReader

/**
 * @author PaulFrmBrn
 */

fun main(args: Array<String>) {
    println("Hello, world!")
    readNumber(BufferedReader(StringReader("asd")))
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
