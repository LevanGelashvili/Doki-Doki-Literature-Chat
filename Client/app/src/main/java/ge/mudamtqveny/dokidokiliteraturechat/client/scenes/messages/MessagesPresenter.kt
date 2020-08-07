package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages

interface MessagesPresenting {
    fun goBackToChats()
    fun sendMessage()
}


class MessagesPresenter(private val router: MessagesRouting): MessagesPresenting {

    override fun goBackToChats() {
        router.navigateToChatList()
    }

    override fun sendMessage() {
        TODO("Not yet implemented")
    }
}