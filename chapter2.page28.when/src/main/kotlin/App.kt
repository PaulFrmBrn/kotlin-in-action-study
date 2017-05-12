/**
 * @author PaulFrmBrn
 */

fun main(args: Array<String>) {
    println("Hello, world!")
    println(Color.BLUE)
    println(Color2.BLUE.rgb())
    println(getMnemonic(Color2.BLUE))
    println(getWarmth(Color2.BLUE))
}

// enums in Kotlin
// enum - soft keyword
enum class Color {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}

enum class Color2(
        val r: Int, val g: Int, val b: Int // properties of enum constants
) {
    RED(255,0,0),
    ORANGE(255,165,0),
    YELLOW(255,255,0),
    GREEN(0,255,0),
    BLUE(0,0,255),
    INDIGO(75,0,130),
    VIOLET(238,130,238); // the only place in Kotlin where ; required. Separating enum constants from methods

    fun rgb() = ((r*256 + g)*256) + b // method
}

// when example
// when - is an expression
// no break; needed
fun getMnemonic(color: Color2) =
        when (color) {
            Color2.RED -> "Richard"
            Color2.ORANGE -> "Of"
            Color2.YELLOW -> "York"
            Color2.GREEN -> "Gave"
            Color2.BLUE -> "Battle"
            Color2.INDIGO -> "In"
            Color2.VIOLET -> "Vain"

        }

fun getWarmth(color: Color2) =
    when (color) {
        Color2.RED, Color2.ORANGE, Color2.YELLOW -> "warm" // multiple values in the same line
        Color2.GREEN -> "neutral"
        Color2.BLUE, Color2.INDIGO, Color2.VIOLET -> "cold"
    }


