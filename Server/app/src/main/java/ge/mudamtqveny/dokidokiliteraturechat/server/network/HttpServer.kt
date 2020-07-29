package ge.mudamtqveny.dokidokiliteraturechat.server.network

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress
import java.util.concurrent.Executors

class HttpServer {

    private val port = 5000
    private lateinit var httpServer: HttpServer

    fun start() {
        httpServer = HttpServer.create(InetSocketAddress(port), 0).apply {
            executor = Executors.newCachedThreadPool()
            createContext("/", rootHandler)
            start()
        }
    }

    fun stop() {
        httpServer.stop(0)
    }

    private val rootHandler = HttpHandler { exchange ->
        run {
            when (exchange.requestMethod) {
                "GET" -> sendResponse(exchange, "Welcomeeeee")
            }
        }
    }

    private fun sendResponse(exchange: HttpExchange, response: String) {
        exchange.sendResponseHeaders(200, response.length.toLong())
        exchange.responseBody.apply {
            write(response.toByteArray())
            close()
        }
    }

}