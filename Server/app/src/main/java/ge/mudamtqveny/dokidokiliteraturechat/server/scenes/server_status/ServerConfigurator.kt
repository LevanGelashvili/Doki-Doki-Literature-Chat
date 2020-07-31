package ge.mudamtqveny.dokidokiliteraturechat.server.scenes.server_status

import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.LocalServerGateway
import ge.mudamtqveny.dokidokiliteraturechat.server.core.usecases.ServerTogglerUseCase

class ServerConfigurator(private val view: ServerView) {

    fun configure() {
        val gateway = LocalServerGateway()
        val useCase = ServerTogglerUseCase(gateway)
        val presenter = ServerPresenter(view, useCase)
        view.presenter = presenter
    }
}