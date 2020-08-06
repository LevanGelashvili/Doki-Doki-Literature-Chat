package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database

import androidx.room.*
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserEntity
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserLoginEntity
import ge.mudamtqveny.dokidokiliteraturechat.server.scenes.server_status.ServerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalRoomDatabase: DatabaseService {

    companion object {
        // TODO: If kotlin null exception error here, remove database from companion object and use it as a private variable
        private var database = Room.databaseBuilder(ServerView.context, ChatDatabase::class.java, "database").build()
        private val instance = LocalRoomDatabase()

        @Synchronized fun getInstance(): DatabaseService {
            return instance
        }
    }

    override fun verifyUser(loginEntity: UserLoginEntity): UserEntity {

        var resultUser: UserEntity? = null

        GlobalScope.launch(Dispatchers.IO) {

            val userData = database.getUserDAO().userGivenNickname(loginEntity.name)

            resultUser = if (userData == null) {

                val id = database.getUserDAO().insertUser(UserDataEntity(null, loginEntity))
                UserEntity(id, loginEntity, listOf())

            } else {

                database.getUserDAO().updateUser(UserDataEntity(userData.id, loginEntity))
                val chats: List<ChatDataEntity> = database.getChatDAO().getUserChats(userData.id!!)
                // TODO KRAWA: chats to List<ChatEntity> instead of listOf()
                UserEntity(userData.id!!, loginEntity, listOf())

            }
        }

        return resultUser!!
    }

}

@Database(entities = [UserDataEntity::class, ChatDataEntity::class, MessageDataEntity::class], version = 1)
abstract class ChatDatabase: RoomDatabase() {
    abstract fun getUserDAO(): UserDAO
    abstract fun getChatDAO(): ChatDAO
}