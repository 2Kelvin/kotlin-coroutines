import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun main() {
    // measuring the time it takes to run this code
    val codeTime = measureTimeMillis {
        // runBlocking is synchronous; waits for all the code in its body to finish before returning to the main function
        // that means for "Sunny" to be printed, it has to wait for the 1 seconds delay & so does the
        runBlocking {
            println("Weather forecast:")
            printForecast()
            printTemperature()
        }
    }

    //printing the time taken to run the code in seconds
    println("Code execution time is: ${codeTime / 1000.0} seconds")
}

// a suspend function is a function that can pause and resume its functionality from where it last left off
// suspend functions like delay should be called in other suspend functions that offer suspend functionality or in coroutines
suspend fun printForecast() {
    delay(1000)
    println("Cold")
}

suspend fun printTemperature() {
    delay(1000)
    println("15\u00b0C")
}

/**
 * in a coroutine, when the code suspends(pauses), the coroutines shares the event loop allowing other code to be run
 * delay is an example of a coroutine suspend function. Other code can run once in delay mode
 */