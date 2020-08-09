
package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities

/** Chat Part */

data class ChatPresentingEntity (
    val chatId: Long,
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
    val chatId: Long,
    val deleterUserId: Long
)

/** Message Part */

data class MessageEntity (
    val chatId: Long,
    val userIdFrom: Long,
    val userIdTo: Long,
    val text: String,
    val date: Long
)

data class MessagePresentingEntity (
    val userIdFrom: Long,
    val userIdTo: Long,
    val text: String,
    val date: Long
)

data class UnseenMessageEntity (
    val lastDate: Long,
    val chatId: Long
)
