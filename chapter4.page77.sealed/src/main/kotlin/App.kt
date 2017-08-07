/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {

    println("sealed classes")

    val expr = Sum(Num(5), Num(3))
    println("expr = ${eval(expr)}")

    val sExpr = SExpr.SSum(SExpr.SNum(5), SExpr.SNum(3))
    println("sExpr = ${eval(sExpr)}")

}

interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(expr: Expr): Int =
        when (expr) {
            is Num -> expr.value
            is Sum -> eval(expr.right) + eval(expr.left)
        // if this line is commented, there will be compilation error:
        // 'Kotlin: 'when' expression must be exhaustive, add necessary 'else' branch'
            else -> throw IllegalArgumentException()
        // even worse: if new subclass is added, it will be handled via 'else' branch - source of subtle bugs
        }

// foe sealed classes all direct subclasses must be nested in the superclass or file (Kotlin 1.1)
// no need to use 'open'
sealed class SExpr {
    // all subclasses
    class SNum(val value: Int) : SExpr()
    class SSum(val left: SExpr, val right: SExpr) : SExpr()
    // adding new subclass will lead to compilation fail in eval
    //class SMultiply(val left: SExpr, val right: SExpr) : SExpr()
}

// adding new subclass will lead to compilation fail in eval
//class SMultiply : SExpr()

fun eval(expr: SExpr): Int =
        when (expr) {
            is SExpr.SNum -> expr.value
            is SExpr.SSum -> eval(expr.right) + eval(expr.left)
        // there is no need to provide 'else' branch, because all possible subclasses are known in compile time
        }