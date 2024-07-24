import kotlinx.coroutines.*
//import kotlin.system.measureTimeMillis
/**
 * in a coroutine, when the code suspends(pauses), the coroutines shares the event loop allowing other code to be run
 * delay is an example of a coroutine suspend function. Other code can run once in delay mode
 */

fun main() {
    // runBlocking is synchronous; waits for all the code in its body to finish before returning to the main function
    // that means for "Sunny" to be printed, it has to wait for the 1 seconds delay & so does the
    runBlocking {
        println("Weather forecast:")
        // as printForecast is running so is printTemperature i.e. at the same time (concurrently)
        // in real time they are both being delayed in the same exact second
        println(getWeatherReport())
        println("Last print message in runBlocking")
    }
}

/**
* a suspend function is a function that can pause and resume its functionality from where it last left off
* suspend functions like delay should be called in other suspend functions that offer suspend functionality or in coroutines
*/
suspend fun getForecast(): String {
    delay(1000)
    return "Cold"
}

suspend fun getTemperature(): String {
    delay(500)
    throw AssertionError("Temperature is invalid") // simulating an error fetching temperature data
    return "15\u00b0C"
}

/**
 * coroutineScope groups together all the coroutines launched in this scope
 * coroutineScope even though it's internally running code concurrently, won't return until all its work (code) is completed
 *
 * getWeatherReport uses parallel decomposition where it breaks down the task of getting the weather data into chunks...
 * ... then combining the result of these tasks at the end into a final result
 */
suspend fun getWeatherReport() = coroutineScope {
    // foreCast & temperature are Deferred objects (same as a JavaScript Promise)
    // ... of type string since getForecast() & getTemperature() return strings
    val foreCast = async { getForecast() }
    val temperature = async {
        try {
            getTemperature()
        } catch (e: AssertionError) {
            println("Caught exception $e")
            "{ No temperature found }"
        }
    }

    // accessing the values/result of the async coroutines using await() on the Deferred objects
    "${foreCast.await()} ${temperature.await()}" // returns => Cold 15°C
}
