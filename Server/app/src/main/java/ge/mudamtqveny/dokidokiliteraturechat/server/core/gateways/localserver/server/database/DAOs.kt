package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

const val USER_TABLE = "users"
const val CHAT_TABLE = "chats"
const val MESSAGE_TABLE = "messages"

@Dao
interface UserDAO {

    @Insert(entity = UserDataEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserDataEntity): Long

    //TODO: update user

    @Query("select * from $USER_TABLE where name == :nickname")
    suspend fun userGivenNickname(nickname: String): UserDataEntity?

}