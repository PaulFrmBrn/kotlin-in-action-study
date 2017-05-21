/**
 * @author paul
 */

fun main(args: Array<String>) {

    // Kotlin uses standard Java Collections

    println("Hello, World!")

    val set = hashSetOf(1, 2, 3)
    println(set.javaClass)// Kotlin's javaClass - Java's getClass()

    val list = arrayListOf(1, 3, 2)
    println(list.javaClass)
    val last = list.last() // there are additional features in Kotlin for Java Collection in standard library
    println(last)
    val max = list.max()
    println(max)

    val map = hashMapOf(1 to "one", 2 to "two", 3 to "three") // *to* is not a keyword but function
    println(map.javaClass)

}
