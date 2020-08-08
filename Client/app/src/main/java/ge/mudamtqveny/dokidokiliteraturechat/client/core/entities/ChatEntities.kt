
package ge.mudamtqveny.dokidokiliteraturechat.client.core.entities

data class ChatPresentingEntity (
    val chatId: Long,
    val lastMessage: String,
    val lastMessageDate: Long,
    val friendUserEntity: UserEntity
)

data class ChatDeleteEntity (
    val chatId: Long,
    val deleterUserId: Long
)

data class ChatIdEntity (
    val id: Long
)

data class ChatInsertEntity (
    val initUserId: Long,
    val otherUserId: Long
)

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
    val idToFetchFrom: Long,
    val chatId: Long
)