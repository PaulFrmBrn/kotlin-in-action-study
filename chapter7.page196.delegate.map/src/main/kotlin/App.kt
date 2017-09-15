/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("delegation map")

    val alice = Person()
    alice.setAttribute("name","Alice")
    println("name = ${alice.name}")

    val bob = PersonWithDelegate()
    bob.setAttribute("name","Bob")
    println("name = ${bob.name}")

}

// implementing pattern 'expando objects'
class Person {

    private val _attributes = hashMapOf<String,String>()

    // loading generic data
    fun setAttribute(attrName: String, attrValue: String) {
        _attributes[attrName] = attrValue
    }

    // implementing delegation by hand
    val name: String
        get() = _attributes["name"]!!

}

// implementing pattern 'expando objects'
class PersonWithDelegate {

    private val _attributes = hashMapOf<String,String>()

    // loading generic data
    fun setAttribute(attrName: String, attrValue: String) {
        _attributes[attrName] = attrValue
    }

    // using map '_attributes' as delegate
    // there extension function getValue() and setValue() defined for the Map
    val name: String by _attributes
}