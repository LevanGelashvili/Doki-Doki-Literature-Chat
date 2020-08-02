package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities

import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database.UserDataEntity

data class UserEntity (
    var id: Long?,
    val nickname: String,
    val job: String,
    val picture: String
    //TODO: CHATS
) {
    constructor(userData: UserDataEntity) : this(
        id = userData.id,
        nickname = userData.name,
        job = userData.job,
        picture = userData.picture
    )

}

data class UserLoginEntity (
    val nickname: String,
    val job: String,
    val picture: String
)