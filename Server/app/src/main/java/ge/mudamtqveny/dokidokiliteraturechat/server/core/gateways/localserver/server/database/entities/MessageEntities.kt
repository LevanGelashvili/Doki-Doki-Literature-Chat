
package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.MessageEntity

const val MESSAGE_TABLE = "messages"

@Entity (
    tableName = MESSAGE_TABLE,
    foreignKeys = [
        ForeignKey(entity = UserDataEntity::class, parentColumns = ["user_id"], childColumns = ["user_id_from"]),
        ForeignKey(entity = UserDataEntity::class, parentColumns = ["user_id"], childColumns = ["user_id_to"]),
        ForeignKey(entity = ChatDataEntity::class, parentColumns = ["chat_id"], childColumns = ["chat_id"], onDelete = ForeignKey.CASCADE)
    ]
)
data class MessageDataEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "message_id")
    var messageID: Long = 0,

    @ColumnInfo(name = "user_id_from", index = true)
    var userIDFrom: Long,

    @ColumnInfo(name = "user_id_to", index = true)
    var userIDTo: Long,

    @ColumnInfo(name = "chat_id", index = true)
    var chatID: Long,

    @ColumnInfo(name = "text")
    var text: String,

    @ColumnInfo(name = "date")
    var date: Long
) {

    constructor(message: MessageEntity): this (
        userIDFrom = message.userIdFrom,
        userIDTo = message.userIdTo,
        chatID = message.chatId,
        text = message.text,
        date = message.date
    )
}

data class MessagePresentingDataEntity (

    @ColumnInfo(name = "user_id_from")
    val userIdFrom: Long,

    @ColumnInfo(name = "user_id_to")
    val userIdTo: Long,

    @ColumnInfo(name = "text")
    val text: String,

    @ColumnInfo(name = "date")
    val date: Long
)
