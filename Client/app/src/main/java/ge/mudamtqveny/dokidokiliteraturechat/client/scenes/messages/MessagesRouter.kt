package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import ge.mudamtqveny.dokidokiliteraturechat.client.R
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats.ChatListParameters

interface MessagesRouting {
    fun navigateToChatList(parameters: ChatListParameters)
}

class MessagesRouter(private val view: MessagesView): MessagesRouting {

    override fun navigateToChatList(parameters: ChatListParameters) {
        view.findNavController().navigate (
            R.id.actionMessagesViewToChatList,
            bundleOf("parameters" to parameters)
        )
    }
}