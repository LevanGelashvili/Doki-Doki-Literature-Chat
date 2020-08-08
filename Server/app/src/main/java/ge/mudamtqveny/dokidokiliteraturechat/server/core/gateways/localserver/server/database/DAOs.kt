package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database

import androidx.room.*
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.ChatPresentingEntity

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

    @Query (
        "" +
        "SELECT m.chat_id AS chat_id,\n" +
        "       f.picture AS picture,\n" +
        "       f.name AS name,\n" +
        "       m.text AS text,\n" +
        "       m.date AS date\n" +
        "  FROM $MESSAGE_TABLE m\n" +
        "  LEFT JOIN $USER_TABLE f ON (m.user_id_to = f.user_id)\n"+
        " WHERE m.user_id_from = :userId AND" +
        "       (m.chat_id, m.date) IN (SELECT chat_id,\n" +
        "                                      MAX(date)\n" +
        "                                 FROM $MESSAGE_TABLE\n" +
        "                                GROUP BY chat_id)"
    )
    suspend fun getUserChats(userId: Long): List<ChatPresentingEntity>

    @Insert(entity = ChatDataEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertChat(chat: ChatDataEntity): Long

    @Query("delete from $CHAT_TABLE where id == :chatId")
    suspend fun deleteChat(chatId: Long)

}