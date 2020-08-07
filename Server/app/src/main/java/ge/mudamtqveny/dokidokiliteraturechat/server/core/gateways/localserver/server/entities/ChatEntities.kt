package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities

data class ChatPresentingEntity (

    val chatID: Long,
    val friendImage: String, // Base64
    val friendNickname: String,
    val lastMessage: String,
    val lastMessageDate: Long

)

data class MessageEntity (

    var id: Long?
    // TODO
)