
package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserLoginEntity

const val USER_TABLE = "users"

@Entity(tableName = USER_TABLE)
data class UserDataEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var id: Long = 0,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "what_i_do")
    var job: String,

    @ColumnInfo(name = "picture")
    var picture: String
)  {

    /**
     * Constructor for INSERT
     */
    constructor(userLoginEntity: UserLoginEntity): this (
        name = userLoginEntity.name,
        job = userLoginEntity.job,
        picture = userLoginEntity.picture
    )

    /**
     * Constructor for UPDATE
     */
    constructor(id: Long, userLoginEntity: UserLoginEntity): this(userLoginEntity) {
        this.id = id
    }
}
