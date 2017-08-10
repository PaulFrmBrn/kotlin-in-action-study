/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("secondary constructors")

    // Secondary constructor in Kotlin is rather rare case comparing with multiple constructors Java
    // because in most cases default values help to avoid using Secondary constructor

    // calling different constructors of the same class
    println(View(1))
    println(View(1,false))
    println("---")
    println(Button(2))
    println(Button(3,false))

}

open class View { // no primary constructor and initializer
    var intValue: Int
    var boolValue: Boolean
    // secondary constructor
    constructor(intValue: Int) {
        this.intValue = intValue
        this.boolValue = true
        println("View first constructor")
    }
    // secondary constructor
    constructor(intValue: Int,boolValue: Boolean){
        this.intValue = intValue
        this.boolValue = boolValue
        println("View second constructor")
    }
    override fun toString(): String {
        return "View(intValue=$intValue, boolValue=$boolValue)"
    }
}

class Button: View {
    // will not compile without calling superclass constructor before opening curly brace
    // error in first 2 lines
    /*
    constructor(intValue: Int) {
        super(intValue)
        this.intValue = intValue
        this.boolValue = true
        println("Button first constructor")
    }
    */
    constructor(intValue: Int) : this(intValue,true) { // calling another constructor of the same class
        this.intValue = intValue
        println("Button first constructor")
    }

    constructor(intValue: Int,boolValue: Boolean) : super(intValue,boolValue) { // calling superclass constructor
        this.intValue = intValue
        this.boolValue = boolValue
        println("Button second constructor")
    }
    override fun toString(): String {
        return "Button(intValue=$intValue, boolValue=$boolValue)"
    }
}

// if there is primary constructor
open class View2(boolValue: Boolean) {
    var intValue: Int // 'val' can not be used here because in secondary constructor it will be reassigned
    // and initializer
    init {
        this.intValue = 42
    }
    // than all secondary constructors should call it at first
    constructor(intValue: Int) : this(true) {
        this.intValue = intValue
    }
}
