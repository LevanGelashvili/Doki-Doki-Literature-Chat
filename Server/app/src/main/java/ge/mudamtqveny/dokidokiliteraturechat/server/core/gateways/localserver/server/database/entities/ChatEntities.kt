
package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

const val CHAT_TABLE = "chats"

@Entity (
    tableName = CHAT_TABLE,
    foreignKeys = [
        ForeignKey(entity = UserDataEntity::class, parentColumns = ["user_id"], childColumns = ["user_id"])
    ]
)
data class ChatDataEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "chat_id")
    var chatId: Long = 0,

    @ColumnInfo(name = "user_id", index = true)
    var userID: Long
) {
    /**
     *  Constructor for INSERT
     */
    constructor(passedId: Long): this (
        userID = passedId
    )
}

data class ChatPresentingDataEntity (

    @ColumnInfo(name = "chat_id")
    val chatId: Long,

    @ColumnInfo(name = "user_id")
    val friendId: Long,

    @ColumnInfo(name = "name")
    val friendName: String,

    @ColumnInfo(name = "what_i_do")
    val friendJob: String,

    @ColumnInfo(name = "picture")
    val friendAvatar: String,

    @ColumnInfo(name = "text")
    val lastMessage: String,

    @ColumnInfo(name = "date")
    val lastMessageDate: Long
)
