package geomtery

//import geomtery.shapes.createRandomRectangle // classes and functions can be imported from package separately
import geomtery.shapes.* // or all together

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("random rectangle is square? answer is: ${createRandomRectangle().isSquare}")
}
