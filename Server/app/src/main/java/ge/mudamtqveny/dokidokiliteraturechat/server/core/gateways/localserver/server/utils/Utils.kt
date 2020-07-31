package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.utils

import com.sun.net.httpserver.HttpExchange

fun sendResponse(exchange: HttpExchange, response: String) {
    exchange.sendResponseHeaders(200, response.length.toLong())
    exchange.responseBody.apply {
        write(response.toByteArray())
        close()
    }
}