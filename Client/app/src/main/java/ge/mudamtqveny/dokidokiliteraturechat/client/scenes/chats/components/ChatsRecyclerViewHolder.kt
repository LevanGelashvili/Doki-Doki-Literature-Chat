
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats.components

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.mudamtqveny.dokidokiliteraturechat.client.R
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats.components.viewmodels.ChatViewModel
import ge.mudamtqveny.dokidokiliteraturechat.client.utils.timeBetweenMessage

class ChatsRecyclerViewHolder(chatView: View): RecyclerView.ViewHolder(chatView) {

    private val avatarImageView = chatView.findViewById<ImageView>(R.id.avatarImageView)
    private val nameTextView = chatView.findViewById<TextView>(R.id.nameTextView)
    private val lastMessageTextView = chatView.findViewById<TextView>(R.id.lastMessageTextView)
    private val lastMessageDateTextView = chatView.findViewById<TextView>(R.id.lastMessageDateTextView)

    fun configure(chatViewModel: ChatViewModel) {
        avatarImageView.setImageBitmap(chatViewModel.friendAvatar)
        nameTextView.text = chatViewModel.friendName
        lastMessageTextView.text = chatViewModel.lastMessage
        lastMessageDateTextView.text = timeBetweenMessage(chatViewModel.lastMessageDate)
    }
}
