/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("hello!")
}

interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable") // method with default implementation - no special keyword
}

// open stands for classes which can be inherited
// by default classes are not open
open class RichButton : Clickable {
    fun disable() {} // this function is final
    open fun animate() {} // this function is final
    override fun click() = println("RichButton click") // this function is open because it overrides open super function
    // to make click() forbidden for inhabitants final should be used
}

// abstract class - can not have implementations
abstract class Animated {

    abstract fun aninate() //must be overridden in subclasses

    open fun stopAnimating() {} // non abstract methods are final by default

    fun animateTwice() {} // but can be marked as open
}

// final can not be sued for interfaces$ abstract and open - redundant