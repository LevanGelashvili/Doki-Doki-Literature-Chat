package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server

import com.sun.net.httpserver.HttpServer
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.handlers.ConnectHandler
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.handlers.VerifyHandler
import java.net.InetSocketAddress
import java.util.concurrent.Executors

class ServerFactory {

    fun create(): HttpServer {
        return HttpServer.create(InetSocketAddress(8080), 0).apply {
            executor = Executors.newCachedThreadPool()
            createContext("/connect", ConnectHandler())
            createContext("/verify", VerifyHandler())
        }
    }
}