/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("use-site variance")

    // declaration-site variance - the ability to specify variance modifiers on class declaration
    // e.g. Kotlin's 'in' and 'out' modifiers in class declaration

    // use-site variance - the ability to specify variance modifiers were type with type parameters is used
    // e.g. Java's '? extends' and '? super' when type is used
    // e.g. Kotlin's 'in' and 'out' modifiers when type is used

    // Kotlin's MutableList is invariant because it both produces and consumes values of type parameter
    // that's why it's impossible to use declaration-site variance
    // but use-site variance can be used in place instead

    copyData(mutableListOf("a","b","c"), mutableListOf("d","e"))
    // copyData(mutableListOf("a","b","c"), mutableListOf(Any(),Any())) // compilation error
    copyData2(mutableListOf("a","b","c"), mutableListOf(Any(),Any()))

    // but more elegant way to express this can be used - use-site variance
    copyDataCovariance(mutableListOf("a","b","c"), mutableListOf(Any(),Any()))
    // this is called type projection: source is not regular MutableList, but its projection (restriction)
    // only methods that produce values of type parameter can be used

    val list: MutableList<out Number> = mutableListOf(42)
    // list.add(43) // compilation error
    // Out-projected type 'MutableList<out Number>' prohibits the use of
    // 'public abstract fun add(element: E): Boolean defined in kotlin.collections.MutableList'

}

// source only produces elements, while destination only consumes
// in this situation elements types doesn't need to match exactly
fun <T> copyData(source: MutableList<T>, destination: MutableList<T>) = destination.addAll(source)

// this s the way to allow function to work with different types
fun <T : R, R> copyData2(source: MutableList<T>, destination: MutableList<R>) = destination.addAll(source)

// use-site variance
fun <T> copyDataCovariance(source: MutableList<out T>, destination: MutableList<in T>)  = destination.addAll(source)