
package ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.*
import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.ChatGateway

interface ChatListingUseCase {
    fun fetchChatList(userIdEntity: UserIdEntity, completionHandler: (List<ChatPresentingEntity>) -> Unit)
}

interface ChatDeletingUseCase {
    fun deleteChat(chatDeleteEntity: ChatDeleteEntity)
}

interface ChatCreatingUseCase {
    fun createChat(chatInsertEntity: ChatInsertEntity, completionHandler: (ChatIdEntity) -> Unit)
}

interface RestfulChatUseCase: ChatListingUseCase, ChatDeletingUseCase, ChatCreatingUseCase

class ChatsUseCase(private val gateway: ChatGateway): RestfulChatUseCase {

    override fun fetchChatList(userIdEntity: UserIdEntity, completionHandler: (List<ChatPresentingEntity>) -> Unit) {
        gateway.fetchChatList(userIdEntity, completionHandler)
    }

    override fun deleteChat(chatDeleteEntity: ChatDeleteEntity) {
        gateway.deleteChat(chatDeleteEntity)
    }

    override fun createChat(chatInsertEntity: ChatInsertEntity, completionHandler: (ChatIdEntity) -> Unit) {
        gateway.createChat(chatInsertEntity, completionHandler)
    }
}
