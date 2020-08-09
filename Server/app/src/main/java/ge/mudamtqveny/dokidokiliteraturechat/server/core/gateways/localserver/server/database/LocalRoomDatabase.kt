
package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database

import android.util.Log
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database.daos.*
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database.entities.*
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.*
import ge.mudamtqveny.dokidokiliteraturechat.server.scenes.server_status.ServerView
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalRoomDatabase: DatabaseService {

    companion object {
        private val database = Room.databaseBuilder(ServerView.context, ChatDatabase::class.java, "database").build()
        private val instance = LocalRoomDatabase()

        fun getInstance(): DatabaseService {
            return instance
        }
    }

    override fun verifyUser(loginEntity: UserLoginEntity, completionHandler: (UserIdEntity) -> (Unit)) {

        GlobalScope.launch(Dispatchers.IO) {

            val dao = database.getUserDAO()
            val userData = dao.userGivenNickname(loginEntity.name)

            val userIdEntity = if (userData == null) {
                val id = dao.insertUser(UserDataEntity(loginEntity))
                UserIdEntity(id)
            } else {
                dao.updateUser(UserDataEntity(userData.id, loginEntity))
                UserIdEntity(userData.id)
            }

            completionHandler(userIdEntity)
        }
    }

    override fun fetchChatList(userIdEntity: UserIdEntity, completionHandler: (List<ChatPresentingEntity>) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            val dao = database.getChatDAO()
            val chatListDataEntities = dao.getUserChats(userIdEntity.id)
            val chatListEntities = chatListDataEntities.map {
                ChatPresentingEntity (
                    it.chatId,
                    it.lastMessage,
                    it.lastMessageDate,
                    UserEntity (
                        it.friendId,
                        it.friendName,
                        it.friendJob,
                        it.friendAvatar
                    )
                )
            }
            completionHandler(chatListEntities)
        }
    }

    override fun deleteChat(chatDeleteEntity: ChatDeleteEntity) {
        GlobalScope.launch(Dispatchers.IO) {

            val dao = database.getChatDAO()
            dao.deleteChat(chatDeleteEntity.chatId, chatDeleteEntity.deleterUserId)

            if (dao.numberOfChatEntriesLeft(chatDeleteEntity.chatId) == 0) {
                dao.deleteChatlessMessages(chatDeleteEntity.chatId)
            }
        }
    }

    override fun createChat(chatInsertEntity: ChatInsertEntity, completionHandler: (ChatIdEntity) -> Unit) {

        GlobalScope.launch(Dispatchers.IO) {

            val dao = database.getChatDAO()
            val newChatId = 1 + dao.getMaxChatId()
            val initChatData = ChatDataEntity(chatInsertEntity.initUserId, newChatId)
            val otherChatData = ChatDataEntity(chatInsertEntity.otherUserId, newChatId)

            dao.insertChat(initChatData)
            dao.insertChat(otherChatData)

            insertMessage(placeHolderMessage(newChatId, chatInsertEntity.initUserId, chatInsertEntity.otherUserId))

            completionHandler(ChatIdEntity(newChatId))
        }
    }

    override fun insertMessage(messageEntity: MessageEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            database.getChatDAO().insertMessage(MessageDataEntity(messageEntity))
        }
    }

    override fun fetchMessageList(chatIdEntity: ChatIdEntity, completionHandler: (List<MessagePresentingEntity>) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            val chatDataMessages = database.getChatDAO().getMessagesFromChat(chatIdEntity.id)
            val chatMessages = chatDataMessages.map {
                MessagePresentingEntity(it.userIdFrom, it.userIdTo, it.text, it.date)
            }
            completionHandler(chatMessages)
        }
    }

    override fun fetchUnseenMessageList(unseenMessageEntity: UnseenMessageEntity, completionHandler: (List<MessagePresentingEntity>) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            val chatDataMessages = database.getChatDAO().getUnseenMessages(unseenMessageEntity.lastDate, unseenMessageEntity.chatId)
            val chatMessages = chatDataMessages.map {
                MessagePresentingEntity(it.userIdFrom, it.userIdTo, it.text, it.date)
            }
            completionHandler(chatMessages)
        }
    }

    override fun searchUsers(userSearchEntity: UserSearchEntity, completionHandler: (List<UserEntity>) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            val dao = database.getUserDAO()
            val userDataEntities = dao.getUsersHavingInName(userSearchEntity.word)
            val userEntities = userDataEntities.map {
                UserEntity(it.id, it.name, it.job, it.picture)
            }
            completionHandler(userEntities)
        }
    }

    private fun placeHolderMessage(chatId: Long, userIdFrom: Long, userIdTo: Long): MessageEntity {
        return MessageEntity(chatId, userIdFrom, userIdTo, "Welcome Text", System.currentTimeMillis())
    }
}

@Database(entities = [UserDataEntity::class, ChatDataEntity::class, MessageDataEntity::class], version = 1)
abstract class ChatDatabase: RoomDatabase() {
    abstract fun getUserDAO(): UserDAO
    abstract fun getChatDAO(): ChatDAO
}
