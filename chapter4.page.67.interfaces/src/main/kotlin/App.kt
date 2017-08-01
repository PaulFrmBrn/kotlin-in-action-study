/**
 * @author PaulFrmBrn
 */

fun main(args: Array<String>) {
    Button().click()
    Button().showOff()

    val button2 = Button2()
    button2.click()
    button2.setFocus(true)
    button2.showOff()
    button2.setFocus(false)

    // Kotlin default implementations was designed for Java 6, so Both java Interface abd Java Class to support
    // the Interface are created. That's why implementing such interface in Java you will have to implement both
    // Interface abstract methods and methods with default implementation
}

interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable") // method with default implementation - no special keyword
}

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus")
    fun showOff() = println("I'm focusable") // same method from another implementation
}

// colon used instead of implements or extends
class Button : Clickable { // class can implement multiple interfaces but extend single class
    // override is mandatory (for safety purpose)
    override fun click() = println("i'm clicked")
}

// class can implement multiple interfaces but extend single class
class Button2 : Clickable, Focusable {
    override fun click() = println("i'm clicked")
    // compiler error without overriding common for both interfaces method
    // Kotlin forces programmer to provide his own implementation
    override fun showOff() {
        super<Clickable>.showOff() // specifies concrete implementation to call
        super<Focusable>.showOff() // this is Kotlin's analogue for Java's Focusable.super.showOff()
    }


}
