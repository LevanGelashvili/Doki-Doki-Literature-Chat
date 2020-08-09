
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.*
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.RestfulChatUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.UserListingUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats.components.chat.ChatViewModel
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.MessagesParameters
import ge.mudamtqveny.dokidokiliteraturechat.client.utils.base64ToBitmap

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

    private var existingChats: MutableList<ChatPresentingEntity> = mutableListOf()
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
        chatUseCase.fetchChatList(parameters.userIdEntity) { existingChats = it.toMutableList() }
    }

    override fun handleAfterTextChanged(text: String) {
        if (text.length > 3) fetchUserList(text)
        else if (filteredUsers.isNotEmpty()) filteredUsers = emptyList()
    }

    private fun fetchUserList(word: String) {
        userListUseCase.fetchUsersSatisfying(UserSearchEntity(word)) { filteredUsers = it }
    }

    override fun handleChatCellClickedAt(position: Int) {

        val chat = displayingChats[position]

        if (chat.chatId == -1L) {

            val chatInsertEntity = ChatInsertEntity (
                parameters.userIdEntity.id,
                chat.friendUserEntity.id
            )

            chatUseCase.createChat(chatInsertEntity) {
                val parameters = MessagesParameters (
                    it.id,
                    parameters.userIdEntity.id,
                    chat.friendUserEntity.id,
                    chat.friendUserEntity
                )
                router.navigateToMessages(parameters)
            }

        } else {

            val parameters = MessagesParameters (
                chat.chatId,
                parameters.userIdEntity.id,
                chat.friendUserEntity.id,
                chat.friendUserEntity
            )
            router.navigateToMessages(parameters)
        }
    }

    override fun handleChatCellSwipedAt(position: Int) {

        val chat = displayingChats[position]

        if (chat.chatId != -1L) {
            chatUseCase.deleteChat(ChatDeleteEntity(chat.chatId, parameters.userIdEntity.id))
            val index = existingChats.indexOfFirst { it.chatId == chat.chatId }
            existingChats.removeAt(index)
        }

        view.handleChatListChanged()
    }

    override fun chatsCount(): Int {
        return displayingChats.size
    }

    override fun chatViewModelAt(position: Int): ChatViewModel {
        val chat = displayingChats[position]

        return ChatViewModel (
            base64ToBitmap(chat.friendUserEntity.picture),
            chat.friendUserEntity.name,
            chat.lastMessage,
            chat.lastMessageDate
        )
    }
}
