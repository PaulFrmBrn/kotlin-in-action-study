/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("variance")

    // variance is about safety while dealing with arguments with the same type but different type parameter
    // this call is safe, because printContents() doesn't change the list
    printContents(listOf("abc","def"))

    // this call is not safe, because addAnswer() does change the list (mutable list)
    val list = listOf("abc", "def")
    // addAnswer(list) // compilation error
    println(list.maxBy { it.length }) // runtime error, if line above could be uncommented: Integer cannot be cast to String

    // class and types - are no the synonyms, so as class vs subclass and type vs subtype relations are not the same abstracts
    // nullable vs nonnull: String and String? - same class (String) different types
    // generics: List<Int>, List<String?>, List<List<String>> - same class (List) different types

    // subtype definition:
    // a type B ia s subtype of a type A if value of B can be used whenever value of A is required
    // Int is subtype of Number
    // Int is subtype of Int
    // Int is NOT subtype of String
    // String is subtype of CharSequence (an interface)
    // Int is subtype of Int?
    // Int? is NOT subtype of Int
    // compiler performs subtype check every time assigning value to a variable or passing argument to a function
    doSmth(5)

    // invariance definition:
    // a generic class C is invariant on the type parameter if, for two different types A and B,
    // C<A> isn't a subtype or supertype of C<B>
    // NB in Java all classes are invariant

    // covariance definition:
    // a generic class C is covariant on the type parameter if, for two different types A and B,
    // when the fact B is subtype for A means that C<B> is subtype for C<A>
    //

}

fun printContents(list: List<Any>) {
    println(list.joinToString())
}

fun addAnswer(list: MutableList<Any>) {
    list.add(42)
}

fun doSmth(i: Int) {
    val n: Number = i // no error - Int is subtype of Number
    fun doSmthElse(s: String) = println(s)
    //doSmthElse(i) // compilation error - String is not subtype of String
}