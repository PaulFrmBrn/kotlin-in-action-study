/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("nullability 3")

    // extension for nullable types
    var string: String? = null
    println(string.isNullorBlank()) // extension functions can be called on null values without safe calls
    // let-function is also designed fo dealing with null values
    // string.let { unsafePrintln(it) } compilation error: Type mismatch: String? and String

    // nullability of type parameters
    // by default all types params are nullable
    // T is inferred as Any?
    fun <T> printHashCode(t: T) {
        // println(t.hashCode()) compilation error
        println(t?.hashCode())
    }
    printHashCode(null)
    // to make type param non-null it should be bound
    fun <T: Any> printHashCodeSafe(t: T) {
        println(t.hashCode())
    }
    // printHashCodeSafe(null) // compilation error
    printHashCodeSafe("1")

    // nullability and Java
    // Kotlin supports non-null types, Java - doesn't

    // compiler can infer info about nullability from annotations (JetBrains, JSR-305, Android, etc.)
    // JavaClass().methodWithAnnotations(null) // compilation error
    JavaClass().methodWithAnnotations("not null")

    // without annotations Jav type becomes 'platform type'
    // 'platform type' can be both nullable and not-null - compiler will allow all operations
    // so, dealing with nulls from Java code is only programmers responsibility

    // with functions
    // yellAt(Person(null)) // runtime error error at [1]: kotlin.TypeCastException: null cannot be cast to non-null type java.lang.String
    yellAt(Person("John"))
    // another option is to interpret Javs type as nullable
    yellSafelyAt(Person(null)) // no error
    yellSafelyAt(Person("John"))

    // with variables
    val name: String = Person("Jack").name // no compilation error
    val name2: String? = Person("Jack").name // no compilation error
    // val name3: String = Person(null).name // runtime error

    // with inheritance
    val stringPrinter: StringPrinter = StringPrinter()
    // stringPrinter.process(null) // compilation Null can not be a value of a non-null type String
    stringPrinter.process("non-null")
    val nullableStringPrinter: NullableStringPrinter = NullableStringPrinter()
    nullableStringPrinter.process(null)
    nullableStringPrinter.process("non null")
    // IllegalArgumentException: Parameter specified as non-null is null: method StringPrinter.process, parameter value
    StringProcessorRunner.runWithNull()

}

class StringPrinter: StringProcessor {
    // Kotlin generates null check for the params because this code can be called from Java
    override fun process(value: String) { // no compilation error
        println(value)
    }
}
class NullableStringPrinter: StringProcessor {
    override fun process(value: String?) { // no compilation error
        if (value != null) {
            println(value)
        }
    }
}

fun yellAt(person: Person) {
    // if you are shure - don't use additional checks
    println(person.name.toUpperCase() + "!!!") // [1] no compilation error but runtime error
}

fun yellSafelyAt(person: Person) {
    // if you are NOT shure - use checks
    println("whispering: " + (person.name?: "Anyone").toUpperCase() + "!!!")
}

// extension function for nullable type
// in Kotlin 'this' can store null because under the hood 'this' - is the first param of generated static method
fun String?.isNullorBlank() =
        this == null || this.isBlank() // smart cast from String? to String

fun unsafePrintln(string: String) = println(string)

