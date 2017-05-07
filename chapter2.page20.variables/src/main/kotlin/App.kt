/**
 * @author PaulFrmBrn
 */


fun main(args: Array<String>) {
    println("hello, world")
    // val - immutable reference (final variable in Java)
    // omit type declaration
    val question = "The Ultimate Question of Life, the Universe, and Everything"
    val answer = 42
    val answer2: Int = 42
    // double
    val yearsToCompute = 7.5e6
    // no type inference without initializer
    val answer3: Int
    answer3 = 42
    // var - mutable reference (non-final variable in Java)

    val languages = arrayListOf("Java") // immutable reference
    languages.add("Kotlin") // mutable object

}