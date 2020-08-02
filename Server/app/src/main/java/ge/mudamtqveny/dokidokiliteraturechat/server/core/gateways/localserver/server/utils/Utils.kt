package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.utils

import com.google.gson.Gson
import com.sun.net.httpserver.HttpExchange
import java.io.BufferedReader
import java.io.InputStreamReader

fun sendResponse(exchange: HttpExchange, response: String) {
    exchange.sendResponseHeaders(200, response.length.toLong())
    exchange.responseBody.apply {
        write(response.toByteArray())
        close()
    }
}

fun <T : Any> exchangeToObject(exchange: HttpExchange, classType: Class<T>): T {
    val isr = InputStreamReader(exchange.requestBody, "utf-8")
    val jsonString = BufferedReader(isr).use(BufferedReader::readText)
    return Gson().fromJson(jsonString, classType)
}