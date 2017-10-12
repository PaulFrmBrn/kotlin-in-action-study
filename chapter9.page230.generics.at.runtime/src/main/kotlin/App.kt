/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("generics at runtime")

    // just as Java's, Kotlin's generics are erased at runtime
    val listOfStrings: List<String> = listOf("a", "b")
    val listOfInts: List<Int> = listOf(1,2,3)
    // at compile time type parameters are known
    // but in runtime listOfStrings can be "seen" only as List<*>, so
    // val isStringList = listOfStrings is List<Int> // compilation error - there no chance to check parameter type in runtime

    val isStringList = listOfStrings is List<*> // no error Type information (not parameter type) is not erased at runtime

    // NB raw types are forbidden in Kotlin


    printSum(listOf(1,2,3)) // no errors - List<Int> is passed
    // printSum(listOf("a","b")) // runtime error at [1]: ClassCastException: String cannot be cast to Number
    // not at [2], because only type is checked, not type parameter at [3] an 'as?' returns true

    printSum2(listOf(1,2,3))
    //printSum2(listOf("a","b")) // compiletime error

}


fun printSum(c: Collection<*>) {
    val intList = c as? List<Int> // [3] no compilation error, but warning - unchecked cast
            ?: throw IllegalArgumentException("List is expected") // [2]
    println(intList.sum()) // [1]
}

fun printSum2(c: Collection<Int>) {
    if (c is List<Int>) { // no errors or warnings: type parameter can be checked as compiletime, ant type at runtime
        println(c.sum())
    }
}