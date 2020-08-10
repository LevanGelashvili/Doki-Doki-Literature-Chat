
package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities

data class UserEntity (
    var id: Long,
    val name: String,
    val job: String,
    val picture: String
)

data class UserIdEntity (
    val id: Long
)

data class UserLoginEntity (
    val name: String,
    val job: String,
    val picture: String
)

data class UserSearchEntity (
    val userId: Long,
    val word: String
)
