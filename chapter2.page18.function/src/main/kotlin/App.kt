/**
 * @author PaulFrmBrn
 */

fun main(args1: Array<String>) {
    println("Hello, world!")
    val max = max(3, 5)
    println("max = $max")
    val min = min(3, 5)
    println("min = $min")
}

// if -  is an expression, not a statement
// assignment  - is a statement, not an expression
// block body
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

// expression body
// type inference for function return type of expression body function
fun min (a: Int, b: Int) = if (a<b) a else b

