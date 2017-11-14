
/**
 * @author PaulFrmBrn
 */

@Target(AnnotationTarget.PROPERTY)
annotation class JsonExclude

@Target(AnnotationTarget.PROPERTY)
annotation class JsonName(val name: String)
