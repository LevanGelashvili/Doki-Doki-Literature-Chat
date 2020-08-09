package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages

import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network.ServerGateway
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.MessagesUseCase

class MessagesConfigurator(private val view: MessagesView) {

    fun configure() {
        val parameters = view.requireArguments().get("parameters") as MessagesParameters
        val gateway = ServerGateway()
        val messageUseCase = MessagesUseCase(gateway)
        val router = MessagesRouter(view)
        val presenter = MessagesPresenter(view, parameters, router, messageUseCase, messageUseCase, messageUseCase)
        view.presenter = presenter
    }
}