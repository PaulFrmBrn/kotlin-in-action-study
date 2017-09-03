/**
 * @author paul
 */
fun main(args: Array<String>) {
    println("basic types")

    // Kotlin's 'Any' is analogue to Java's 'Object', but 'Any' represents both references and primitives
    // 'Any' is compiled into 'Objects' in bytecode. 'Any' is seen in Java as 'Object'
    val answer: Any = 42 // autoboxing
    val noAnswer: Any? = null

    // only this three methods are accessible on 'Any' without explicit cast to 'Object'
    answer.toString()
    answer.hashCode()
    answer.equals(null)
    //answer.wait() // compilation error
    // (answer as Object).wait()

    // Kotlin's 'Unit' is analogue to Java's 'void', but
    //  - it can be omitted in function declaration
    fun functionWithUnit(): Unit {}
    fun functionWithoutUnit() {}
    functionWithUnit()
    functionWithoutUnit()
    //  - it can be a generic type
    class NoResultProcessor: Processor<Unit> {
        override fun process() {
            // - no need to explicitly return from function
        }
    }
    NoResultProcessor().process()
    // The only value of 'Unit' is 'Unit'

    // 'Nothing' for functions which never terminate successfully/ Doesn't have any values
    fun fail(message: String): Nothing {
        throw IllegalStateException(message)
    }
    fun getAddress(company: Company) {
        val addr = company.address ?: fail("No address") // compiler infers that 'addr' is non-null Address
        println(addr.city)
    }

    getAddress(Company("Company1",Address("Boston","USA")))
    // getAddress(Company("Company1",null)) // runtime error: NPE
}



interface Processor<T> {
    fun process(): T
}


class Address(val city: String, val country: String) {}
class Company(val name: String, val address: Address?) {}