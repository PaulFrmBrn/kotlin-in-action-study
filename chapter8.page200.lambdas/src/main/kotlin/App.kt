/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("lambdas")

    // declaring lambda local variable
    // takes 2 Int's, returns Int
    val sum: (Int, Int) -> Int = { x, y -> x + y }
        /*param types*/ /*return type*/
    val sum2 = { x: Int, y: Int -> x + y } // compiler can infer types

    // takes nothing, returns тщерштп
    val action: () -> Unit = { println(42) }
    val action2 = { println(42) } // compiler can infer types

    var canReturnNull: (Int,Int) -> Int? = { x,y ->  null } // can return nullable result
    var funorNull: ((Int, Int) -> Int)? = null // can hold nullable value

    val url = "http:\\kotl.in"
    performRequest(url) {code , body -> println("body = $body")} // param could be renamed
    performRequest(url) {code , content -> println(content)} // but using the same param's names improves readability

    println("ab1c".filter { it in 'a' .. 'z' })

}

// declaring a paramter of a function type
fun performRequest(url: String, callabck: (code: Int, content: String) -> Unit) {
    callabck(2,"some text") // calling the lambda
}
   /*receiver type*/ /*name*/ /*param name*/ /*param function type*/ /*function return value type*/
fun String.          filter(  predicate:     (Char) -> Boolean):     String {
  /*parameter type of function passed as a parameter*/ /*return type of function passed as a parameter*/
       val sb = StringBuilder()
       for (index in 0 until length) {
           val element = get(index)
           if(predicate(element) ) {
               sb.append(element)
           }
       }
       return sb.toString()
}