package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages

interface MessagesRouting {
    fun navigateToChatList()
}

class MessagesRouter(private val view: MessagesView): MessagesRouting {

    override fun navigateToChatList() {
        TODO("Not yet implemented")
    }
}