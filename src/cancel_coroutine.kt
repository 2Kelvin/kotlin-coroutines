import kotlinx.coroutines.*

fun main() {
    runBlocking {
        println("Weather forecast")
        println(getWeatherReport2())
        println("Have a good day!")
    }
}

suspend fun getWeatherReport2() = coroutineScope {
    val forecast = async { getForecast2() }
    val temperature = async { getTemperature2() }

    delay(200)
    // cancelling the getTemperature async coroutine
    temperature.cancel()

    // returning only the forecast string result
    "${forecast.await()}"
}

suspend fun getForecast2(): String {
    delay(1000)
    return "Sunny"
}

suspend fun getTemperature2(): String {
    delay(1000)
    return "30\u00b0C"
}
