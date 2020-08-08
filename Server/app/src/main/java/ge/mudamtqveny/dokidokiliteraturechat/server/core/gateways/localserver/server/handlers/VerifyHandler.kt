package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.handlers

import com.sun.net.httpserver.HttpExchange
import com.sun.net.httpserver.HttpHandler
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database.LocalRoomDatabase
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserLoginEntity
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.utils.exchangeToObject
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.utils.objectToJSON
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.utils.sendResponse

class VerifyHandler: HttpHandler {

    override fun handle(exchange: HttpExchange) {
        when (exchange.requestMethod) {
            "POST" -> {
                val userLoginEntity = exchangeToObject(exchange, UserLoginEntity::class.java)
                LocalRoomDatabase.getInstance().verifyUser(userLoginEntity) {
                    sendResponse(exchange, objectToJSON(it))
                }
            }
        }
    }
}