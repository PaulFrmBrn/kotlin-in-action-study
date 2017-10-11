/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("generic types")

    // generic type can be inferred
    val people = listOf("Alice","Bob","Tom")

    // or declared explicitly, if there is no info  fo type inference
    val otherPeople = mutableListOf<String>()
    val someOtherPeople: List<String> = mutableListOf() // same result

    // Kotlin does not support row types

    // generic function - has type parameters of its own
    // on each call of generic function there should be provided specific type
    val slice = people.getChunk<String>(0..1)
    println(slice)
    // unless it can be inferred
    val slice2 = people.getChunk(0..1)
    println(slice2)
    println(people.penultimate)

}

// <T> - type parameter of generic function
// it defines type parameter in returning value, anf type parameter of receiver object of extension function
fun <T> List<T>.getChunk(indices: IntRange): List<T> {
    if (indices.isEmpty()) return listOf()
    return this.subList(indices.start, indices.endInclusive + 1).toList()
}

// extension properties can also be generified
val <T> List<T>.penultimate: T // can be called on list of any type
    get() = this[size-2]
// regular properties - not