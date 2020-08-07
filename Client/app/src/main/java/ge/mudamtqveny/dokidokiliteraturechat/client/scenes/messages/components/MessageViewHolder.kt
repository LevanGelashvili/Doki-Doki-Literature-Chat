package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ge.mudamtqveny.dokidokiliteraturechat.client.R
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.viewmodels.MessageViewModel

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
    abstract fun configure(viewModel: MessageViewModel)
}

class MessageSentViewHolder(view: View): MessageViewHolder(view) {

    override fun configure(viewModel: MessageViewModel) {
        TODO("Not yet implemented")
    }

}

class MessageReceivedViewHolder(view: View): MessageViewHolder(view) {

    override fun configure(viewModel: MessageViewModel) {
        TODO("Not yet implemented")
    }
}
