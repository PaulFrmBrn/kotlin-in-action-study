import java.lang.IllegalArgumentException

/**
 * @author PaulFrmBrn
 */

interface Expr
class Num (val value: Int) : Expr
class Sum (var left: Expr, val right: Expr) : Expr //

fun main(args: Array<String>) {
    val sum = Sum(Sum(Num(1), Num(2)), Num(4))
    println(evalLikeJava(sum))
    println(evalLikeKotlin(sum))
    println(eval(sum))
    println(evalWithLogging(sum))
}

fun evalLikeJava(expr : Expr) : Int {
    if (expr is Num) {
        val num = expr as Num // redundant explicit cast
        return num.value // return can not be omitted in here because this is regular function - not expression body
    }
    var expr1 = expr
    if (expr1 is Sum) {
        // using smart cast - expr1 is now of type Sum without explicit cast with "as"
        // IDE highlits left because it is var, but not val
        // expr1 = Num(0) // if this line is removed the compilation will fail - reassigning variable after smart cast
        return evalLikeJava(expr1.left) + evalLikeJava(expr1.right)
    }
    throw IllegalArgumentException("Unknown Expression")
}

fun evalLikeKotlin(expr : Expr) : Int =
    if (expr is Num) {
        expr.value // last expression of the block is returned as a result
    } else if (expr is Sum) {
        evalLikeKotlin(expr.left) + evalLikeKotlin(expr.right)
    } else {
        throw IllegalArgumentException("Unknown Expression")
    }

// idiomatic Kotlin eval version
fun eval(expr : Expr) : Int =
    when (expr) { // when checks not equality but type
        is Num -> expr.value // smart cast
        is Sum -> eval(expr.left) + eval(expr.right) // smart casts
        else -> throw IllegalArgumentException("Unknown Expression")
    }

fun evalWithLogging(expr : Expr) : Int =
    when (expr) {
        is Num -> {
            println("value = ${expr.value}")
            expr.value
        }
        is Sum -> {
            val left = evalWithLogging(expr.left)
            val right = evalWithLogging(expr.right)
            println("sum = $left + $right")
            left + right // last expression of the block will be return as a result
        } // smart casts
        else -> throw IllegalArgumentException("Unknown Expression")
    }
