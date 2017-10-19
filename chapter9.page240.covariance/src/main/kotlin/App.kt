/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("covariance")

    // covariance definition
    // if B is subtype of A, than C<B> is subtype of C<A>
    // covariance can be used only in 'out' positions, meaning that class can produce values of <T> but not consume
    // so subtyping is preserved
    feedAll(Herd(listOf(Animal(), Animal())))
    takeCareOfCats(Herd(listOf(Cat(),Cat(),Cat()))) // processing covariant types in here

    // so immutable List<T> is covariant on type parameter, and mutable MutableList is not,
    // because its type parameter appears in both 'in' and 'out' position

}

// <out T> requires all functions using <T> have <T> only  in 'out' position and not in 'in' position
interface Producer<out T> {
    fun produce() : T
}

// <T> is both in 'in' and 'out' position so nor <out T> neither <in T> can be used
interface Transformer<T> { // trying to use <out T> will lead to compilation error:
                           // Type parameter T is declared as 'out' but occurs in 'in' position in type T
    fun transform(t: T): T
    //             'in' 'out'
    //         consumes produces
}

open class Animal {
    fun feed() = println("fed!")
}

class Cat: Animal() {
    fun cleanLitter() = println("cleaned")
}

class Herd<out T: Animal>(val animal: List<T>) { // [1] <T> is declared as covariant
    // constructor val parameters are in neither 'in' nor 'out' position
    // that's because covariance protects instances from misuse after creation, and constructor can not be called after object is created
    // constructor var parameters are in 'in' position, because getter is created
    // but var params in constructor can appear with <out T> if all of them are private
    // because covariance only deals with classes's public API
    val size: Int get() = animal.size
    operator fun get(i: Int): T = animal[i]
}

fun feedAll(animals: Herd<Animal>) {
    for (i in 0 until animals.size){
        animals[i].feed()
    }
}

fun takeCareOfCats(cats: Herd<Cat>) {
    for (i in 0 until cats.size) {
        cats[i].cleanLitter()
    }
    // there is no compilation error in the line because <T> in Herd is declared as covariant in [1]
    // but if it had hadn't, there would nave been
    // compilation error: Type mismatch: inferred type is Herd<Cat> but Herd<Animal> was expected
    feedAll(cats)
}
