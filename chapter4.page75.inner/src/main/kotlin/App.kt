
import java.io.ObjectOutputStream
import java.io.FileOutputStream

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("inner and nested classes")

    // nested (static) classes don't have access to the outer class instance -
    // inner (static) classes have

    // Kotlin's default is nested classes. Using 'inner' makes them inner
    val kotlinButtonState = KotlinButton.KotlinButtonState()
    ObjectOutputStream(FileOutputStream("temp.out")).writeObject(kotlinButtonState)

    // Java's default is inner classes. Using 'static' makes them nested
    val javaButtonState = JavaButton().JavaButtonState() // inner class
    // trying serialize nested class will rise
    // 'Exception in thread "main" java.io.NotSerializableException: JavaButton'
    // because buttonState implicitly stores a reference to a Button instance, which is not Serializable
    ObjectOutputStream(FileOutputStream("temp.out")).writeObject(javaButtonState)

    // example of using Kotlin's inner class
    val someOtherInnerClass = KotlinButton().SomeOtherInnerClass()
    ObjectOutputStream(FileOutputStream("temp.out")).writeObject(someOtherInnerClass)

}