package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages

import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network.ServerGateway

class MessagesConfigurator(private val view: MessagesView) {

    fun configure() {
        val router = MessagesRouter(view)
        val gateway = ServerGateway() // TODO: Usecases
        val presenter = MessagesPresenter(router)
        view.presenter = presenter
    }
}