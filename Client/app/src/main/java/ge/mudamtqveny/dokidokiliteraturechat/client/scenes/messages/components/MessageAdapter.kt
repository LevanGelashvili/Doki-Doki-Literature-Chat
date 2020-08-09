package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.components

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.MessagesPresenting

enum class MessageTypes {
    MESSAGE_SENT, MESSAGE_RECEIVED
}

class MessageAdapter(private val presenter: MessagesPresenting): RecyclerView.Adapter<MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolderFactory.getMessageHolder(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return if (presenter.isUserMessageAt(position))
            MessageTypes.MESSAGE_SENT.ordinal
        else
            MessageTypes.MESSAGE_RECEIVED.ordinal
    }

    override fun getItemCount(): Int {
        return presenter.messageCount()
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.configure(presenter.viewModelAt(position))
    }

    fun requestNewlyTypedMessage() {
        notifyItemChanged(itemCount)
    }
}