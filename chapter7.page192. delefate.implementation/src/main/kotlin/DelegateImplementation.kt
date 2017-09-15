package delegate.implementation

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

// helper class imcapsulating notification processing for the property
// but still this is boilerplate code can be eliminated
class ObservableProperty(val propName: String, var propValue: Int, val changeSupport: PropertyChangeSupport) {
    fun getValue(): Int = propValue
    fun setValue(newValue: Int) {
        val oldValue = propValue
        propValue = newValue
        // notifies listeners about the change
        changeSupport.firePropertyChange(propName,oldValue,newValue)
    }
}

// Class which notifies listeners of its property change
class Person(val name: String, age: Int, salary: Int): PropertyChangeAware() {

    // no more duplication thanks to backing field of ObservableProperty type
    // but still there is boilerplate code can be eliminated
    val _age = ObservableProperty("age",age,changeSupport)
    var age: Int
        get() = _age.getValue()
        set(value) { _age.setValue(value)}

    val _salary = ObservableProperty("salary",salary,changeSupport)
    var salary: Int
        get() = _salary.getValue()
        set(value) { _salary.setValue(value)}

}