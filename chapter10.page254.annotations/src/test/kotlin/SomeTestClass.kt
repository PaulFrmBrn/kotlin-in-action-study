@file:JvmName("SomeTestClass") // example of use-site declaration with target - file
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

/**
 * @author PaulFrmBrn
 */
class SomeTestClass {

    // some single declarations in Kotlin corresponds to multiple Java declarations
    // so elements to be annotated can be specified via 'target'

    // in this case @Rule is applied to a property which is private, so it leads to
    //@Rule // runtime exception: The @Rule 'folder' must be public
    @get:Rule // use-site declaration should be used instead to mark only getter
    @Suppress("UNCHECKED_CAST") // annotations can be applied to arbitrary expressions
    val folder = TemporaryFolder()

    @Test
    fun testSomething() {
        println("tested")
    }

}