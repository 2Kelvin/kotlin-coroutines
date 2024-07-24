import kotlinx.coroutines.*

/**
 * 3 built in coroutine dispatchers:
 * Dispatchers.Main - main thread
 * Dispatchers.IO - network and disks/files
 * Dispatchers.Default - async & launch -> for intensive work
 */

fun main() {
    runBlocking {
        println("${Thread.currentThread().name} thread - runBlocking function")

        launch {
            println("${Thread.currentThread().name} thread - launch function")

            withContext(Dispatchers.Default) { // using the default dispatcher thread instead of the main thread
                println("${Thread.currentThread().name} thread - withContext function")
                delay(1000)
                println("10 results found.")
            }

            println("${Thread.currentThread().name} thread - end of launch function")
        }
        println("Loading...")
    }
}