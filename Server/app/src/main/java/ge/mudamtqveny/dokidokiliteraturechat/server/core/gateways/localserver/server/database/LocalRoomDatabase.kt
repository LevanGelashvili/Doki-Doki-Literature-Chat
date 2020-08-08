package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database

import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.ChatPresentingEntity
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
            val dao = database.getChatDAO()
            val chatList = dao.getUserChats(userIdEntity.id)
            completionHandler(chatList)
        }
    }

    override fun deleteChat(chatDeleteEntity: ChatDeleteEntity) {
        GlobalScope.launch(Dispatchers.IO) {
            //database.getChatDAO().deleteChat(chatDeleteEntity.id)
        }
    }

    override fun createChat(chatInsertEntity: ChatInsertEntity, completionHandler: (ChatIdEntity) -> Unit) {

        GlobalScope.launch(Dispatchers.IO) {



            /*val initiatorChatData = ChatDataEntity(chatInsertEntity.initiatorUserId)
            val otherChatData = ChatDataEntity(chatInsertEntity.otherUserId)

            val initiatorChatId = database.getChatDAO().insertChat(initiatorChatData)
            database.getChatDAO().insertChat(otherChatData)

            //TODO: Insert placeholder message

            completionHandler(ChatIdEntity(initiatorChatId))*/
        }
    }
}

@Database(entities = [UserDataEntity::class, ChatDataEntity::class, MessageDataEntity::class], version = 1)
abstract class ChatDatabase: RoomDatabase() {
    abstract fun getUserDAO(): UserDAO
    abstract fun getChatDAO(): ChatDAO
}