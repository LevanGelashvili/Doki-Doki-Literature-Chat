package ge.mudamtqveny.dokidokiliteraturechat.server.core.usecases

import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.RunnableGateway

interface ServerTogglingUseCase {

    /**
     * Toggles Servers' State and
     * Returns Servers' State after toggling:
     * true  - server is up
     * false - server is down
     */
    fun toggleServer(): Boolean
}

class ServerTogglerUseCase (

    private val gateway: RunnableGateway

): ServerTogglingUseCase {

    override fun toggleServer(): Boolean {
        return if (gateway.isRunning) {
            gateway.halt()
            false
        } else {
            gateway.run()
            true
        }
    }
}