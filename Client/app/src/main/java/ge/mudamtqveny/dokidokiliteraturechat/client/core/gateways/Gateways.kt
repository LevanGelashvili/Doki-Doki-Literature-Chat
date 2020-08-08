
package ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.*

interface ConnectionGateway {
    fun connect(completionHandler: (Boolean) -> (Unit))
}

interface LoginUserGateway {
    fun verify(loginEntity: UserLoginEntity, completionHandler: (UserIdEntity) -> (Unit))
}

interface ChatGateway {
    fun fetchChatList(userIdEntity: UserIdEntity, completionHandler: (List<ChatPresentingEntity>) -> Unit)
    fun deleteChat(chatDeleteEntity: ChatDeleteEntity)
    fun createChat(chatInsertEntity: ChatInsertEntity, completionHandler: (ChatIdEntity) -> Unit)
}

interface MessageGateway {
    fun sendMessage(messageEntity: MessageEntity)
    fun getMessageList(chatIdEntity: ChatIdEntity, completionHandler: (List<MessagePresentingEntity>) -> Unit)
}