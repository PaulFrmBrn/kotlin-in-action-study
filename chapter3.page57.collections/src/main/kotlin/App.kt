/**
 * @author paul
 */

fun main(args: Array<String>) {

    // just an example
    val strings: List<String> = listOf("first", "second", "fourteenth")
    println("last from $strings is ${strings.last()}")

    // varargs is almost equals to Java's [...], but Arrays shuold be unpacked before passing as varargs (using *)
    // this allows to pass additional arguments with unpacked array
    val array = arrayOf("a","b","c")
    val anotherList = listOf("another argument 1","another argument 2",*array)
    println("anotherList is $anotherList")

}
