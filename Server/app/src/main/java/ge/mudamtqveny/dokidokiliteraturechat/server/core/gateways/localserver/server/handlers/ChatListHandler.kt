package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.handlers

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserIdEntity
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.utils.exchangeToObject
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.utils.sendResponse

class ChatListHandler: HttpHandler {

    override fun handle(exchange: HttpExchange) {
        when (exchange.requestMethod) {
            "GET" -> {
                val userIdEntity = exchangeToObject(exchange, UserIdEntity::class.java)
                sendResponse(exchange, "")
            }
        }
    }
}