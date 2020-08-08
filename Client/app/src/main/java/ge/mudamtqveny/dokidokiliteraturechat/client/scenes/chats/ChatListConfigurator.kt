
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats

import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network.ServerGateway
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.ChatsUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.UserUseCase

class ChatListConfigurator(private val view: ChatListView) {

    fun configure() {
        val parameters = view.requireArguments().get("parameters") as ChatListParameters
        val gateway = ServerGateway()
        val chatListUseCase = ChatsUseCase(gateway)
        val userListUseCase = UserUseCase(gateway)
        val router = ChatListRouter(view)
        val presenter = ChatListPresenter(view, parameters, chatListUseCase, userListUseCase, router)
        view.presenter = presenter
    }
}
