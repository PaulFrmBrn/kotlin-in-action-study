import com.sun.deploy.services.Service
import java.util.*

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("reified functions types")

    // due to type erasure normalIsA() can't be compiled
    // but inlined function can
    println(isA<String>("asd"))
    println(isA<String>(123))

    val list = listOf("1", 2, "3")
    println(list.filterIsInstance<String>()) // returns: [1, 3], because type argument is known at runtime
    // for this call line [1] in bytecode will be transformed into 'if (element is String) {'
    // because compiler is aware of specific type while performing inlining, so there is no need in type argument
    // and therefore there is no need in erasure

    // NB because Kotlin's inlined functions are accessible in Java as regular, types can be reified while Java call

    // Java class's references can be replaced with reified types
    val serviceImpl = ServiceLoader.load(Service::class.java)
    val serviceImpl2 = loadService<Service>() // reifying makes the code shorter


}

// fun <T> isA(value: Any) = value is T // compilation error: cannot check instance of erased type: T
inline fun <reified T> isA(value: Any) =  value is T // no compilation error

inline fun <reified T> Iterable<*>.filterIsInstance(): List<T> { // 'reified' prevent erasure
    val dest = mutableListOf<T>()
    for (element in this) {
        if (element is T) { // [1] no error
            dest.add(element)
        }
    }
    return dest
}

inline fun <reified T> loadService(): ServiceLoader<T>? {
    return ServiceLoader.load(T::class.java) // due to inlining and therefore reifying T real runtime type is known
}


