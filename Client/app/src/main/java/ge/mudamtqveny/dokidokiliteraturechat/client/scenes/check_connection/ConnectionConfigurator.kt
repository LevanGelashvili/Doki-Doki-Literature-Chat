package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.check_connection

import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network.ServerGateway
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.ConnectionUseCase

class ConnectionConfigurator(private val view: ConnectionView) {

    fun configure() {
        val router = ConnectionRouter(view)
        val gateway = ServerGateway()
        val connectionUseCase = ConnectionUseCase(gateway)
        val presenter = ConnectionPresenter(router, connectionUseCase)

        view.presenter = presenter
    }
}