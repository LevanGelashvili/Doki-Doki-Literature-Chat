package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localpersistence
import androidx.room.*
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.Gateway

class LocalPersistenceGateway: Gateway {

    companion object {
        val database = Room.databaseBuilder(ge.mudamtqveny.dokidokiliteraturechat.server.app.Application.context!!, ChatDatabase::class.java, "database").build()
    }

}

/* Framework specific part */

@Entity(tableName = "users")
data class UserDataEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var id: Long,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "what_i_do")
    var job: String,

    @ColumnInfo(name = "picture")
    var picture: String
)  // TODO: CONSTRUCTOR





@Entity(
    tableName = "chats",
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
    tableName = "messages",
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



@Database(entities = [UserDataEntity::class, ChatDataEntity::class, MessageDataEntity::class], version = 1)
abstract class ChatDatabase: RoomDatabase() {
    //abstract fun getNoteDao(): NoteDAO
    //abstract fun getTaskDao(): TaskDAO
}
