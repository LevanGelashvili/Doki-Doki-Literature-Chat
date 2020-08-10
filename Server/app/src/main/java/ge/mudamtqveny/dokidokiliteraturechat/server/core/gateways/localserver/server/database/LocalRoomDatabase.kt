
package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database

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
            database.getChatDAO().deleteChat(chatDeleteEntity.chatId)
        }
    }

    override fun createChat(chatInsertEntity: ChatInsertEntity, completionHandler: (ChatIdEntity) -> Unit) {

        GlobalScope.launch(Dispatchers.IO) {

            val dao = database.getChatDAO()
            val initUserId = chatInsertEntity.initUserId
            val otherUserId = chatInsertEntity.otherUserId

            val initChatData = ChatDataEntity(initUserId)
            val initChatId = dao.insertChat(initChatData)

            var otherChatId = dao.getToChatId(initChatId, initUserId, otherUserId)
            if (otherChatId == null) {
                val otherChatData = ChatDataEntity(otherUserId)
                otherChatId = dao.insertChat(otherChatData)
            }

            insertPlaceholderMessagesIn(initChatId, otherChatId, placeHolderMessage(0, initUserId, otherUserId))

            completionHandler(ChatIdEntity(initChatId))
        }
    }

    private suspend fun insertPlaceholderMessagesIn(chat1Id: Long, chat2Id: Long, messageEntity: MessageEntity) {

        val message = MessageDataEntity(messageEntity)
        val dao = database.getChatDAO()

        message.chatID = chat1Id
        dao.insertMessage(message)

        message.chatID = chat2Id
        dao.insertMessage(message)
    }

    override fun insertMessage(messageEntity: MessageEntity) {
        GlobalScope.launch(Dispatchers.IO) {

            val message = MessageDataEntity(messageEntity)
            val dao = database.getChatDAO()
            dao.insertMessage(message)

            var toChatId = dao.getToChatId(messageEntity.chatId, messageEntity.userIdFrom, messageEntity.userIdTo)
            if (toChatId == null) {
                val otherChatData = ChatDataEntity(messageEntity.userIdTo)
                toChatId = dao.insertChat(otherChatData)
            }
            message.chatID = toChatId
            dao.insertMessage(message)
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
        return MessageEntity(chatId, userIdFrom, userIdTo, "Hi there!", System.currentTimeMillis())
    }
}

@Database(entities = [UserDataEntity::class, ChatDataEntity::class, MessageDataEntity::class], version = 1)
abstract class ChatDatabase: RoomDatabase() {
    abstract fun getUserDAO(): UserDAO
    abstract fun getChatDAO(): ChatDAO
}
