
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats.components.chat

import android.graphics.Bitmap

data class ChatViewModel (
    val friendAvatar: Bitmap,
    val friendName: String,
    val lastMessage: String,
    val lastMessageDate: Long
)
