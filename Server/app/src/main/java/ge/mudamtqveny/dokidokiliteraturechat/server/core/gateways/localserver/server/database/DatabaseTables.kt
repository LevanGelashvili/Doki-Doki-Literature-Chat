package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database

import androidx.room.*
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.MessageEntity
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserLoginEntity

@Entity(tableName = USER_TABLE)
data class UserDataEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var id: Long = 0,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "what_i_do")
    var job: String,

    @ColumnInfo(name = "picture")
    var picture: String
)  {

    /**
     * Constructor for INSERT
     */
    constructor(userLoginEntity: UserLoginEntity): this (
        name = userLoginEntity.name,
        job = userLoginEntity.job,
        picture = userLoginEntity.picture
    )

    /**
     * Constructor for UPDATE
     */
    constructor(id: Long, userLoginEntity: UserLoginEntity): this(userLoginEntity) {
        this.id = id
    }
}





@Entity(
    tableName = CHAT_TABLE,
    foreignKeys = [
        ForeignKey(entity = UserDataEntity::class, parentColumns = ["user_id"], childColumns = ["user_id"])
    ]
)
data class ChatDataEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0,

    @ColumnInfo(name = "chat_id")
    var chatId: Long,

    @ColumnInfo(name = "user_id", index = true)
    var userID: Long
) {

    /**
     *  Constructor for INSERT
     */
    constructor(passedId: Long, passedChatId: Long): this (
        userID = passedId,
        chatId = passedChatId
    )
}



@Entity(
    tableName = MESSAGE_TABLE,
    foreignKeys = [
        ForeignKey(entity = UserDataEntity::class, parentColumns = ["user_id"], childColumns = ["user_id_from"]),
        ForeignKey(entity = UserDataEntity::class, parentColumns = ["user_id"], childColumns = ["user_id_to"])
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