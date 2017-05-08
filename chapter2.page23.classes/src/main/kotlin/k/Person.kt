package k

/**
 * @author PaulFrmBrn
 */
// value object concept - only data with no code
// default visibility - public
// properties in Kotlin is combination of Java's fields and accessors
class Person(val name: String, // read-only property - field and getter
             var isMarried: Boolean // modifiable property: field + setter + getter
             )
// Custom accessors can be used