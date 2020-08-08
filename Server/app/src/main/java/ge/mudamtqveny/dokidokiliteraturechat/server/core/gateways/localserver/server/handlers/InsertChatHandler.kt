package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.handlers

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database.LocalRoomDatabase
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.ChatInsertEntity
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.utils.exchangeToObject
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.utils.objectToJSON
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.utils.sendResponse

class InsertChatHandler: HttpHandler {

    override fun handle(exchange: HttpExchange) {
        when (exchange.requestMethod) {
            "POST" -> {
                val chatInsertEntity = exchangeToObject(exchange, ChatInsertEntity::class.java)
                LocalRoomDatabase.getInstance().insertChat(chatInsertEntity) {
                    sendResponse(exchange, objectToJSON(it))
                }
            }
        }
    }
}