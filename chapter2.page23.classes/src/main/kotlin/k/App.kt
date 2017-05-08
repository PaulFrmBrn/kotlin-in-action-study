package k

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    val person = Person("Alex",false)
    println("person: $person    ")
    println("person.name: ${person.name}")
    println("person.married: ${person.isMarried}")
    person.isMarried = true
    println("person.married: ${person.isMarried}")
}
