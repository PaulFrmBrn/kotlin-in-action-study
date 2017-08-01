/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {

    val user = User(1, "john", "somewhere")

    saveUser(user)
    saveUserWithLocalFunction(user)
    saveUserWithLocalFunctionWithoutPassingUser(user)
    saveUserWithLocalExtnsion(user)

}

class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {

    if (user.name.isEmpty()) {
        throw IllegalAccessException("Can't save user ${user.id}: empty Name")
    }

    if (user.address.isEmpty()) {
        throw IllegalAccessException("Can't save user ${user.id}: empty Address")  // validation duplication
    }

    // other actions
}

// using local function
fun saveUserWithLocalFunction(user: User) {

    // validate(user,user.name,"Name") // compilation error - there is no info at this point about local function

    // local function
    fun validate(user: User, value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalAccessException("Can't save user ${user.id}: empty $fieldName")
        }
    }
    validate(user,user.name,"Name")
    validate(user,user.address,"Address")
    // other actions
}

// using local function and access to params
fun saveUserWithLocalFunctionWithoutPassingUser(user: User) {

    // validate(user,user.name,"Name") // compilation error - there is no info at this point about local function

    // local function has access to params
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalAccessException("Can't save user ${user.id}: empty $fieldName")
        }
    }
    validate(user.name,"Name")
    validate(user.address,"Address")
    // other actions
}

// using local extension and access to params
fun saveUserWithLocalExtnsion(user: User) {

    // validate(user,user.name,"Name") // compilation error - there is no info at this point about local function

    // local function has access to params
    fun User.validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalAccessException("Can't save user $id: empty $fieldName")
        }
    }
    user.validate(user.name,"Name")
    user.validate(user.address,"Address")
    // other actions
}
