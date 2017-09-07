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

    // defining operator normally leads to defining compound assignment operator
    var p3 = Point(50,60) // this is var
    p3 += Point(5,5) // this equals to 'p3 = p3 + Point(5,5)' - new object created
    println(p3)

    // if the aim is to modify object - compound assignment operator should be defined explicitly
    // this function will return 'Unit' (void)
    // MutableCollections#plusAssign(T)
    val array = arrayListOf(1,2,3)
    array += 42 // for collections compound assignment operators modify collection
    val newArray = array + listOf(4,5) // while regular operators create new one
    println(array)
    println(newArray)

    // if both plus and plusAssign are applicable compiler will report an error
    val mutablePoint1 = MutablePoint(0,0)
    // var mutablePoint1 = MutablePoint(0,0) // compilation error
    mutablePoint1 += MutablePoint(1,1)
    println(mutablePoint1)

    // to avoid this either operator should be called as function
    // or 'val' used to make calling '+=' impossible because of immutability

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

data class MutablePoint(var x: Int, var y: Int)

operator fun MutablePoint.plus(other: MutablePoint): MutablePoint {
    println("plus extension function for mutable point")
    return MutablePoint(this.x + other.x, this.y + other.y)
}

operator fun MutablePoint.plusAssign(other: MutablePoint) {
    println("plus assign extension function for mutable point")
    this.x = this.x +other.x
    this.y = this.y + other.y
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
