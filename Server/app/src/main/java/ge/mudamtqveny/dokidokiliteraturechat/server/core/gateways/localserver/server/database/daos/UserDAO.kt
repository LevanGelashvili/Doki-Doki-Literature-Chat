
package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database.daos

import androidx.room.*
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database.entities.USER_TABLE
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database.entities.UserDataEntity

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

}
