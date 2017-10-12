/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("generic classes")


    // type upper bound constraint
    // type arguments of specific instantiations must either of specifier type or it's subclasses
    println(24.half()) // Int
    println(24L.half()) // Long

    val str = StringBuilder("Hello World")
    ensureTrailingPeriod(str)
    println(str)

    NullableProcessor<String?>() // no compilation error
    NullableProcessor<String>() // no compilation error

    // NonnullProcessor<String?>() // compilation error
    NonnullProcessor<String>() // no compilation error

}


// Number - is upper value
fun <T: Number> T.half(): Double {
    return this.toDouble() / 2.0
}

fun <T> ensureTrailingPeriod(seq: T)
        where T: CharSequence, T: Appendable { // type argument must implement both interfaces, e.g. String Bsuilder
    if (!seq.endsWith('.')) { // CharSequence
        seq.append('.') // Appendable
    }
}

// generic interface
interface SimpleList<T> {
    operator fun get(Index: Int): T // <T> can be used as regular type without placing <T> before function name
}

// subclasses should provide a type argument: either specific
class StringList: SimpleList<String> {
    override fun get(Index: Int): String // signature has 'String' instead of <T>
            = "a" // dummy implementation
}

// or another type parameter
abstract class SimpleArrayList<S>: SimpleList<S> { // <S> is new  type parameter
    abstract override operator fun get(Index: Int): S // signature has type parameter <S>
}

// class can refer to itself as a type param
class SimpleString : Comparable<String> {
    override fun compareTo(other: String): Int = 0 // dummy implementation
}
interface SimpleComparable<T> {
    fun compareTo(other: T): Int
}


class NullableProcessor<T> { // specific instantiations can be of any type including nullable types
    fun process(value: T) {
        // value.hashCode() // compilation error NullableProcessor<T> is in fact NullableProcessor<T: Any?>
        value?.hashCode()
    }
}

// to guarantee type parameter is always non-null type, upper bound should be of non-null type
class NonnullProcessor<T : Any> { // specific instantiations can be of any type including nullable types
    fun process(value: T) {
         value.hashCode() // no compilation error
         // value?.hashCode() // unnecessary safe call
    }
}