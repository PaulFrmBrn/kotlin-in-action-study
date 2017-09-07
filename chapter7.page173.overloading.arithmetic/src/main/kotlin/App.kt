/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("overloading arithmetic operators")

    // Java uses special interfaces connected with special language features
    // e.g. java.lang.Iterable can be used for loops
    // Kotlin uses not interfaces but conventions for functions
    // this make it possible to add extension function to existing Java class and thereby adapt it without modifying
    val p1 = Point(10,20)
    val p2 = Point(30,40)
    println(p1 + p2) // using overloaded plus operation for Point
    println(p1.plus(p2)) // same effect
    // binary operators 'plus +', 'minus -', 'mod %', 'div /', 'times *' can be overloaded
    // precedence is the same as for numeric operations
    println(p1 * 1.5)
    println(2.0 * p1)
    println('a' * 3)
    // operator functions can be overloaded
    // there is no special operators fo bitwise operations -infix functions are used instead

}

data class Point(val x: Int, val y: Int) {
    // 'operator' is used to distinguish operator overloading with function with the same name
    operator fun plus(other: Point): Point { // defining plus operator
        println("plus member function")
        return Point(this.x + other.x, this.y + other.y)
    }
}

// using extension functions to overload operators of external library classes is common pattern
operator fun Point.plus(other: Point): Point { // extension functions is shadowed by member function
    println("plus extension function")
    return Point(this.x + other.x, this.y + other.y)
}

operator fun Point.times(scale: Double): Point { // operand don't have to be of the same type
    println("Point times Double operation")
    return Point((this.x * scale).toInt(),(this.y * scale).toInt())
}

operator fun Double.times(point: Point): Point { // there is no commutativity out of the box
    println("Double times Point operation") // opposite operation should be implemented
    return Point((point.x * this).toInt(),(point.y * this).toInt())
}

operator fun Char.times(count: Int): String { // return type may differ from either of operands
    return this.toString().repeat(count)
}
