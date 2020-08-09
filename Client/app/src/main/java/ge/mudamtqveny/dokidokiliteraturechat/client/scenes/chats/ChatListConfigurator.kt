package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats

import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network.ServerGateway
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.ChatsUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.UserUseCase

class ChatListConfigurator(private val view: ChatListView) {

    fun configure() {
        val parameters = view.requireArguments().get("parameters") as ChatListParameters
        val gateway = ServerGateway()
        val chatUseCase = ChatsUseCase(gateway)
        val userUseCase = UserUseCase(gateway)
        val router = ChatListRouter(view)
        val presenter = ChatListPresenter(view, parameters, chatUseCase, userUseCase, router)
        view.presenter = presenter
    }
}
