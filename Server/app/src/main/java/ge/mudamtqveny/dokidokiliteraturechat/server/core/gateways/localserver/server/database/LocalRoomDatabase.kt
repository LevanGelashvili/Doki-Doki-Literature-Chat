package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database

import androidx.room.*
import ge.mudamtqveny.dokidokiliteraturechat.server.app.Application
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserEntity
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserLoginEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalRoomDatabase: DatabaseService {

    companion object {
        private val database = Room.databaseBuilder(Application.context!!, ChatDatabase::class.java, "database").build()
        private val instance = LocalRoomDatabase()

        fun getInstance(): DatabaseService {
            return instance
        }
    }

    override fun verifyUser(loginEntity: UserLoginEntity): UserEntity {

        var resultUser: UserEntity? = null

        /*GlobalScope.launch(Dispatchers.IO) {
            val userData = database.getUserDAO().userGivenNickname(loginEntity.nickname)
            if (userData == null) {
                val id = database.getUserDAO().insertUser(UserDataEntity(loginEntity))
            } else {

                resultUser = UserEntity(userData)
            }
        }*/

        return resultUser!!
    }

}

/* Framework specific part */

@Entity(tableName = USER_TABLE)
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
)  {

    constructor(passedId: Long, userLoginEntity: UserLoginEntity): this (
        id = passedId,
        name = userLoginEntity.nickname,
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



@Database(entities = [UserDataEntity::class, ChatDataEntity::class, MessageDataEntity::class], version = 1)
abstract class ChatDatabase: RoomDatabase() {
    abstract fun getUserDAO(): UserDAO
}
