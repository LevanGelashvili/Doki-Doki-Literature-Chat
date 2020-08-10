
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import ge.mudamtqveny.dokidokiliteraturechat.client.R
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.MessagesParameters

interface ChatListRouting {
    fun navigateToMessages(parameters: MessagesParameters)
    fun navigateToIntroduce()
}

class ChatListRouter(private val view: ChatListView): ChatListRouting {

    override fun navigateToMessages(parameters: MessagesParameters) {
        view.findNavController().navigate (
            R.id.actionChatListViewToMessagesView,
            bundleOf("parameters" to parameters)
        )
    }

    override fun navigateToIntroduce() {
        view.findNavController().navigate(R.id.actionChatListViewToIntroduceView)
    }
}
