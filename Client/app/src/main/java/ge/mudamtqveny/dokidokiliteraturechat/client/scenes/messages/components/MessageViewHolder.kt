package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ge.mudamtqveny.dokidokiliteraturechat.client.R
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.components.viewmodels.MessageViewModel
import ge.mudamtqveny.dokidokiliteraturechat.client.utils.formatDate

class MessageViewHolderFactory {

    companion object {

        fun getMessageHolder(parent: ViewGroup, messageType: Int): MessageViewHolder {

            val inflater = LayoutInflater.from(parent.context)

            return if (messageType == MessageTypes.MESSAGE_SENT.ordinal)
                MessageSentViewHolder(inflater.inflate(R.layout.message_sent_cell, parent, false))
            else
                MessageReceivedViewHolder(inflater.inflate(R.layout.message_received_cell, parent, false))
        }
    }
}

abstract class MessageViewHolder(view: View): RecyclerView.ViewHolder(view) {
    abstract var messageBubble: TextView
    abstract var messageDate: TextView

    fun configure(viewModel: MessageViewModel) {
        messageBubble.text = viewModel.text
        messageDate.text = formatDate(viewModel.date, "HH:mm")
    }
}

class MessageSentViewHolder(view: View): MessageViewHolder(view) {
    override var messageBubble: TextView = view.findViewById(R.id.message_sent_bubble)
    override var messageDate: TextView = view.findViewById(R.id.message_sent_date)
}

class MessageReceivedViewHolder(view: View): MessageViewHolder(view) {
    override var messageBubble: TextView = view.findViewById(R.id.message_received_bubble)
    override var messageDate: TextView = view.findViewById(R.id.message_received_date)
}
