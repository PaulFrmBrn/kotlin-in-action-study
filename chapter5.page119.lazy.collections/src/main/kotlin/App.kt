import java.io.File

/**
 * @author paul
 */
fun main(args: Array<String>) {
    println("lazy collections")

    val people = listOf(Person("Alice", 29), Person("Bob", 31),
            Person("Charles",31),Person("Dan",21))

    // because both filter() and map() returns List
    // one intermediate collection is created after map() call
    // and another final collection after filter() call
    println(people.map { it.name }.filter { it.startsWith("A") })

    // alternative - is using sequences
    // it's Kotlin's analogue of Java's 8 Stream API
    // NB! without parallel option
    // result is the same but only one collection is created - better performance
    println(people.asSequence() // can be used on any collection
            .map { it.name }                // intermediate operation
            .filter { it.startsWith("A") }  // intermediate operation
            .toList()                       // terminal operation -
    )
    // intermediate operation - returns another sequence (lazy)
    // terminal operation - returns result

    // lazy means that in this case no list will be printed
    println(listOf(1,2,3,4).asSequence()
            .map { print("map($it) "); it * it }
            .filter { print("filter($it) "); it% 2 == 0 })

    // all print() will be called and the result list printed - because of the call of terminal operation
    println(listOf(1,2,3,4).asSequence()
            .map { print ("map($it) "); it * it }
            .filter { print("filter($it) "); it% 4 == 0 }
            .toList())
    // sout >> map(1) filter(1) map(2) filter(4) map(3) filter(9) map(4) filter(16) [4, 16]
    // first map(), then filter() is called for each element sequentially as opposed to collection processing

    // find() is Java's findFirst()
    // lazy (sequence operations)
    // since element 2 square meets the filtering condition, element 3 and 4 will not be processed at all
    println(listOf(1,2,3,4).asSequence().map { print ("map($it) "); it * it }.find { print("find($it) "); it > 3 })
    // sout >> map(1) find(1) map(2) find(4) 4

    // eager (collection operations)
    // all four elements will be processed
    println(listOf(1,2,3,4).map { print ("map($it) "); it * it }.find { print("find($it) "); it > 3 })
    // sout >> map(1) map(2) map(3) map(4) find(1) find(4) 4

    // the order of operations can affect the performance
    println(people.asSequence()
            .map { it.name }
            .filter { it.length < 4 }
            .toList()
    )
    // same result but map() will be performed only for two Persons
    println(people.asSequence()
            .filter { it.name.length < 4 }
            .map { it.name }
            .toList()
    )

    // sequence can be generated
    val naturalNumbers = generateSequence(0) { it + 1 }
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo100.sum())// terminal operation

    fun File.isInsideHeddenDirectory() =
            generateSequence(this) { it.parentFile }.any { it.isHidden }

    // using sequence allows stop traversing the parents as soon as required directory is found
    println(File("/home/paul/study/kotlin/kotlin-in-action-study/chapter5.page119.lazy.collections/src/kotlin.App.kt")
            .isInsideHeddenDirectory())


}

data class Person(val name: String, val gae: Int)