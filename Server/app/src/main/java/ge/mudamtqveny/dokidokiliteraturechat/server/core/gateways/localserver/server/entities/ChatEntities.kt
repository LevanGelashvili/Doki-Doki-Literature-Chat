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
    val chatId: Long,
    val userIdFrom: Long,
    val userIdTo: Long,
    val text: String,
    val date: Long
)

data class MessagePresentingEntity (

    @ColumnInfo(name = "user_id_from")
    val userIdFrom: Long,

    @ColumnInfo(name = "user_id_to")
    val userIdTo: Long,

    @ColumnInfo(name = "text")
    val text: String,

    @ColumnInfo(name = "date")
    val date: Long
)

data class ChatInsertEntity (
    val initUserId: Long,
    val otherUserId: Long
)

data class ChatDeleteEntity (
    val chatId: Long,
    val deleterUserId: Long
)

data class ChatIdEntity (
    val id: Long
)