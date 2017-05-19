/**
 * @author paul
 */

fun main(args: Array<String>) {
    println("Hello, world!")

    // simple exception example
    val percentage = 0
    if (percentage !in 0..100)
        throw IllegalArgumentException("A percentage value must be between 0 and 100: $percentage")

    checkPercentage(7)

}

fun checkPercentage(number: Int): Int {
    val percentage = if (number in 0..100)
        number
    else // throw is an expression
        throw IllegalArgumentException("A percentage value must be between 0 and 100: $number")
    return percentage
}