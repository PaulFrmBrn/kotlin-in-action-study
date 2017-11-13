import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("serialization implementation")
}

fun serilaize(obj: Any): String = buildString { serializeObject(obj) }

// this is common Kotlin's pattern - converting function parameter into an extension function receiver
// 'private' stand for not extending existing StringBuilder's API - this function is meaningless outside this particular domain
// extension function emphasize that StringBuilder is primary for this code block
private fun StringBuilder.serializeObject(obj: Any) {

    val kClass = obj.javaClass.kotlin
    val properties = kClass.memberProperties
            .filter { it.findAnnotation<JsonExclude>() == null } // keeping only properties without @JsonExclude

    properties.joinToString(prefix = "{", postfix = "}") { prop ->
        val propName = prop.findAnnotation<JsonName>()?.name ?: prop.name // change the name of property if @JsonName exists
        serializeString(propName) // serializing prop name
        append(": ")
        // no compile checks in here because types are unknown in compile time
        serializePropertyValue(prop.get(obj)) // getting prop value
    }

    //append("")
}

data class Person(
        @JsonName("alias") val firstName: String,
        @JsonExclude val age: Int
)
