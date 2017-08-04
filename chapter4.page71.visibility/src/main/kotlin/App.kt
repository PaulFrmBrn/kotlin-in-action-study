/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {

    println("visibilty")

    // default visibility - public
    // package-protected is not presented as in Kotlin uses packages only for code organization, but not visibility

    // Differences from Java are:
    // -- private - can be use for classes. Such classes are visible within a file
    // -- protected - can't be used from within the same package
    // -- public
    // -- pacjage-private - ABSENT
    // -- internal - visible in a module (set of Kotlin files compiled together). Give stronger encapsulation, which
    //               can't be broken by creating external code in the same package

    // NB There is some specific in Java interop due to the fact that Kotlin's 'internal' becomes 'public' in bytecode

}

interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus")
    fun showOff() = println("I'm focusable") // same method from another implementation
}

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk")
}

fun TalkativeButton.giveSpeech() { // public giveSpeech() is trying to expose less-visible internal TalkativeButton
    yell(); // trying to access private field
    whisper(); // trying to access protected field
}
