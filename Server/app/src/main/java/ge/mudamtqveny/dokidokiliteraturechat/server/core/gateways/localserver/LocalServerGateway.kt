package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver

import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.RunnableGateway
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.ServerFactory

class LocalServerGateway: RunnableGateway {

    companion object {
        private var runs = false
        private val server = ServerFactory().create()
    }

    override val isRunning: Boolean
        get() = runs

    override fun run() {
        runs = true
        server.start()
    }

    override fun halt() {
        runs = false
        server.stop(0)
    }
}