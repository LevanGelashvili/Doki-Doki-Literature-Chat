
package ge.mudamtqveny.dokidokiliteraturechat.client.core.entities

data class ChatPresentingEntity (
    val chatId: Long,
    val lastMessage: String,
    val lastMessageDate: Long,
    val friendUserEntity: UserEntity
)
