
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats

import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network.ServerGateway
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.ChatListUseCase

class ChatListConfigurator(private val view: ChatListView) {

    fun configure() {
        val gateway = ServerGateway()
        val chatListUseCase = ChatListUseCase(gateway)
        val router = ChatListRouter(view)
        val presenter = ChatListPresenter(view, chatListUseCase, router)
        view.presenter = presenter
    }
}
