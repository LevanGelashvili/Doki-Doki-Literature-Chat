package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities

import androidx.room.ColumnInfo

data class ChatPresentingEntity (

    @ColumnInfo(name = "chat_id")
    val chatId: Long,

    @ColumnInfo(name = "picture")
    val friendAvatar: String, // Base64

    @ColumnInfo(name = "name")
    val friendNickname: String,

    @ColumnInfo(name = "text")
    val lastMessage: String,

    @ColumnInfo(name = "date")
    val lastMessageDate: Long

)

data class MessageEntity (

    var id: Long?
    // TODO
)