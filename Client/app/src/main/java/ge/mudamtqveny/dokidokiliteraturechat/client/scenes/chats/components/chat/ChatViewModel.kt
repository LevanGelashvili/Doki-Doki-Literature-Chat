
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats.components.chat

import android.graphics.Bitmap

data class ChatViewModel (
    val friendAvatar: Bitmap,
    val friendName: String,
    val lastMessage: String,
    val lastMessageDate: Long
)

//  val decodedString = Base64.decode(friendAvatar, Base64.DEFAULT)
//  val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
