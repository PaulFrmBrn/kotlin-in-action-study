import java.beans.PropertyChangeListener
import plain.implementation.Person as PersonPlain
import delegate.implementation.Person as PersonDelegate

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {

    println("delegate implementation")

    val john = PersonPlain("John",30,5000)
    john.addListener(PropertyChangeListener {
        println("Property '${it.propertyName}' has changed from ${it.oldValue} to ${it.newValue}")
    })
    john.age = 31

    val jane = PersonDelegate("Jane",27,4300)
    jane.addListener(PropertyChangeListener {
        println("Property '${it.propertyName}' has changed from ${it.oldValue} to ${it.newValue}")
    })
    jane.salary = 4400


}