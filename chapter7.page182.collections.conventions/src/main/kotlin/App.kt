import java.time.LocalDate

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("collections conventions")

    // 'get'
    val point = Point(1, 2)
    println("point[0] = ${point[0]}")
    println("point[1] = ${point[1]}")
    // println("point[2] = ${point[2]}") // runtime error IndexOutOfBoundsException

    // 'set'
    val mutablePoint = MutablePoint(1, 2)
    println("mutablePoint = $mutablePoint")
    mutablePoint[0] = 3
    println("mutablePoint = $mutablePoint")

    // 'in'
    val rectangle = Rectangle(Point(10, 10), Point(50, 50))
    println(Point(20,30) in rectangle) // true

    // '..'
    // 'rangeTo' operator can be implemented manually
    // but it is implemented for 'Comparable', so any class implementing 'Comparable' has 'rangeTo' implemented ou of the box
    val now = LocalDate.now() // 'LocalDate' implements 'Comparable'
    val vacation = now..now.plusDays(10) // 'start..end' -> 'start.rangeTo(end)' (closed range)
    println(now.plusWeeks(1) in vacation) // true

    // 'rangeTo' has lover priority than arithmetic operations, but parentheses make it clear
    println(0..(9+1)) // 0..10

    // to call method on a range it should be in parentheses
    (0..10).forEach { print("$it ")}

    // in
    // 'iterator()' should be implemented to allow using class's instances in 'for (a in b)' syntax
    // 'iterator()' returns iterator
    val newYear = LocalDate.ofYearDay(2017, 1)
    val daysOffs = newYear.minusDays(1)..newYear.plusDays(10) // creating an ClosedRange<LocalDate> instance
    for (day in daysOffs) { // calling 'iterator()' for ClosedRange<LocalDate>
        println(day)
    }

}

// implementing iterator for using with range of LocalDates (ClosedRange<LocalDate) not for a single LocalDate
operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
        object : Iterator<LocalDate> {
            var current = start // 'start' and 'endInclusive' comes from 'ClosedRange'
            override fun hasNext() = current < endInclusive
            override fun next() = current.apply {
                current = plusDays(1)
            }
        }

data class Point(val x: Int, val y: Int)

data class Rectangle(val upperLeft: Point, val lowerRight: Point)

// 'a in b' -> 'b.contains(a)'
operator fun Rectangle.contains(point: Point): Boolean {
                   // from this to that
    return point.x in upperLeft.x until lowerRight.x && // 'until' builds a range and 'in' checks whenever value is in range
            point.y in upperLeft.y until lowerRight.y
}

// 'get' stands for reading value via index and square brackets
// index can be of any type.
// Also there can be multiple params, e.g. 'operator fun get(i: Int, j: Int)' -> 'matrix[i,j]'
// 'get' can be overloaded
operator fun Point.get(index: Int ): Int {
    return when(index) {
        0 -> this.x
        1 -> this.y
        else -> throw IndexOutOfBoundsException("Invalid index $index")
    }
}

data class MutablePoint(var x: Int, var y: Int)

// 'set' stands for writing value via index and square brackets
// first params stands for indices, the last - for value to be assigned
operator fun MutablePoint.set(index: Int, value: Int) {
    when(index) {
        0 -> this.x = value
        1 -> this.y = value
        else -> throw IndexOutOfBoundsException("Invalid index $index")
    }
}