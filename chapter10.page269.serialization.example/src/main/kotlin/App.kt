/**
 * @author paul
 */
fun main(args: Array<String>) {

    println("simplified serialization implementation")

    val person = Person("Alice", 27)
    val serialized = serialize(person)
    println("serialized person = $serialized")

}

data class Person(@JsonName("alias") val firstName: String, @JsonExclude val age: Int)