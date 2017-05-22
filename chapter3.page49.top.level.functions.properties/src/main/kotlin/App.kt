import strings.PUBLIC_STATIC_FINAL_LINE_SEPARATOR
import strings.UNIX_LINE_SEPARATOR
import strings.joinToString
import strings.opCount

/**
 * @author PaulFrmBrn
 */

fun main(args: Array<String>) {
    println("Hello")
    println(joinToString(listOf(1,2,3)))
    // to call this method from Java - JoinKt.joinToString(list,"-","<",">") with import strings.JoinKt

    val count = opCount
    println("count = $count")
    opCount = 2 // setter
    val count2 = opCount // getter
    println("count2 = $count2")

    println("UNIX_LINE_SEPARATOR = $UNIX_LINE_SEPARATOR") // getter
    //UNIX_LINE_SEPARATOR = "" // compile time error - no setter

    println("PUBLIC_STATIC_FINAL_LINE_SEPARATOR = $PUBLIC_STATIC_FINAL_LINE_SEPARATOR")
}
