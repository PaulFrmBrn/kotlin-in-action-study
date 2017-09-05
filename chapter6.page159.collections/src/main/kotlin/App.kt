import java.io.BufferedReader
import java.io.StringReader

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("collections")

    // Kotlin supports nullability for type arguments
    // list of nullable Int values, but list itself is not-null
    val numbers: List<Int?> = readNumbers(BufferedReader(StringReader("1\n2\nasd\n3\n")))
    println("numbers = $numbers")
    addValidNumbers(numbers)
    addValidNumbers2(numbers)
    // nullable list of nullable elements
    val nullableList: List<Int?>? = null

    // Kotlin supports both read-only and mutable collections
    // kotlin.collections.Collections - read-only
    // kotlin.collections.MutableCollections (extends Collections) - mutable
    val readOnlyList: Collection<Int> = List<Int>(10, {it})
    readOnlyList.size // only read-only operations are present
    readOnlyList.iterator()
    readOnlyList.isEmpty()
    println("readOnlyList = $readOnlyList")
    val mutableList: MutableCollection<Int> = MutableList<Int>(10, {it + 10})
    mutableList.add(1) // operation for changing collection's state are also presented
    mutableList.remove(11)
    println("mutableList = $mutableList")
    mutableList.clear()

    // makes it easier to understand what is happening in the program, like 'var' vs. 'val'
    // and prevents from using unsupported operations
    val readOnlySource = listOf(1,2,3)
    val mutableTarget: MutableCollection<Int> = MutableList<Int>(10,{it})
    copyElement(readOnlySource,mutableTarget)
    println("mutableTarget = $mutableTarget")

    val source: Collection<Int> = arrayListOf(1,2,3)
    val target: Collection<Int> = arrayListOf(1)
    // copyElement(source,target) // compilation exception

    // read-only collections are not necessary immutable and not thread-safe
    val mutableOrigin = mutableListOf(1,2,3)
    val readOnlyReference: List<Int> = mutableOrigin
    println("mutableOrigin = $mutableOrigin")
    println("readOnlyReference = $readOnlyReference")
    mutableOrigin.add(5)
    println("mutableOrigin = $mutableOrigin")
    println("readOnlyReference = $readOnlyReference") // will print [1, 2, 3, 5]


}

fun <T> copyElement(source: Collection<T>, // this collection won't be modified
                    target: MutableCollection<T>) { // but this - will
    for (element in source) {
        target.add(element)
    }
}

fun addValidNumbers(numbers: List<Int?>) {
    var sum = 0
    var invalidNumbers = 0
    for (number in numbers) {
        if (number != null) {
            sum += number
        } else {
            invalidNumbers++
        }
    }
    println("sum = $sum")
    println("invalid numbers count = $invalidNumbers")
}

fun addValidNumbers2(numbers: List<Int?>) {
    val validNumbers: List<Int> = numbers.filterNotNull() // list is no more nullable after filtering
    println("sum = ${validNumbers.sum()}")
    println("invalid numbers count = ${numbers.count() - validNumbers.count()}")
}

fun readNumbers(reader: BufferedReader): List<Int?> {
    val result = ArrayList<Int?>() // list of nullable Int values
    for (line in reader.lineSequence()) {
        try {
            val number = line.toInt()
            result.add(number) // adding non-null value
        } catch (e: NumberFormatException) {
            result.add(null) // adding null value
        }
    }
    return result
}