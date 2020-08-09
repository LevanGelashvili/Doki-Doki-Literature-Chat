package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.handlers

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database.LocalRoomDatabase
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.ChatDeleteEntity
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.utils.exchangeToObject
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.utils.sendResponse

class DeleteChatHandler: HttpHandler {

    override fun handle(exchange: HttpExchange) {
        when (exchange.requestMethod) {
            "POST" -> {
                val chatDeleteEntity = exchangeToObject(exchange, ChatDeleteEntity::class.java)
                LocalRoomDatabase.getInstance().deleteChat(chatDeleteEntity)
                sendResponse(exchange, "")
            }
        }
    }
}