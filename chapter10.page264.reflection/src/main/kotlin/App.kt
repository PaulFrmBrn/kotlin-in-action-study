import kotlin.reflect.KClass
import kotlin.reflect.KFunction1
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

/**
 * @author paul
 */
fun main(args: Array<String>) {
    println("reflection")


    // using Kotlin'n reflection API - kotlin.reflect
    val kClass: KClass<Person> = Person::class // Kotlin's KClass is Java's Class counterpart
    val alice = Person("Alice", 27)
    val aliceClass: KClass<Person> = alice.javaClass.kotlin // get the object's class at runtime
    println(aliceClass)
    aliceClass.memberProperties.forEach { println(it.name) }

    // fun foo(x: Int) = println(x) runtime error: introspecting local functions is not supported yet
    val function: KFunction1<Int, Unit> = ::foo // KCallable is superinterface for functions (e.g. KFunction1) and properties
    function.call(42) // Calling function via KCallable.call - takes vararg as argument - som not type safe
    //function.call("smth",43) // runtime error: with no compile time checks of params types
    function(42) // Calling function via KFunction1.invoke - takes only one param of specified type
    //function("smth",43) // compile time checks of params types

    // NB KFunctionN - are synthetically generated types - they can't be found in kotlin-reflect.jar

    val property = ::counter
    property.setter.call(21)
    println(property.get()) // calls a setter through reflection
    println(counter)

    val memberProperty: KProperty1<Person, Int> = Person::age // member property - property of the class, not an real object
    println(memberProperty.get(receiver = alice)) // object should be provided as an argument for which value is needed
    // println(memberProperty.get(receiver = "alice")) // compile error:

    // KClass, KCallable (incl. all inheritors), KParameter extend KAnnotatedElement - all of them can be annotated


}

var counter = 0

fun foo(x: Int) = println(x)

data class Person(val name: String, val age: Int)