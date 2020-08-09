
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats

import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.ChatInsertEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.ChatPresentingEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserSearchEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.RestfulChatUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.UserListingUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats.components.chat.ChatViewModel
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.MessagesParameters

interface ChatListPresenting {
    fun handleOnCreate()
    fun handleAfterTextChanged(text: String)
    fun handleChatCellClickedAt(position: Int)
    fun handleChatCellSwipedAt(position: Int)
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

    private var existingChats: List<ChatPresentingEntity> = emptyList()
        set(value) {
            field = value
            view.handleChatListChanged()
        }

    private var filteredUsers: List<UserEntity> = emptyList()
        set(value) {
            field = value
            view.handleChatListChanged()
        }

    private val displayingChats: List<ChatPresentingEntity>
        get() {

            val filteredExistingChats =
                if (view.searchText.length > 3)
                    existingChats.filter { it.friendUserEntity.name.contains(view.searchText) }
                else existingChats

            val filteredNonExistingChats =
                filteredUsers.map { ChatPresentingEntity(-1, "", -1, it) }

            return filteredExistingChats + filteredNonExistingChats
        }

    override fun handleOnCreate() {
        fetchChatList()
    }

    private fun fetchChatList() {
        chatUseCase.fetchChatList(parameters.userIdEntity) { existingChats = it }
    }

    override fun handleAfterTextChanged(text: String) {
        if (text.length > 3) fetchUserList(text)
        else filteredUsers = emptyList()
    }

    private fun fetchUserList(word: String) {
        userListUseCase.fetchUsersSatisfying(UserSearchEntity(word)) { filteredUsers = it }
    }

    override fun handleChatCellClickedAt(position: Int) {

        val chatPresentingEntity = displayingChats[position]

        if (chatPresentingEntity.chatId == -1L) {

            val chatInsertEntity = ChatInsertEntity (
                parameters.userIdEntity.id,
                chatPresentingEntity.friendUserEntity.id
            )

            chatUseCase.createChat(chatInsertEntity) {
                val parameters = MessagesParameters (
                    it.id,
                    parameters.userIdEntity.id,
                    chatPresentingEntity.friendUserEntity.id
                )
                router.navigateToMessages(parameters)
            }

        } else {
            val parameters = MessagesParameters (
                chatPresentingEntity.chatId,
                parameters.userIdEntity.id,
                chatPresentingEntity.friendUserEntity.id
            )
            router.navigateToMessages(parameters)
        }

    }

    override fun handleChatCellSwipedAt(position: Int) { // TODO
        Log.d("butter_knife", "Swiped At: $position")
    }

    override fun chatsCount(): Int {
        return displayingChats.size
    }

    override fun chatViewModelAt(position: Int): ChatViewModel {
        val chat = displayingChats[position]
        val decodedString = Base64.decode(chat.friendUserEntity.picture, Base64.DEFAULT)
        val friendAvatar  = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        return ChatViewModel (
            friendAvatar,
            chat.friendUserEntity.name,
            chat.lastMessage,
            chat.lastMessageDate
        )
    }
}
