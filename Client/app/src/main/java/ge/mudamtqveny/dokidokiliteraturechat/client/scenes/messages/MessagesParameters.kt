package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserEntity
import java.io.Serializable

data class MessagesParameters (

    val chatId: Long,
    val initUserId: Long,
    val otherUserId: Long,
    val userEntity: UserEntity

): Serializable
