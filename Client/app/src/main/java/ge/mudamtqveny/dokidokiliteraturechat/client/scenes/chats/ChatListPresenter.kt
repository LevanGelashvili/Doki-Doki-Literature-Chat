
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats

import android.util.Log
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserSearchEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.ChatListingUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.UserListUseCase

interface ChatListPresenting {
    fun handleOnCreate()
}

class ChatListPresenter (

    private val view: ChatListViewing,
    private val parameters: ChatListParameters,
    private val chatListUseCase: ChatListingUseCase,
    private val userListUseCase: UserListUseCase,
    private val router: ChatListRouting

): ChatListPresenting {

    override fun handleOnCreate() {
        fetchChatList()
        fetchUsers("word")
    }

    private fun fetchChatList() {
        chatListUseCase.fetchChatList(parameters.userIdEntity) {
            Log.d("butter_knife", "Chat List: $it")
        }
    }

    private fun fetchUsers(word: String) {
        if (word.length > 3) {
            userListUseCase.fetchUsersSatisfying(UserSearchEntity(word)) {
                Log.d("butter_knife", "User List: $it")
            }
        }
    }
}
