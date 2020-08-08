
package ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.ChatPresentingEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserIdEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.ChatListGateway

interface ChatListingUseCase {

    /**
     * Fetches Chat List of User specified by userIdEntity
     */
    fun fetchChatList(userIdEntity: UserIdEntity, completionHandler: (List<ChatPresentingEntity>) -> Unit)
}

class ChatListUseCase(private val gateway: ChatListGateway): ChatListingUseCase {

    override fun fetchChatList(userIdEntity: UserIdEntity, completionHandler: (List<ChatPresentingEntity>) -> Unit) {
        gateway.fetchChatList(userIdEntity, completionHandler)
    }
}
