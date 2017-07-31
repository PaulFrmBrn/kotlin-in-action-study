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

    // "to" is not a keyword - it is infix function
    val map1 = mapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
    println("map1 = $map1")
    // the same construct
    val map2 = mapOf(1.to("one"), 7.to("seven"), 53.to("fifty-three"))
    println("map2 = $map2")

    // using infix function both with destructing declaration - splitting Pair objects into two objects
    val (num, string) = 1 pair "one"
    println("num = $num and string = $string")

    // also can be used with Maps
    println("map:")
    for ((key,value) in map1) {
        println("key = $key, value = $value")
    }
    // and collections
    println("collection:")
    for ((index,element) in strings.withIndex()) {
        println("index = $index, element = $element")
    }

}

// infix modifier specifies infix function
// infix can be used for any method that have one required argument
infix fun Any.pair(other: Any) = Pair(this,other)
