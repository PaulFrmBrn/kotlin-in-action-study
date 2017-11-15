/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {

    println("invoke")

    val greeter = Greeter("Hello")
    greeter.invoke("John") // invoke() can be called directly
    greeter("Jane") // or applying the convention
    println(greeter("Abraham", separator = "!"))

    // if not inlined, lambdas are compiled into classes implementing functional interfaces with defined invoke() method with corresponding signature

    val issue1 = Issue("Ticket-1", "Project1", "Bug", "Major")
    val issue2 = Issue("Ticket-2", "Project2", "Feature", "Normal")
    val issue3 = Issue("Ticket-3", "Project1", "Bug", "Critical")

    listOf(issue1,issue2,issue3).filter {
        it.project == "Project1" && it.type == "Bug" && (it.priority == "Major" || it.priority == "Critical")
    }.forEach { println(it) }

    // in order to make filtering predicate above more readable, the predicate itself can be replaced by lambda
    // or, if the lambda body is too big and therefore still not readable enough, in the separate instance of
    // functional interface

    // invoke() convention here is used inside filter() to call predicate
    listOf(issue1,issue2,issue3).filter(ImportantIssuesPredicate("Project1")).forEach { println(it) }

}

class Greeter(val greeting: String) {
    // invoke() is convention
    operator fun invoke(name: String) {
        println("$greeting, $name")
    }
    // invoke can be overloaded, have any signature and return type
    operator fun invoke(name: String, separator: String = ","): String {
        return "$greeting$separator $name"
    }

}

data class Issue(val id: String, val project: String, val type: String, val priority: String)

// functional interface implementation holding filtering condition
class ImportantIssuesPredicate(val project: String): (Issue) -> Boolean {

    override fun invoke(issue: Issue): Boolean {
        return issue.project == project && issue.isImportant()
    }

    // having lambda represented functional interface implementation gives a way to refactor complicated logic
    // e.g. split condition into several methods calls, still hiding the internals
    private fun Issue.isImportant(): Boolean {
        return type == "Bug" && (priority == "Major" || priority == "Critical")
    }
}