/**
 * @author PaulFrmBrn
 */

fun main(args: Array<String>) {
    println("Hello, world!")
    println(Color.BLUE)
    println(Color2.BLUE.rgb())
    println(getMnemonic(Color2.BLUE))
    println(getWarmth(Color2.BLUE))
    println(mix(Color2.VIOLET,Color2.BLUE))
    println(mixOptimized(Color2.VIOLET,Color2.BLUE))
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

fun mix(firstColor: Color2, secondColor: Color2) =
        when (setOf(firstColor,secondColor)) { // when expression - any object equality check
            setOf(Color2.RED,Color2.YELLOW) -> Color2.ORANGE
            setOf(Color2.BLUE,Color2.YELLOW) -> Color2.GREEN
            setOf(Color2.BLUE,Color2.VIOLET) -> Color2.INDIGO
            else -> throw Exception("Dirty color") // default branch
        }

fun mixOptimized(firstColor: Color2, secondColor: Color2) =

        when { // no when argument

            // less concise but more efficient implementation  - no set creation on each and every method call

            (firstColor == Color2.RED && secondColor ==  Color2.YELLOW) || // any boolean expression can be used
            (firstColor == Color2.YELLOW && secondColor ==  Color2.RED) -> // if when expression is not supplied
                Color2.ORANGE

            (firstColor == Color2.BLUE && secondColor ==  Color2.YELLOW) ||
            (firstColor == Color2.YELLOW && secondColor ==  Color2.BLUE) ->
                Color2.GREEN

            (firstColor == Color2.BLUE && secondColor ==  Color2.VIOLET) ||
            (firstColor == Color2.VIOLET && secondColor ==  Color2.BLUE) ->
                Color2.INDIGO

            else -> throw Exception("Dirty color") // default branch

        }

