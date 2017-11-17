import java.time.LocalDate
import java.time.Period

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {

    println("dsl with extension om primitives")

    val yesterday = 1.days.ago
    val tomorrow = 1.days.fromNow
    println("yesterday = $yesterday")
    println("tomorrow = $tomorrow")

}

// using extension properties
val Int.days: Period get() = Period.ofDays(this) // on primitive
// here '-' and '+' are conventions matching minus() and plus()
val Period.ago: LocalDate get() = LocalDate.now() - this // on regular type
val Period.fromNow: LocalDate get() = LocalDate.now() + this