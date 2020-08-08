package ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.ChatIdEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.MessageEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.MessagePresentingEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UnseenMessageEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.MessageGateway

interface MessageSendingUseCase {
    fun sendMessage(messageEntity: MessageEntity)
}

interface MessageListingUseCase {
    fun getMessages(chatIdEntity: ChatIdEntity, completionHandler: (List<MessagePresentingEntity>) -> Unit)
}

interface UnseenMessageListingUseCase {
    fun getUnseenMessages(unseenMessage: UnseenMessageEntity, completionHandler: (List<MessagePresentingEntity>) -> Unit)
}

class MessagesUseCase(private val gateway: MessageGateway) : MessageSendingUseCase, MessageListingUseCase, UnseenMessageListingUseCase {

    override fun sendMessage(messageEntity: MessageEntity) {
        gateway.sendMessage(messageEntity)
    }

    override fun getMessages(chatIdEntity: ChatIdEntity, completionHandler: (List<MessagePresentingEntity>) -> Unit) {
        gateway.getMessageList(chatIdEntity, completionHandler)
    }

    override fun getUnseenMessages(unseenMessage: UnseenMessageEntity, completionHandler: (List<MessagePresentingEntity>) -> Unit) {
        gateway.getUnseenMessageList(unseenMessage, completionHandler)
    }
}