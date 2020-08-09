package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages

import java.io.Serializable

data class MessagesParameters (

    val chatId: Long,
    val initUserId: Long,
    val otherUserId: Long

): Serializable
