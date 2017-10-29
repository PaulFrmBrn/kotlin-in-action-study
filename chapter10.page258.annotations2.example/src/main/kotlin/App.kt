import java.util.*
import kotlin.reflect.KClass

/**
 * @author paul
 */
fun main(args: Array<String>) {
    println("serialisation example via annotations")
}

// ----------------------
// Using

interface Company{ val name: String }
data class CompanyImpl(override val name: String) : Company

data class AnotherPerson(val name: String,
                         @JsonName("alias") val firstName: String,
                         // nullable, because while deserialization there will be an error while setting age property -
                         // object will be serialized without it
                         @JsonExclude val age: Int? = null,
                         @DeserializeInterface(CompanyImpl::class) val company: Company,
                         @CustomSerializer(DateSerializer::class) val birthDate: Date)

// ----------------------
// Declaration

// annotations are only used to define the structure of metadata, so they can't contain any code
// annotations can be annotated - meta-annotations
// semantics - excludes property from serialization/deserialization process
@Target(AnnotationTarget.PROPERTY) // if '@Target' not used, annotation will be applicable to all kind of objects
annotation class JsonExclude

@Target(AnnotationTarget.ANNOTATION_CLASS) // this is declaration of meta-annotations
annotation class BindingAnnotation

// Annotation with @Target(AnnotationTarget.PROPERTY) can not be used in Java because concept of property doesn't exist in the language
// But using both with @Target(AnnotationTarget.PROPERTY) [for Kotlin] and @Target(AnnotationTarget.FIELD) [for] solves the problem

// Unlike Java Kotlin's default is @Retention(AnnotationRetention.RUNTIME) (stored in .class file available at runtime)

@BindingAnnotation
// semantics - gives specified name to the key in key/value pair in serialization/deserialization process
annotation class JsonName(val name: String) // 'val' is mandatory to all params of an annotation
// in Java annotations params are declared via methods, with special one 'value', witch name can be omitted
// in Kotlin annotations params are declared as constructor params, so names can be both present and omitted

// defines
// semantics - specifies implementation for deserialization of property of interface type
annotation class DeserializeInterface(val targetClass: KClass<out Any>) // declaring class reference as parameter type

interface ValueSerializer<T> {
    fun toJsonValue(value: T): Any?
    fun fromJsonValue(jsonValue: Any?): T
}

// semantics - specifies custom class to serializer/deserializer a property
annotation class CustomSerializer(val serializer: KClass<out ValueSerializer<*>>)
// if 'out' have been omitted than only ValueSerializer instance (not including its' inheritors) would have been acceptable

class DateSerializer : ValueSerializer<Date> {
    override fun toJsonValue(value: Date): Any? {
        TODO("not implemented")
    }

    override fun fromJsonValue(jsonValue: Any?): Date {
        TODO("not implemented")
    }

}