
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats

import android.util.Log
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.ChatListingUseCase

interface ChatListPresenting {
    fun handleOnCreate()
}

class ChatListPresenter (

    private val view: ChatListViewing,
    private val parameters: ChatListParameters,
    private val chatListUseCase: ChatListingUseCase,
    private val router: ChatListRouting

): ChatListPresenting {

    override fun handleOnCreate() {
        fetchChatList()
    }

    private fun fetchChatList() {
        chatListUseCase.fetchChatList(parameters.userIdEntity) {
            Log.d("butter_knife", it.toString())
        }
    }
}
