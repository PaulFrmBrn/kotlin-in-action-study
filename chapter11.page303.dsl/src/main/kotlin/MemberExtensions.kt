/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("member extension")

    // member extension - is extension function/property defined in a class
    // examples are built on basis of Exposed Framework (https://github.com/JetBrains/Exposed) - example of internal DSL

    // both eq() and primaryKey() are member extension functions for Column<T>
    // but theirs usage is restricted by the context of the call
    Country.select { Country.name eq "UK" }
    // Country.select { Country.name primaryKey "UK" } // compilation error - 'Unresolved reference: primaryKey' because of (1)

}

object Country: Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val name = varchar("name", 50)
}

open class Table { // defines table structure
    fun integer(name: String): Column<Int> {TODO()} // creates new column of type Integer
    fun varchar(name: String, length: Int): Column<String> {TODO()}// creates new column of type String
    // member extensions for Column types - inapplicable outside the Table class scope
    fun <T> Column<T>.primaryKey(): Column<T> {TODO()} // works with all Column types
    fun Column<Int>.autoIncrement(): Column<Int> {TODO()} // suits only Integers - restricting the receiver
}

fun Table.select(where: SqlExpressionBuilder.() -> Op<Boolean>) : Query { TODO() }

object SqlExpressionBuilder {
    // member extensions for Column types - inapplicable outside the SqlExpressionBuilder class scope (1)
    infix fun<T> Column<T>.eq(t: T): Op<Boolean> { TODO() }
}

interface Op<T> { /*stub*/ }
interface Query { /*stub*/ }
interface Column<T> { /*stub*/ }
