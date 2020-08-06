package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database

import androidx.room.*

const val USER_TABLE = "users"
const val CHAT_TABLE = "chats"
const val MESSAGE_TABLE = "messages"

@Dao
interface UserDAO {

    @Insert(entity = UserDataEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserDataEntity): Long

    @Update
    suspend fun updateUser(user: UserDataEntity)

    @Query("select * from $USER_TABLE where name == :nickname")
    suspend fun userGivenNickname(nickname: String): UserDataEntity?

    /** For testing */
    @Query("select * from $USER_TABLE")
    suspend fun getAllUsers(): List<UserDataEntity>
}

@Dao
interface ChatDAO {

    @Query("select * from $CHAT_TABLE where user_id == :userId")
    suspend fun getUserChats(userId: Long): List<ChatDataEntity>
}