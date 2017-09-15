import java.beans.PropertyChangeListener
import plain.implementation.Person as PersonPlain
import delegate.implementation.Person as PersonDelegate
import delegate.kotlin.implementation.Person as PersonDelegateKotlin
import delegate.kotlin.observable.implementation.Person as PersonDelegateKotlinObservable

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {

    println("delegate implementation")

    val john = PersonPlain("John",30,5000)
    john.addListener(PropertyChangeListener {
        println("${john.name}'s ${it.propertyName} has changed from ${it.oldValue} to ${it.newValue}")
    })
    john.age = 31

    val jane = PersonDelegate("Jane",27,4300)
    jane.addListener(PropertyChangeListener {
        println("${jane.name}'s ${it.propertyName} has changed from ${it.oldValue} to ${it.newValue}")
    })
    jane.salary = 4400

    val jack = PersonDelegateKotlin("Jack",31,4900)
    jack.addListener(PropertyChangeListener {
        println("${jack.name}'s ${it.propertyName} has changed from ${it.oldValue} to ${it.newValue}")
    })
    jack.salary = 5000

    val bill = PersonDelegateKotlinObservable("Bill",31,100500)
    bill.addListener(PropertyChangeListener {
        println("${bill.name}'s ${it.propertyName} has changed from ${it.oldValue} to ${it.newValue}")
    })
    bill.salary *= 2

    println(
    """
    var prop: Type by Delegate()
    """
    )
    // will be translated into
    println(
    """
    private val <delegate> = MyDelegate()
    var prop: Type
       get() = <delegate>.getValue(this,<property(prop)>)
       set() = <delegate>.setValue(this,<property(prop)>,field)
    """
    )
}