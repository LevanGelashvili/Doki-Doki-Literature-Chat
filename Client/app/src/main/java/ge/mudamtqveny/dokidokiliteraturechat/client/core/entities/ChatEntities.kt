
package ge.mudamtqveny.dokidokiliteraturechat.client.core.entities

data class ChatPresentingEntity (

    val chatId: Long,
    val friendAvatar: String, // Base64
    val friendNickname: String,
    val lastMessage: String,
    val lastMessageDate: Long

)
