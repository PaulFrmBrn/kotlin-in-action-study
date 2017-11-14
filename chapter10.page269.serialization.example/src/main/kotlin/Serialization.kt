import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties


/**
 * @author PaulFrmBrn
 */

fun serialize(obj: Any): String = buildString { serializeObject(obj) }

// this is common Kotlin's pattern - converting function parameter into an extension function receiver
// 'private' stands for not extending existing StringBuilder's API - this function is meaningless outside this particular domain
// extension function emphasize that StringBuilder is primary for this code block
private fun StringBuilder.serializeObject(obj: Any) {

    obj.javaClass.kotlin.memberProperties

            .filter { it.findAnnotation<JsonExclude>() == null } // keeping only properties without @JsonExclude

            .joinToStringBuilder(this, prefix = "{", separator = ",", postfix = "}") { prop ->

                // change the name of property if @JsonName exists
                val findAnnotation = prop.findAnnotation<JsonName>()
                val propName = findAnnotation?.name ?: prop.name
                serializePropName(propName) // serializing prop name
                append(": ")
                // no compile checks in here because types are unknown in compile time
                serializePropertyValue(prop.get(obj)) // getting prop value
            }

}

// inline from JKid - https://github.com/yole/jkid
fun <T> Iterable<T>.joinToStringBuilder(stringBuilder: StringBuilder, separator: CharSequence = ", ", prefix: CharSequence = "", postfix: CharSequence = "", limit: Int = -1, truncated: CharSequence = "...", callback: ((T) -> Unit)? = null): StringBuilder {
    return joinTo(stringBuilder, separator, prefix, postfix, limit, truncated) {
        if (callback == null) return@joinTo it.toString()
        callback(it)
        ""
    }
}

// simplified - only String as values are supported
fun StringBuilder.serializePropertyValue(propValue: Any?) {
    if (propValue !is String) throw UnsupportedOperationException("Only Strings are supported as prop values")
    append("\"").append(propValue).append("\"")
}

// simplified - no symbols escaping
fun StringBuilder.serializePropName(propName: String) {
    append("\"").append(propName).append("\"")
}

