/**
 * Created by dupavlov on 04.05.2017.
 */


data class Person (val name: String,
                   val age: Int? = null)

fun main(args: Array<String>) {
    val persons = listOf(Person("Alice"), Person("Bob", 29))
    val oldest = persons.maxBy { it.age ?: 0 }
    print("The oldest person is: $oldest")
}