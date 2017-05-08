/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    val name = if (args.isNotEmpty()) args[0] else "Kotlin"
    println("Hello, $name!") // string template. equivalent of Java's String concat
    main2(args)
    main3(args)
}

fun main2(args: Array<String>) {
    if (args.isNotEmpty()) {
        println("Hello, ${args[0]}!") // using expression
    }
}

fun main3(args: Array<String>) {
    println("Hello, ${if (args.isNotEmpty()) args[0] else "someone"}!") // nested quotes
}