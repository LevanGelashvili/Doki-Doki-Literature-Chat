package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database

import androidx.room.*
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.MessagePresentingEntity

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

    @Query("SELECT * FROM $USER_TABLE WHERE name LIKE '%' || :substring  || '%'")
    suspend fun getUsersHavingInName(substring: String): List<UserDataEntity>

    /** For testing */
    @Query("select * from $USER_TABLE")
    suspend fun getAllUsers(): List<UserDataEntity>
}

@Dao
interface ChatDAO {

    @Query (
        "" +
        "SELECT m.chat_id AS chat_id,\n" +
        "       f.user_id AS user_id,\n" +
        "       f.name AS name,\n" +
        "       f.what_i_do AS what_i_do,\n" +
        "       f.picture AS picture,\n" +
        "       m.text AS text,\n" +
        "       m.date AS date\n" +
        "  FROM $MESSAGE_TABLE m\n" +
        "  JOIN (SELECT chat_id,\n" +
        "               MAX(date) max_date\n" +
        "          FROM $MESSAGE_TABLE\n" +
        "         GROUP BY chat_id) mm\n" +
        "    ON m.chat_id = mm.chat_id AND\n" +
        "       m.date = mm.max_date\n" +
        "  LEFT JOIN $USER_TABLE f\n" +
        "    ON m.user_id_to = f.user_id\n" +
        " WHERE m.user_id_from = :userId"
    )
    suspend fun getUserChats(userId: Long): List<ChatPresentingDataEntity>

    @Query("select user_id_from, user_id_to, text, date from $MESSAGE_TABLE where chat_id == :chatId")
    suspend fun getMessagesFromChat(chatId: Long): List<MessagePresentingEntity>

    @Query("select user_id_from, user_id_to, text, date from $MESSAGE_TABLE where chat_id == :chatId and message_id > :idToFetchFrom")
    suspend fun getUnseenMessages(idToFetchFrom: Long, chatId: Long): List<MessagePresentingEntity>

    @Insert(entity = ChatDataEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertChat(chat: ChatDataEntity): Long

    @Query("delete from $CHAT_TABLE where chat_id == :chatId and user_id == :userId")
    suspend fun deleteChat(chatId: Long, userId: Long)

    @Query("select coalesce (max(chat_id), 0) from $CHAT_TABLE")
    suspend fun getMaxChatId(): Long

    @Query("select count(*) from $CHAT_TABLE where chat_id == :chatId")
    suspend fun numberOfChatEntriesLeft(chatId: Long): Int

    @Query("delete from $MESSAGE_TABLE where chat_id == :chatId")
    suspend fun deleteChatlessMessages(chatId: Long)

    @Insert(entity = MessageDataEntity::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMessage(message: MessageDataEntity): Long
}