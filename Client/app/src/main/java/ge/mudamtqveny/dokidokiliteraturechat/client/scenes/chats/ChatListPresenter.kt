
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats

import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.ChatPresentingEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserSearchEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.RestfulChatUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.UserListingUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats.components.chat.ChatViewModel

interface ChatListPresenting {
    fun handleOnCreate()
    fun handleAfterTextChanged(text: String)
    fun handleChatCellClickedAt(position: Int)
    fun chatsCount(): Int
    fun chatViewModelAt(position: Int): ChatViewModel
}

class ChatListPresenter (

    private val view: ChatListViewing,
    private val parameters: ChatListParameters,
    private val chatUseCase: RestfulChatUseCase,
    private val userListUseCase: UserListingUseCase,
    private val router: ChatListRouting

): ChatListPresenting {

    private var chats: List<ChatPresentingEntity> = listOf()

    override fun handleOnCreate() {
        fetchChatList()
    }

    override fun handleAfterTextChanged(text: String) {
        if (text.length > 3)
            fetchUserList(text)
    }

    override fun handleChatCellClickedAt(position: Int) {
        Log.d("butter_knife", "Clicked ar: $position")
    }

    override fun chatsCount(): Int {
        return chats.size
    }

    override fun chatViewModelAt(position: Int): ChatViewModel {
        val chat = chats[position]
        val decodedString = Base64.decode(chat.friendUserEntity.picture, Base64.DEFAULT)
        val friendAvatar  = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        return ChatViewModel (
            friendAvatar,
            chat.friendUserEntity.name,
            chat.lastMessage,
            chat.lastMessageDate
        )
    }

    private fun fetchChatList() {
        chatUseCase.fetchChatList(parameters.userIdEntity) { chats = it }
    }

    private fun fetchUserList(word: String) {
        userListUseCase.fetchUsersSatisfying(UserSearchEntity(word)) {
            // Appear Filtered User Chats
            val filtered = it.map { userEntity ->
                ChatPresentingEntity(-1, "", 0, userEntity)
            }
            chats = filtered
            view.handleChatListChanged()
        }
    }
}
