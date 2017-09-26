import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantReadWriteLock

/**
 * @author PaulFrmBrn
 */
fun main(args: Array<String>) {
    println("inline functions")

    // each time lambda is used extra class is created
    // if lambda captures some variable, than new object is created on every invocation

    // function inlining helps avoiding these overhead

    // 1. ------------------------------------
    // and for function marked 'inline' for this Kotlin code snippet
    val lock = ReentrantReadWriteLock().readLock()
    synchronized(lock) {
        println("in lock")
    }
    // such Object code analogue will be generated by compiler
    // >>> function inlining
    lock.lock()
    try {
        println("in lock") // lambda code is inlined too.
        // NB there is no lambda in here anymore neither new object creation, so - no overhead
    } finally {
        lock.unlock()
    }
    // <<< function inlining

    // 2. ------------------------------------
    // but if variable of a function type id passed instead of lambda, than it can not be inlined
    // so for such code snippet
    val action = { println("in lock") }
    synchronized(lock,action)
    // this Object code analogue will be generated by compiler
    // >>> function inlining
    lock.lock()
    try {
        action() // function body is not inlined - just function call, because there is no lambda
    } finally {
        lock.unlock()
    }
    // <<< function inlining

}

// inlined function
inline fun <T> synchronized(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } finally {
        lock.unlock()
    }
}

//