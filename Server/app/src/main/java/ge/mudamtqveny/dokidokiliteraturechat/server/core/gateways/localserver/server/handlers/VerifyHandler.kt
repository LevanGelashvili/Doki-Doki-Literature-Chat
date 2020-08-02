package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.handlers

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database.LocalPersistence
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.IntroduceUserViewModel
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.utils.exchangeToObject


class VerifyHandler: HttpHandler {

    override fun handle(exchange: HttpExchange) {
        when (exchange.requestMethod) {
            "POST" -> {
                val userModel = exchangeToObject(exchange, IntroduceUserViewModel::class.java)
                val db = LocalPersistence.getInstance()

            }
        }
    }
}