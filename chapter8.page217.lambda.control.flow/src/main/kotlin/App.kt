/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {

    println("control flow in higher-order functions")

    val people = listOf(Person("Alice",29),Person("Bob",31))

    // non-local return
    // if 'return' is used in a lambda it returns from the function in which lambda wa called.
    lookForAlice(people)
    val found = lookForAliceLambda(people) // and functions with real returning value
    println("main(): Alice was found! $found")
    lookForAliceLambdaUnit(people) // works for Unit functions


    // non-local return is only possible if function that takes lambda argument is inlined
    lookForAliceLambdaNoInline(people) {
        // as lookForAliceLambdaNoInline() is not inlined, action() can be stored and called after function itself
        // so it will be to late for the lambda to affect outer function
        for (person in it) {
            if (person.name == "Alice") {
                println("main(): Found!")
                // return  compilation error
            }
        }
        println("main(): We tried to find Alice") // this line will be printed anyway
    }

    // local return: return with label
    lookForAliceLabel(people)
    lookForAliceLabelAlternative(people)

    // 'return' returns from the closest function declared using 'fun' keyword
    // anonymous functions: local returns by default
    // NB anonymous function is just another syntactic form of lambda
    lookForAliceAnonymous(people)

}

data class Person(val name: String, val age: Int)

fun lookForAliceAnonymous(people: List<Person>)  {
    people.forEach (fun(person) {
        if (person.name == "Alice") {
            println("lookForAliceAnonymous(): Found!")
            return // this local lambda 'return' will return only from lambda, not from outer function
        }
    })
    println("lookForAliceAnonymous(): We tried to find Alice") // this line will be printed anyway
}

fun lookForAlice(people: List<Person>) {
    for (person in people) {
        if (person.name == "Alice") {
            println("lookForAlice(): Found!")
            return
        }
    }
    println("lookForAlice(): Alice is not in the list")
}

fun lookForAliceLabelAlternative(people: List<Person>)  {
    people.forEach {
        if (it.name == "Alice") {
            println("lookForAliceLabelAlternative(): Found!")
            return@forEach // this local lambda 'return' will return only from lambda, not from outer function
        }
    }
    println("lookForAliceLabelAlternative(): We tried to find Alice") // this line will be printed anyway
}

fun lookForAliceLabel(people: List<Person>)  {
    people.forEach label@{
        if (it.name == "Alice") {
            println("lookForAliceLabel(): Found!")
            return@label // this local lambda 'return' will return only from lambda, not from outer function
        }
    }
    println("lookForAliceLabel(): We tried to find Alice") // this line will be printed anyway
}

fun lookForAliceLambdaUnit(people: List<Person>)  {
    people.forEach {
        if (it.name == "Alice") {
            println("lookForAliceLambdaUnit(): Found!")
            return // this local lambda 'return' will also return control flow from lookForAliceLambda()
        }
    }
    println("lookForAliceLambdaUnit(): Alice is not in the list") // so if 'Alice' was found this line wouldn't be printed
}

fun lookForAliceLambda(people: List<Person>): Person  {
    people.forEach {
        if (it.name == "Alice") {
            println("lookForAliceLambda(): Found!")
            return it // this local lambda 'return' will also return control flow from lookForAliceLambda()
        }
    }
    println("lookForAliceLambda(): Alice is not in the list") // so if 'Alice' was found this line wouldn't be printed
    return Person("Fake Alice",1)
}

fun lookForAliceLambdaNoInline(people: List<Person>, action : (List<Person>) -> Unit) {
    action(people)
}






