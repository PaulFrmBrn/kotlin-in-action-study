/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("overloading comparision operators")


    // '==' is automatically translated into equals() because 'equals' is marked with 'operator' and implemented
    // on 'Any' as member function
    // this also means that '==' can not be overloaded via extension function - member function takes precedence
    // '!=' is automatically translated into !equals()

    println(Point(1,1) == Point(1,1)) // true
    println(Point(1,1) != Point(1,2)) // true
    println(null == Point(1,1)) // false with no runtime error - can be used with nullable operands
    println(Point(1,1) == null) // can be used with nullable operands
    println(null == null) // true

    // 'a > b' is translated into 'a.compareTo(b) > 0' in Kotlin. All the same with '<', '<=', '>='
    println(Person("Alice","Smith") > Person("Bob","Johnson"))
    // all Java classes implemented Comparable can be compared in Kotlin via operators


}

class Person(val firstName: String, val lastName: String) : Comparable<Person> {
    // 'operator' is applied to the function in the base interface
    override fun compareTo(that: Person): Int {
        // standard function (can be slower than implemented by hands)
        return compareValuesBy(
                this,that, // objects to be compared
                Person::lastName,Person::firstName) // list of lambdas in order of comparision
    }

}

class Point(val x: Int, val y: Int) {
    override fun equals(that: Any?): Boolean {
        // null checks are performed by compiler (?)
        if (that === this) return true // identity equals, like '==' in Java. Can't be overloaded
        if (that !is Point) return false
        return this.x == that.x && this.y == that.y
    }

}