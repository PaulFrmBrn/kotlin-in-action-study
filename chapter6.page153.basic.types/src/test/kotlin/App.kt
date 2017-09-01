/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("basic types")

    // Kotlin doesn't distinguish primitives and wrappers
    val i: Int = 1
    val list: List<Int> = listOf(1,2,3)

    val percent = 146.coerceIn(0, 100) // methods can be called on values of number types
    println("We're ${percent} deone!")

    // Kotlin represent each number type value in the most efficient way possible

    // non-null types
    // variables, properties, params, return types - Java primitive types, generic classes - Java reference type
    val listOfInts: List<Int> = listOf(1,2,3) // List<java.lang.Integer>

    // for nullable types
    // represented by Java Reference Types
    println(Person("Sam",35).isOlderThan(Person("Amy",42)))
    println(Person("Sam",35).isOlderThan(Person("Jane")))

    // number conversion in Kotlin is not automatic
    val j: Int = 1
    // val k: Long = i // compilation error
    val k: Long = i.toLong() //  there is a conversion function for each primitive type

    // this is so to avoid surprises like comparing variable of Boxed values
    // first types are compared, than - values
    println( java.lang.Integer(42).equals(java.lang.Long(42)) ) // false

    val x = 1
    val list2 = listOf(1L,2L,3L)
    // x in list2 // compilation error, but if Kotlin had supported implicit conversion the result would have been false
    println(x.toLong() in list2) // true - no type comparision in here

    // primitive type literals in Kotlin are the same as Java
    // usually special syntax fir number constants is unnecessary
    fun foo(l: Long) = println(l)
    val b: Byte = 1 // no error. b is interpreted like 0b1
    foo(42) // no error. 42 is interpreted like 42L
    val l = b + 1L // no error. arithmetic operations are overloaded ot accept all appropriate numeric types

    // Kotlin as Java doesn't make any overhead for overflow checks for arithmetic operations

    // There are functions for conversions string into numeric types
    println("42".toInt())
    // println("a42".toInt()) // runtime error: NumberFormatException

}

data class Person(val name: String,
                  val age: Int? = null /*java.lang.Integer*/ ) {
    fun isOlderThan(other: Person): Boolean? {
        if (this.age == null || other.age == null)
            return null
        return this.age > other.age
    }
}