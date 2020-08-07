package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages

import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.viewmodels.MessageViewModel

interface MessagesPresenting {
    fun goBackToChats()
    fun sendMessage()

    fun isUserMessageAt(position: Int): Boolean
    fun viewModelAt(position: Int): MessageViewModel
    fun messageCount(): Int
}


class MessagesPresenter(private val router: MessagesRouting): MessagesPresenting {

    override fun goBackToChats() {
        router.navigateToChatList()
    }

    override fun sendMessage() {
        TODO("Not yet implemented")
    }

    override fun isUserMessageAt(position: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun viewModelAt(position: Int): MessageViewModel {
        TODO("Not yet implemented")
    }

    override fun messageCount(): Int {
        TODO("Not yet implemented")
    }
}