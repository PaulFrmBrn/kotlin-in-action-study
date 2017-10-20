import java.util.*
import kotlin.reflect.KClass

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("star projection")

    // Kotlin's Type<*> is the analogue of Java's Type<?>


    // MutableList<Any?> and MutableList<*> are not the same
    // contains element of any type
    val anyList: MutableList<Any?> = mutableListOf(1, "qwe", 'a')
    val charList: MutableList<Char> = mutableListOf('a', 'b', 'c')
    // contains element of some specific type, but it is unknown here
    val unknownTypeList: MutableList<*> = if (Random().nextBoolean()) anyList else charList
    // In this context MutableList<*> acts like MutableList<out Any?>, so
    // unknownTypeList.add(42) // compilation error: it's unsafe to consume elements of unknown type
    println(unknownTypeList.first()) // no error: it's safe to produce elements of unknown type

    // star projection can be used if information about type argument isn't important
    // when information about type argument is not important these both implementation means the same
    printFirst(listOf("John", "Jane")) // but star-projection is mor concise
    printFirstWithType(listOf("John", "Jane"))

    val validators = mutableMapOf<KClass<*>,FieldValidator<*>>() //[1]
    validators[String::class] = StringValidator
    validators[Int::class] = IntValidator

    // validators[String::class]!!.validate("") // compilation error:
    // int's unsafe because the declared validator type argument is *in [1]

    // explicit cast can be used, but it's still unsafe
    val stringValidator = validators[String::class] as FieldValidator<String> // compilation warning: unchecked cast
    stringValidator.validate("")
    // this approach is error prone also because in case of mistake of chasing right type where will be error
    // only in runtime and in the place where validation() is called [3]
    // but not in the place Validator is obtained form te map [2]
    val intValidator = validators[Int::class] as FieldValidator<String> // [2] real error - Int for String
    intValidator.validate("") // [3] runtime error

    Validators.registerValidator(String::class,StringValidator)
    Validators.registerValidator(Int::class,IntValidator)
    println(Validators[String::class].validate("Kotlin")) // API now is safe
    println(Validators[Int::class].validate(42)) // and error-prone code is hidden from the user of API
    //println(Validators[Int::class].validate("")) // compilation error

}

fun printFirst(list: List<*>) {
    if (list.isNotEmpty()) {
        println(list.first())
    }
}

fun <T> printFirstWithType(list: List<T>) {
    if (list.isNotEmpty()) {
        println(list.first())
    }
}

interface FieldValidator<in T> {
    fun validate(input: T): Boolean
}

object StringValidator: FieldValidator<String> {
    override fun validate(input: String) = input.isNotEmpty()
}

object IntValidator: FieldValidator<Int> {
    override fun validate(input: Int) = input >= 0
}

object Validators {
    private val validators = mutableMapOf<KClass<*>,FieldValidator<*>>() // the same as [1] but map is encapsulated

    fun <T: Any> registerValidator(kClass: KClass<T>, validator: FieldValidator<T>) {
        validators[kClass] = validator
    }

    @Suppress("UNCHECKED_CAST")
    operator fun <T: Any> get(kClass: KClass<T>): FieldValidator<T> =
            validators[kClass] as? FieldValidator<T> // here is unchecked cast
                    // but it's encapsulated in the class , so users of the API can not make mistake with it
                    ?: throw IllegalArgumentException("No validator for ${kClass.simpleName}")
}
