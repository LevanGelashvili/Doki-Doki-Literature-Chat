package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.handlers

import android.util.Log
import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import java.io.BufferedReader
import java.io.InputStreamReader


class VerifyHandler: HttpHandler {

    override fun handle(exchange: HttpExchange) {
        when (exchange.requestMethod) {
            "POST" -> {
                val isr = InputStreamReader(exchange.requestBody, "utf-8")
                val br = BufferedReader(isr)
                val json = br.toString()
                Log.d("Here", json)
            }
        }
    }
}