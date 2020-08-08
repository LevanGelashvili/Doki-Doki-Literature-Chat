package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database

import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserIdEntity
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserLoginEntity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.*
import ge.mudamtqveny.dokidokiliteraturechat.server.scenes.server_status.ServerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalRoomDatabase: DatabaseService {

    companion object {
        private var database = Room.databaseBuilder(ServerView.context, ChatDatabase::class.java, "database").build()
        private val instance = LocalRoomDatabase()

        fun getInstance(): DatabaseService {
            return instance
        }
    }

    override fun verifyUser(loginEntity: UserLoginEntity, completionHandler: (UserIdEntity) -> (Unit)) {

        GlobalScope.launch(Dispatchers.IO) {

            val userData = database.getUserDAO().userGivenNickname(loginEntity.name)

            val resultUser: UserIdEntity = if (userData == null) {
                val id = database.getUserDAO().insertUser(UserDataEntity(loginEntity))
                UserIdEntity(id)
            } else {
                database.getUserDAO().updateUser(UserDataEntity(userData.id, loginEntity))
                UserIdEntity(userData.id)
            }

            completionHandler(resultUser)
        }
    }

    override fun fetchChatList(userIdEntity: UserIdEntity, completionHandler: (List<ChatPresentingEntity>) -> Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            val chatListDataEntities = database.getChatDAO().getUserChats(userIdEntity.id)
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

            database.getChatDAO().deleteChat(chatDeleteEntity.chatId, chatDeleteEntity.deleterUserId)

            if (database.getChatDAO().numberOfChatEntriesLeft(chatDeleteEntity.chatId) == 0) {
                database.getChatDAO().deleteChatlessMessages(chatDeleteEntity.chatId)
            }
        }
    }

    override fun createChat(chatInsertEntity: ChatInsertEntity, completionHandler: (ChatIdEntity) -> Unit) {

        GlobalScope.launch(Dispatchers.IO) {

            val newChatId = 1 + database.getChatDAO().getMaxChatId()
            val initChatData = ChatDataEntity(chatInsertEntity.initUserId, newChatId)
            val otherChatData = ChatDataEntity(chatInsertEntity.otherUserId, newChatId)

            val initChatId = database.getChatDAO().insertChat(initChatData)
            database.getChatDAO().insertChat(otherChatData)

            insertMessage(placeHolderMessage(newChatId, chatInsertEntity.initUserId, chatInsertEntity.otherUserId))

            completionHandler(ChatIdEntity(initChatId))
        }
    }

    override fun insertMessage(messageEntity: MessageEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            database.getChatDAO().insertMessage(MessageDataEntity(messageEntity))
        }
    }

    override fun fetchMessageList(chatIdEntity: ChatIdEntity, completionHandler: (List<MessagePresentingEntity>) -> Unit) {

        GlobalScope.launch(Dispatchers.IO) {
            val chatMessages = database.getChatDAO().getMessagesFromChat(chatIdEntity.id)
            completionHandler(chatMessages)
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