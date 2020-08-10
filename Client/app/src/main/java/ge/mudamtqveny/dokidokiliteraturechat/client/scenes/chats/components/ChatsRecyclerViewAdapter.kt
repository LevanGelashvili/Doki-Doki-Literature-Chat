
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.mudamtqveny.dokidokiliteraturechat.client.R
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats.ChatListPresenting

class ChatsRecyclerViewAdapter (

    private val presenter: ChatListPresenting

): RecyclerView.Adapter<ChatsRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsRecyclerViewHolder {
        val chatView = LayoutInflater.from(parent.context).inflate(R.layout.chat_cell, parent, false);
        return ChatsRecyclerViewHolder(chatView);
    }

    override fun getItemCount(): Int {
        return presenter.chatsCount()
    }

    override fun onBindViewHolder(holder: ChatsRecyclerViewHolder, position: Int) {
        holder.configure(presenter.chatViewModelAt(position))
        holder.itemView.setOnClickListener {
            presenter.handleChatCellClickedAt(position)
        }
    }
}
