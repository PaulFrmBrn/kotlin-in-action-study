/**
 * @author PaulFrmBrn
 */
package delegate.kotlin.observable.implementation


import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * @author PaulFrmBrn
 */

// helper class providing property change listener processing
open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)
    fun addListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }
    fun removeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

// Class which notifies listeners of its property change
class Person(val name: String, age: Int, salary: Int): PropertyChangeAware() {

    // no more boilerplate code at all - ObservableProperty can be replaced with Kotlin's standard observer
    private val observer = {
        prop: KProperty<*>, oldValue: Int, newValue: Int -> changeSupport.firePropertyChange(prop.name,oldValue,newValue)
    }

    // not new instance creation but function call
    // expression after 'by' but should return an object with functions meet the convention
    var age: Int by Delegates.observable(age,observer)
    var salary: Int by Delegates.observable(salary,observer)
}