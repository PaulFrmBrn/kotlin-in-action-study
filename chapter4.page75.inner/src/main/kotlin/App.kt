
import java.io.ObjectOutputStream
import java.io.FileOutputStream

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("inner and nested classes")

    // nested classes donn't have access to the outer class instance by default -
    // inner class - has
    val buttonState = ButtonJava().ButtonState() // inner class

    // trying serialize nested class will rise
    // 'Exception in thread "main" java.io.NotSerializableException: ButtonJava'
    // because buttonState implicitly stores a reference to a Button instance, which is not Serializable
    ObjectOutputStream(FileOutputStream("temp.out")).writeObject(buttonState)

}