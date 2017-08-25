/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {

    println("functional APIs for collections")

    val ints = listOf(1, 2, 3, 4)
    val people = listOf(Person("Alice", 29), Person("Bob", 31), Person("Carol", 31))

    // filtering
    // doesn't change the collection -  new collection as a result
    println(ints.filter{ it % 2 == 0 })
    println(people.filter { it.age > 30 })

    // mapping (transformation)
    println(people.map { it.name })
    println(people.map(Person::name)) // member reference

    // can be chained
    println(people.filter { it.age > 30 }.map { it.name} ) // NB each call leads to new collection creation (not a stream)

    // maximum
    val maxAge = people.maxBy(Person::age)?.age
    people.filter { it.age == maxAge }

    // special cases for maps
    val pairs = mapOf(0 to "zero", 1 to "one")
    println(pairs.mapValues { it.value.toUpperCase() }.filterValues { it.length > 3 })
    println(pairs.mapKeys { it.key * 2 }.filterKeys { it > 0 })

    val canBeInClub29 = { p:Person -> p.age <= 29}

    // all elements satisfy predicate
    println(people.all(canBeInClub29))

    // at least one element satisfy predicate
    println(people.any(canBeInClub29))

    val listOfTree = listOf(1,2,3)
    println(!listOfTree.all { it == 3 })
    println(listOfTree.any { it != 3 }) // this is preferable - negotiation is noticeable

    // count of elements that satisfy predicate
    println(people.count(canBeInClub29)) // no intermediate collection created
    println(people.filter(canBeInClub29).size) // intermediate collection created

    // grouping
    val groupedPeople:  Map<Int,List<Person>> = people.groupBy { it.age } // map as a result
    println(groupedPeople)
    println(listOf("a","ab","b").groupBy { it.first() })

}

data class Person(val name:String,val age: Int)