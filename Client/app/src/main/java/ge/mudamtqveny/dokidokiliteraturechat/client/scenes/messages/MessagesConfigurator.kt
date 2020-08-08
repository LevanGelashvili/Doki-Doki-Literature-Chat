package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages

import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network.ServerGateway
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.MessagesUseCase

class MessagesConfigurator(private val view: MessagesView) {

    fun configure() {
        val gateway = ServerGateway()
        val messageUseCase = MessagesUseCase(gateway)
        val router = MessagesRouter(view)
        val presenter = MessagesPresenter(router)
        view.presenter = presenter
    }
}