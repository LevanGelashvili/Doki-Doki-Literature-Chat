package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserLoginEntity

@Entity(tableName = USER_TABLE)
data class UserDataEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var id: Long?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "what_i_do")
    var job: String,

    @ColumnInfo(name = "picture")
    var picture: String
)  {

    constructor(id: Long?, userLoginEntity: UserLoginEntity): this (
        id = id,
        name = userLoginEntity.name,
        job = userLoginEntity.job,
        picture = userLoginEntity.picture
    )
}





@Entity(
    tableName = CHAT_TABLE,
    foreignKeys = [
        ForeignKey(entity = UserDataEntity::class, parentColumns = ["user_id"], childColumns = ["user_id"])
    ]
)
data class ChatDataEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "chat_id")
    var id: Long,

    @ColumnInfo(name = "user_id")
    var userID: Long
) // TODO: CONSTRUCTOR



@Entity(
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
    var messageID: Long,

    @ColumnInfo(name = "user_id_from")
    var userIDFrom: Long,

    @ColumnInfo(name = "user_id_to")
    var userIDTo: Long,

    @ColumnInfo(name = "chat_id")
    var chatID: Long,

    @ColumnInfo(name = "text")
    var text: String,

    @ColumnInfo(name = "date")
    var date: Long
) // TODO: CONSTRUCTOR