
package ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.*
import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.ChatGateway

interface ChatListingUseCase {

    /**
     * Fetches Chat List of User specified by userIdEntity
     */
    fun fetchChatList(userIdEntity: UserIdEntity, completionHandler: (List<ChatPresentingEntity>) -> Unit)

    fun deleteChat(chatDeleteEntity: ChatDeleteEntity)

    fun createChat(chatInsertEntity: ChatInsertEntity, completionHandler: (ChatIdEntity) -> Unit)
}

class ChatsUseCase(private val gateway: ChatGateway): ChatListingUseCase {

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
