
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats.components.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.mudamtqveny.dokidokiliteraturechat.client.R

class ChatsRecyclerViewAdapter (

    private val chats: List<ChatViewModel>

): RecyclerView.Adapter<ChatsRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsRecyclerViewHolder {
        val chatView = LayoutInflater.from(parent.context).inflate(R.layout.chat_cell, parent, false);
        return ChatsRecyclerViewHolder(chatView);
    }

    override fun getItemCount(): Int {
        return chats.size
    }

    override fun onBindViewHolder(holder: ChatsRecyclerViewHolder, position: Int) {
        holder.configure(chats[position])
    }
}
