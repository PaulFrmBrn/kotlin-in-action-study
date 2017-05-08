/**
 * @author PaulFrmBrn
 */
class Rectangle (val height: Int, val width: Int) {
    val isSquare: Boolean // no field isSquare, only getter
        get() {
            return height == width
        }
    val isNotSquare: Boolean
        get() = height != width // more concise syntax
}

fun main(args: Array<String>) {
    val rectangle = Rectangle(4,5)
    println("rectangle is square: ${rectangle.isSquare}")
    println("rectangle is not square: ${rectangle.isNotSquare}")
}
