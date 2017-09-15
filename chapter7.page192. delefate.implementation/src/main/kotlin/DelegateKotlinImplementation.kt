package delegate.kotlin.implementation

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
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


class ObservableProperty(var propValue: Int, val changeSupport: PropertyChangeSupport) {
    // 'operator' describes convention for delegate
    // first param represents the instance for which the property is get or set
    // second param represents Kotlin property
    // can be member function or extension function
    operator fun getValue(p:Person, prop: KProperty<*>): Int = propValue
    operator fun setValue(p:Person, prop: KProperty<*>, newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        // notifies listeners about the change
        changeSupport.firePropertyChange(prop.name,oldValue,newValue)
    }
}

// Class which notifies listeners of its property change
class Person(val name: String, age: Int, salary: Int): PropertyChangeAware() {
    // no more boilerplate code
    // objects after 'by' is 'delegate'. It is automatically stored in a hidden property,
    // so calling getValue() leads to delegate.getValue()
    // still ObservableProperty can be eliminated
    var age: Int by ObservableProperty(age,changeSupport)
    var salary: Int by ObservableProperty(salary,changeSupport)
}