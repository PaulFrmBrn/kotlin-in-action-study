import java.awt.Button
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("lambdas intro")

    // 1. using lambdas instead Object expressions
    // 'Object expressions'
    val button= Button()
    button.addActionListener( object : ActionListener {
        override fun actionPerformed(e: ActionEvent?) { // verbouse
            println("button is pushed")
        }
    })
    // lambda
    button.addActionListener({println("button is pushed")}) // concise

    // 2. using lambdas with collections
    val people = listOf(Person("John",30),Person("Jane",27))
    println(findTheOldest(people)) // procedural style
    println(people.maxBy{ it.age }) // functional style
    println(people.maxBy( Person::age )) // if delegating to a function or property can be replaced with member reference

    // 3. syntax
    // can be stored in a value
    val sum = {x: Int, y: Int -> x + y }
                /*params*/    /*body*/
    println(sum(1,2)) // and called

    run { println(42)} // lambda can be called directly
    ;{ println(42)} () // or like this

    people.maxBy({ p:Person -> p.age }) // lambda without any syntax shortcuts - verbouse
    people.maxBy() { p:Person -> p.age } // if the last argument in a function call lambda can be moved out of parentheses
    people.maxBy { p:Person -> p.age } // if the only argument in a function call parentheses can be omitted
    people.maxBy { p -> p.age } // if the type can be inferred it can be omitted
    people.maxBy { it.age } // if there is only one parameter its declaration can be replaced with default name 'it'

    // lambda can consist of multiple expressions
    val sumWithSout = { x:Int, y:Int ->
        println("Computing the sum of $x and $y")
        x+y
    }
    println(sumWithSout(3,4))

    // 4. lambda can access local variables of containing scope
    printMessageWithPrefix("Error: ",listOf("403 Forbidden","404 Not Found"))
    // and even modify them
    // wrapper object is created in this case to be stored 'near' the lambda and to store the reference to the local variable
    printMessageWithPrefixAndCount("Error: ",listOf("403 Forbidden","404 Not Found"))

    // 5. member reference
    val getAge = Person::age
              /*class*/ /*member*/

    val getAge2 = { p:Person->p.age } // lambda has the same type as member reference
    run(::salute) // reference to a top-level function

    val createPerson = ::Person
    val sting = createPerson("Sting", 65) // reference to a constructor
    println(sting)

    fun Person.isAdult() = age >= 18
    val predicate  = Person::isAdult // reference to the extension function

    val stingsAgeFunction = sting::age // bound member reference - reference to the method on a specific object
    println("stingsAgeFunction = ${stingsAgeFunction()}")

}

fun printMessageWithPrefix(prefix:String, messages: Collection<String>) {
    messages.forEach { println("$prefix $it") }
}

fun printMessageWithPrefixAndCount(prefix:String, messages: Collection<String>) {
    var clientErrors = 0 // captured by lambda - Ref object is created to store reference clientErrors - mutable variable (var)
    var serverErrors = 0 // final variables (val) references are just copied to be stored 'near' the lambda code
    messages.forEach {
        if (it.startsWith("4")) {
            clientErrors++
        } else if (it.startsWith("5")) {
            serverErrors++
        }
        println("$prefix $it")
    }
    println("$clientErrors client errors, $serverErrors server errors ")

}

fun salute() = println("Salute!")

data class Person(val name: String, val age: Int)

fun findTheOldest(people: List<Person>): Person? {  // verbouse
    var maxAge = 0
    var theOldest: Person? = null
    for (person in people) {
        if (person.age > maxAge) { // error prone code - it's easy to use '<' in here
            maxAge = person.age
            theOldest = person
        }
    }
    return theOldest

}