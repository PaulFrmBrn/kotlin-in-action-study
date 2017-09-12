/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("destructuring declarations and component functions")

    // val (a,b) = p => val.component1();val b = component2
    val (x, y) = Point(10, 20)
    println(x)
    println(y)

    val (s, t) = Point2(10, 20)
    println(s)
    println(t)

    // common pattern is to use data classes (with component functions out of the box) to unpack composite return values
    val (name, extension) = splitFilename("App.kt")
    println(name)
    println(extension)
    val (name2, extension2) = splitFilename2("Ap2.kt")
    println(name2)
    println(extension2)
    val (name3, extension3) = splitFilename2("App3.kt")
    println(name3)
    println(extension3)

    // destructuring declarations work anywhere variables can be declared
    val map = mapOf("Oracle" to "Java", "JetBrains" to "Kotlin")
    printEntries(map)

}

fun printEntries(map: Map<String,String>) {
    for ((key,value) in map) { // in -> Iterator |+|  val (key,value) => component1(); component2()
        println("$key - > $value")
    }
}

data class NameComponents(val name: String, val extension: String)

fun splitFilename(fullName: String): NameComponents {
    val result = fullName.split('.',limit = 2)
    return NameComponents(result[0],result[1])
}

// componentN function is also defined on arrays and collections. Up to 5 first element can be obtained
fun splitFilename2(fullName: String): NameComponents {
    val (name,extension) = fullName.split('.', limit = 2)
    return NameComponents(name,extension)
}

// Pair or triple can be used to hold return values - less verbouse both less clear
fun splitFilename3(fullName: String): Pair<String,String> {
    val (name,extension) = fullName.split('.', limit = 2)
    return Pair(name,extension)
}

// N - position of the variable in the declaration
data class Point(val x: Int, val y: Int)
// componentN functions are automatically generated for every property for data classes

// manual declaration of component functions
class Point2(val x: Int, val y: Int) {
    operator fun component1() = x
    operator fun component2() = y
}