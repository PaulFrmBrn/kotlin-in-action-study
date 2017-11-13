import java.util.*
import kotlin.reflect.KClass

/**
 * @author PaulFrmBrn
 */

@Target(AnnotationTarget.PROPERTY)
annotation class JsonExclude
//
//@Target(AnnotationTarget.ANNOTATION_CLASS)
//annotation class BindingAnnotation
//
//@BindingAnnotation
annotation class JsonName(val name: String)
