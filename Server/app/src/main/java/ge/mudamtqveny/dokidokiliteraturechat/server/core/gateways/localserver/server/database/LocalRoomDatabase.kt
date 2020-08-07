package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database

import androidx.room.*
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserIdEntity
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserLoginEntity
import ge.mudamtqveny.dokidokiliteraturechat.server.scenes.server_status.ServerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalRoomDatabase: DatabaseService {

    companion object {
        private var database = Room.databaseBuilder(ServerView.context, ChatDatabase::class.java, "database").build()
        private val instance = LocalRoomDatabase()

        @Synchronized fun getInstance(): DatabaseService {
            return instance
        }
    }

    override fun verifyUser(loginEntity: UserLoginEntity, completionHandler: (UserIdEntity) -> (Unit)) {

        GlobalScope.launch(Dispatchers.IO) {

            val userData = database.getUserDAO().userGivenNickname(loginEntity.name)

            val resultUser: UserIdEntity = if (userData == null) {
                val id = database.getUserDAO().insertUser(UserDataEntity(null, loginEntity))
                UserIdEntity(id)
            } else {
                database.getUserDAO().updateUser(UserDataEntity(userData.id, loginEntity))
                UserIdEntity(userData.id)
            }

            completionHandler(resultUser)
        }
    }
}

@Database(entities = [UserDataEntity::class, ChatDataEntity::class, MessageDataEntity::class], version = 1)
abstract class ChatDatabase: RoomDatabase() {
    abstract fun getUserDAO(): UserDAO
    abstract fun getChatDAO(): ChatDAO
}