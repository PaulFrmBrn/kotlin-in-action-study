

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("annotations")

    oldFunc(1) // IDEA let's automatically replace deprecated oldFunc to newFunc

    // annotation params can be only:
    // primitives, strings, enums, class references (MyClass::class), other annotations (without @)
    // and arrays thereof (via arrayOf())

    // use-site declaration is placed in {@link SomeTestClass}

}

// properties can be used as annotation params only with 'const val' - known at compile time
const val newFuncName = "Use newFunc(index)"

@Deprecated(newFuncName, ReplaceWith("newFunc(index)")) // indicates function that should be used instead
fun oldFunc(index: Int) = println("old func")

fun newFunc(idenx: Int) = println("new func")
