/**
 * @author PaulFrmBrn
 */

// to use extension function it should be imported
import string.extension.lastChar

// it could be renamed
import string.extension.lastChar as last

import collection.extension.joinToString

// specific "degenerified" version of collection.extension.joinToString
import string.extension.join

// extension functions are effectively syntactic sugar over static method calls

fun main(args: Array<String>) {

    println("Hello, world".lastChar())
    println("Hello, world".last())

    val list = listOf(1, 2, 3)
    println(list.joinToString())

    println(listOf("uno","due","tre").join())
    //println(listOf(1,2,3).join()) // compile error - type mismatch

    // with member functions inheritance in Kotlin works like in Java
    val view: View = Button()
    view.click() // will print 'button clicked'

    // for extension function there is no overriding
    view.showOff() // will print 'i'm a view'

    // if there is member function with the same signature as extension, first has a priority
}

open class View {
    open fun click() = println("view clicked")
}

class Button: View() {
    override fun click() = println("button clicked")
}

fun View.showOff() = println("i'm a view")
fun Button.showOff() = println("i'm a button")







