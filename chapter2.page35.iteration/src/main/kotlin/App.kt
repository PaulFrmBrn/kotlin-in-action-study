/**
 * @author PaulFrmBrn
 */

fun main(args: Array<String>) {
    println("Hello, world!")

    // same as Java's
    println("do-while")
    var i = 0
    do {
        print(" ${i++}")
    } while (i < 5)
    println()

    println("do-while")
    println("while")
    var j = 0
    while (j < 5) {
        print(" ${j++}")
    }
    println()

    // range from 1 to 10 inclusive
    val oneToTen = 1..10
    // simple for loop
    for (i in 1..30) {
        print(fizzBuzz(i))
    }
    println()

    // using backward progression with steps
    for (i in 30 downTo 1 step 2) {
        print(fizzBuzz(i))
    }
    println()

}

fun fizzBuzz(i: Int) =
        when {
            i % 15 == 0 -> "FizzBuzz "
            i % 3 == 0 -> "Fizz "
            i % 5 == 0 -> "Buzz "
            else -> "$i "
        }