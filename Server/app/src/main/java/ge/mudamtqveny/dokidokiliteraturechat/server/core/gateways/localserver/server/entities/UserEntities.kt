package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities

/*data class UserEntity (
    var id: Long?,
    val name: String,
    val job: String,
    val picture: String,
    val chats: List<ChatEntity>
) {

    constructor(id: Long, loginEntity: UserLoginEntity, chats: List<ChatEntity>) : this(
        id = id,
        name = loginEntity.name,
        job = loginEntity.job,
        picture = loginEntity.picture,
        chats = chats
    )
}*/

data class UserIdEntity (
    val id: Long
)

data class UserLoginEntity (
    val name: String,
    val job: String,
    val picture: String
)