package plain.implementation

import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport

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
    var age: Int = age
        set(newValue) {
            val oldValue = field
            field = newValue
            // notifies listeners about the change
            changeSupport.firePropertyChange("age",oldValue,newValue)
        }
    var salary: Int = salary
        set(newValue) {
            val oldValue = field
            field = newValue
            // notifies listeners about the change
            changeSupport.firePropertyChange("salary",oldValue,newValue)
        }
}

