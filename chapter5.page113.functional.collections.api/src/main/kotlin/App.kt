/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("functional APIs for collections")

    // doesn't change the collection new collection as a result
    println(listOf(1,2,3,4).filter{ it % 2 == 0 })
    println(listOf(Person("Alice",29),Person("Bob",31)).filter { it.age > 30 })

}

data class Person(val name:String,val age: Int)