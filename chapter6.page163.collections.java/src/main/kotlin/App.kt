import java.io.File

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("kotlin collections and java")

    // every Kotlin collection implements Java collection interface - there is no wrappers or conversions
    // every java collections has two representation in Kotlin - read-only and immutable
    // basic structure of Kotlin collection hierarchy  is parallel to java.util.*
    // each Kotlin mutable interface extends read-only interface

    // e.g. Kotlin sees java.util.ArrayList as if it inherited from Kotlin's MutableList
    val readOnlyList: List<Int> = listOf(1, 2, 3)
    val mutableList: MutableList<Int> = mutableListOf(1, 2, 3)

    // if calling a Java method taking an java.util.Collection as a param, both Kotlin's Collection or MutableCollection can be passed
    // so Java can modify collection even if it declared read-only
    val readOnlyStringList = listOf("a","b","c")
    println("readOnlyStringList = $readOnlyStringList")
    CollectionUtils.uppercaseAll(readOnlyStringList)
    println("readOnlyStringList = $readOnlyStringList")
    // therefore special precautions needed if passing Kotlin's collections to Java - java code can modify read-only
    // collections or insert null into collection with non-null elements
    val nonnullStringList: MutableList<String> = mutableListOf("a","b","c")
    CollectionUtils.insertNull(nonnullStringList)
    println("nonnullStringList = $nonnullStringList")

    // Java collection in Kotlin is platform type - there is no info about its nullability or mutability
    // this is especially important when overriding Java methods with Collections as params in Kotlin
    FileIndexer()
    PersonParser()
    // e.g. in the examples above Java's List<String> is represented by List<String>? and MutableList<String?>
    // the correct choice depends on exact contract and desired implementation

    // Array in Kotlin is a generic class
    val array = Array<Int>(5,{it}) // creating new array. way 1
    for (i in array.indices) { // getting index
        println("$i = ${array[i]}") // accessing via index
    }
    val array2: Array<String?> = arrayOfNulls(5) // creating new array. way 2
    println(array2.joinToString("-"))
    val array3 = arrayOf(1,2,3,4,5) // creating new array. way 3
    println(array3.joinToString("-"))
    val typedArray: Array<String> = listOf("a", "b", "c").toTypedArray() // converting from list to array
    // Kotlin's Array<Int> -> Java's Integer[] (boxed)
    // Kotlin's IntArray -> Java's int[] (no boxing)
    val intArray = IntArray(5) // creating new array of primitives . way 1
    println(intArray.joinToString ("-"))
    val intArray2 = intArrayOf(1,2,3) // creating new array of primitives . way 2
    println(intArray2.joinToString ("-"))
    val intArray3 = IntArray(5,{it})  // creating new array of primitives . way 3
    println(intArray3.joinToString ("-"))
    // Kotlin support same set of extension functions for arrays as for collections
    intArray3.forEachIndexed { index, element -> println("Element $index is: $element") }

}

class Person(val name: String)

class PersonParser : DataParser<Person> {
    override fun parseData(input: String?,
                           output: MutableList<Person>,
                           // list is non-null - callers need to receive error messages
                           // elements are nullable - not every Person will have associated error
                           // list is mutable - method needs to add elements in the list
                           errors: MutableList<String?>) {

    }
}

class FileIndexer : FileContentProcessor {
    override fun processContents(path: File,
                                 binaryContents: ByteArray?,
                                 // nullable param - file can be either binary either represented as text
                                 // elements are non-null - lines of file are never null
                                 // Collection is read-only - content of the file isn't going to be changed
                                 textContents: MutableList<String>?) {
        println("processed")
    }

}