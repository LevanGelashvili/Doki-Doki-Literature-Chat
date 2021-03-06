
package ge.mudamtqveny.dokidokiliteraturechat.client.core.entities

import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.MessagesParameters
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.components.viewmodels.MessageViewModel

/** Chat Part */

data class ChatPresentingEntity (
    var chatId: Long,
    val lastMessage: String,
    val lastMessageDate: Long,
    val friendUserEntity: UserEntity
)

data class ChatIdEntity (
    val id: Long
)

data class ChatInsertEntity (
    val initUserId: Long,
    val otherUserId: Long
)

data class ChatDeleteEntity (
    val chatId: Long
)

/** Message Part */

data class MessageEntity (
    val chatId: Long,
    val userIdFrom: Long,
    val userIdTo: Long,
    val text: String,
    val date: Long
) {

    constructor(params: MessagesParameters, viewModel: MessageViewModel): this (
        chatId = params.chatId,
        userIdFrom = params.initUserId,
        userIdTo = params.otherUserId,
        text = viewModel.text,
        date = viewModel.date
    )
}

data class MessagePresentingEntity (
    val userIdFrom: Long,
    val userIdTo: Long,
    val text: String,
    val date: Long
) {
    constructor(messageEntity: MessageEntity) : this(
        userIdFrom = messageEntity.userIdFrom,
        userIdTo = messageEntity.userIdTo,
        text = messageEntity.text,
        date = messageEntity.date
    )

    fun toMessageViewModel(): MessageViewModel {
        return MessageViewModel(
            text,
            date
        )
    }
}

data class UnseenMessageEntity (
    val lastDate: Long,
    val chatId: Long
)
