
package ge.mudamtqveny.dokidokiliteraturechat.client.core.entities

data class UserEntity (
    val id: Long,
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
    val word: String
)
