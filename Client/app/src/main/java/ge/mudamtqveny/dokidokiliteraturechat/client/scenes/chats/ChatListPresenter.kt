
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserSearchEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.RestfulChatUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.UserListingUseCase

interface ChatListPresenting {
    fun handleOnCreate()
}

class ChatListPresenter (

    private val view: ChatListViewing,
    private val parameters: ChatListParameters,
    private val chatUseCase: RestfulChatUseCase,
    private val userListUseCase: UserListingUseCase,
    private val router: ChatListRouting

): ChatListPresenting {

    override fun handleOnCreate() {
        fetchChatList()
        fetchUsers("word")
    }

    private fun fetchChatList() {
        chatUseCase.fetchChatList(parameters.userIdEntity) {

        }
    }

    private fun fetchUsers(word: String) {
        if (word.length > 3) {
            userListUseCase.fetchUsersSatisfying(UserSearchEntity(word)) {

            }
        }
    }
}
