/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    // strings in Kotlin are the same objects as in Java - no additional wrappers
    // but Kotlin provides additional extension functions

    // split function
    // unlike Java it is clear when argument is treated as Regex, and when as a char
    var split = "12.345-6.A".split("\\.".toRegex())
    println("split = $split")
    split = "12.345-6.A".split(".")
    println("split = $split")
    split = "12.345-6.A".split(".","-") // arbitrary number of delimiters
    println("split = $split")

    // parsing strings without Regex
    val path = "/home/admin/study/kotlin/file.doc"
    parsePath(path)
    parsePathWithRegex(path)

    // triple-quoted string allows to use multiline strings
    val query1 = """select *
                     .from table
                    .where id=1
                """
    println("query1 = ${query1}")

    val id=1
    val query2 = """select *
                     .from table \n
                    .where id=$id
                """
    // \n = will not make new line
    // $ can be use fro templates
    println("query = ${query2.trimMargin(".")}") // trimMargin is used for left Margin

}

fun parsePath(path: String) {
    val dir = path.substringBeforeLast("/") // helpful methods to avoid using error prone Regex
    val fullName = path.substringAfterLast("/")
    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")
    println("dir: $dir, fileName = $fileName, extension = $extension")
}

fun parsePathWithRegex(path: String) {
    // regex: (any char) + (greedy search of  /) (any char) (greedy sear of .) (any char)
    val regex = """(.+)/(.+)\.(.+)""".toRegex() /// triple-quoted string allows to avoid using escape chars
    val result = regex.matchEntire(path)
    if (result != null) {
        val(dir,fileName,extension) = result.destructured // return destructured value
        println("dir: $dir, fileName = $fileName, extension = $extension")
    }

}
