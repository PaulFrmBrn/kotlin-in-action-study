/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("by delegation")

    val innerList = arrayListOf("foo", "bar")

    val delegatingCollection = DelegatingCollection(innerList)
    println("delegatingCollection contains foo = ${delegatingCollection.contains("foo")}")

    val delegatingCollectionWithBy = DelegatingCollectionWithBy(innerList)
    println("delegatingCollectionWithBy contains foo = ${delegatingCollectionWithBy.contains("foo")}")

}

// decorator helps to avoid fragility of inheritance
// Kotlin's built-in support for Decorator - generating backing object and forwarding methods

// Decorator pattern implementation
class DelegatingCollection<T>(val innerList : Collection<T> = ArrayList<T>()): Collection<T> {
    override val size: Int get() = innerList.size
    override fun contains(element: T): Boolean = innerList.contains(element)
    override fun containsAll(elements: Collection<T>): Boolean = innerList.containsAll(elements)
    override fun isEmpty(): Boolean = innerList.isEmpty()
    override fun iterator(): Iterator<T> = innerList.iterator()
}

// using 'by' keyword
class DelegatingCollectionWithBy<T>(innerList : Collection<T> = ArrayList<T>()) : Collection<T> by innerList {}

