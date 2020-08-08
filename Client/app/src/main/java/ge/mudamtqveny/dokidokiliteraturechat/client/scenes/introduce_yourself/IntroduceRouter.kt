package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import ge.mudamtqveny.dokidokiliteraturechat.client.R
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserIdEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats.ChatListParameters

interface IntroduceRouting {
    fun navigateToChatList(userId: UserIdEntity)
}

class IntroduceRouter(private val view: IntroduceView): IntroduceRouting {

    override fun navigateToChatList(userId: UserIdEntity) {
        view.findNavController().navigate (
            R.id.actionIntroduceViewToChatListView,
            bundleOf("parameters" to ChatListParameters(userId))
        )
    }
}