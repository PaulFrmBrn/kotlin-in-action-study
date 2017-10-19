/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("contravariance")

    // contravariance definition
    // if B is subtype of A, than C<A> is subtype of C<B>
    // contravariance can be used only in 'in' positions, meaning that class can consume values of <T> but not produce
    // so subtyping is reversed
    val anyComparator = Comparator<Any> {
        e1, e2 -> e1.hashCode() - e2.hashCode()
    }
    val strings = listOf("foo","bar")
    // due to declaration od sortedWith() and String used as type parameter argument of type Comparator<String> is awaited
    // but as Comparator type parameter is declared as 'in' Comparator<Any> can be passed
    strings.sortedWith(anyComparator)

    // Assuming that Cat is subtype of Animal
    // the fact that Producer is declared with <out T> means Producer<Cat> is subtype of Producer<Animal>
    // the fact that Consumer is declared with <in T> means Consumer<Animal> is subtype of Consumer<Cat>

    // covariance and contravariance can be combined in a single class for different type params
    val animalToInt: (Animal) -> Int = { it.hashCode() }
    val catCode = Cat().enumerateCats(animalToInt) //
    println(catCode)

}

// (Cat) -> Number stands for Function1<Cat,Number>
// and as Function1 is declared with <in T> and <out R>
// so Cat is contravariant and Number is covariant
// and so (Animal) -> Int can be passed as (Cat) -> Number
fun Cat.enumerateCats(f: (Cat) -> Number): Number {
    return f(this)
}

open class Animal

class Cat: Animal()

