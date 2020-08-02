package ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases

import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.ConnectionGateway

interface ConnectingUseCase {

    /** Returns true if successful, else false */
    fun connect(completionHandler: (Boolean) -> (Unit))
}

class ConnectionUseCase(private val gateway: ConnectionGateway): ConnectingUseCase {

    override fun connect(completionHandler: (Boolean) -> (Unit)) {
        gateway.connect(completionHandler)
    }
}
