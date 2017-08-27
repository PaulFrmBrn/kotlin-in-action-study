/**
 * @author paul
 */
fun main(args: Array<String>) {
    println("java functional interfaces")

    // in Kotlin lambda can be passed instead of Java's functional interface or SAM interface
    // sam - single abstract method (e.g. Runnable, Callable etc.)
    // conversion will be performed automatically
    val button = Button(1)
    button.onClickListener { view -> println("button view id is ${view.id}") }
    button.click()

    val button2 = Button(2)
    button2.onClickListener { view -> println(42)}
    // not direct analogue, but in this case instance of object implementing OnClickListener
    // is created each time the code below runs
    button2.onClickListener(object: OnClickListener {
        override fun onClick(view: View?) {
            println(42)
        }
    })

    // if lambda doesn't capture any variable, then lambda instance is created only once, so direct analogue is
    val listener = OnClickListener { println(42) }
    button2.onClickListener(listener)
    button2.click()
    // if lambda does, than anonymous class for lambda holding captured value as a field is created and
    // an object of this class each time lambda is created

    // the behaviour for 'inline' functions taking a lambda as a parameter differs - no anonymous classes are created
    // for lambda at all

    // SAM constructor
    fun createAllDoneRunnable(): Runnable {

        // is a function that converts a lambda to an instance of a functional interface explicitly
        // returns an instance of the class implementing interface
        // constructor name matches functional interface name
        // single argument - lambda that will be used as a body of SAM in functional interface
        val runnable = Runnable { println("All is done!") }

        return runnable
    }
    createAllDoneRunnable().run()


    // SAM constructor can be used to store functional interface instance generated from lambda
    // to avoid using more verbose object declaration
    val multiListener = OnClickListener { view ->
        // in lambda 'this' refers to the containing object
        // in anonymous object refers to the object itself
        val text = when (view.id) {
            3 -> "third"
            4 -> "fourth"
            else -> "unknown"
        }
        println(text)
    }

    val button3 = Button(3)
    button3.onClickListener(multiListener)
    button3.click()

    val button4 = Button(4)
    button4.onClickListener(multiListener)
    button4.click()


}