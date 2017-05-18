package geomtery.shapes // directory structure can differ form package structure

import java.util.Random

/**
 * @author PaulFrmBrn
 */

// file name does not have to match class name
// multiple classes can be declared in the same file
class Rectangle(val height: Int, val width: Int){
    val isSquare: Boolean
        get() = height == width
}

// functions can be declared separately
fun createRandomRectangle(): Rectangle {
    val random = Random()
    return Rectangle(random.nextInt(),random.nextInt())
}


