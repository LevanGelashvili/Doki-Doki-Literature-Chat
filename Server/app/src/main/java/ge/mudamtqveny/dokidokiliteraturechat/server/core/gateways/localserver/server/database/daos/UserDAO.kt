
package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database.daos

import androidx.room.*
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database.entities.MESSAGE_TABLE
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

    @Query ("" +
        "SELECT user_id, name, what_i_do, picture\n" +
        "  FROM $USER_TABLE\n" +
        " WHERE name LIKE '%' || :substring  || '%' AND\n" +
        "       user_id IN\n" +
        "(SELECT user_id\n"+
        "   FROM $USER_TABLE\n"+
        "  WHERE user_id <> :userId\n"+
        " EXCEPT \n" +
        " SELECT *\n" +
        "   FROM \n" +
        " (SELECT DISTINCT user_id_from\n" +
        "    FROM $MESSAGE_TABLE\n" +
        "   WHERE user_id_to = :userId\n"+
        "   UNION \n" +
        "  SELECT DISTINCT user_id_to\n" +
        "    FROM $MESSAGE_TABLE\n" +
        "   WHERE user_id_from = :userId) t)"
    )
    suspend fun getNonFriendUsersWithSubstringInName(userId: Long, substring: String): List<UserDataEntity>

}
