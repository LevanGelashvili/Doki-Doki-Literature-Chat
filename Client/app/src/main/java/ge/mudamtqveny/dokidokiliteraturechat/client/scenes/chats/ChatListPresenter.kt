
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.*
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.RestfulChatUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.UserListingUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats.components.chat.ChatViewModel
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.MessagesParameters
import ge.mudamtqveny.dokidokiliteraturechat.client.utils.base64ToBitmap
import ge.mudamtqveny.dokidokiliteraturechat.client.utils.timer.ServiceTimer
import ge.mudamtqveny.dokidokiliteraturechat.client.utils.timer.TimerObserver

interface ChatListPresenting {
    fun handleOnCreate()
    fun handleAfterTextChanged(text: String)
    fun handleChatCellClickedAt(position: Int)
    fun handleChatCellSwipedAt(position: Int)
    fun chatsCount(): Int
    fun chatViewModelAt(position: Int): ChatViewModel
    fun goBackToIntroduce()
}

class ChatListPresenter (

    private val view: ChatListViewing,
    private val parameters: ChatListParameters,
    private val chatUseCase: RestfulChatUseCase,
    private val userListUseCase: UserListingUseCase,
    private val router: ChatListRouting

): ChatListPresenting, TimerObserver {

    private val filterMinLength = 3
    private val nullChatId = 0L

    private var timer = ServiceTimer(this, 3000).apply {
        startService()
    }

    private var existingChats: MutableList<ChatPresentingEntity> = mutableListOf()
        set(value) {
            field = value
            view.handleChatListChanged()
        }

    private var filteredUsers: MutableList<UserEntity> = mutableListOf()
        set(value) {
            field = value
            view.handleChatListChanged()
        }

    private val displayingChats: List<ChatPresentingEntity>
        get() {
            if (view.searchText.length > filterMinLength)
                return existingChats.filter { it.friendUserEntity.name.contains(view.searchText) } +
                        filteredUsers.map { ChatPresentingEntity(nullChatId, "", 0, it) }
            return existingChats
        }

    override fun handleOnCreate() {
        fetchChatList()
    }

    private fun fetchChatList() {
        chatUseCase.fetchChatList(parameters.userIdEntity) { existingChats = it.toMutableList() }
    }

    override fun handleAfterTextChanged(text: String) {
        if (text.length > filterMinLength) fetchUserList(text)
        else if (filteredUsers.isNotEmpty()) filteredUsers = mutableListOf()
    }

    private fun fetchUserList(word: String) {
        userListUseCase.fetchUsersSatisfying(UserSearchEntity(parameters.userIdEntity.id, word)) { filteredUsers = it.toMutableList() }
    }

    override fun handleChatCellClickedAt(position: Int) {

        val chat = displayingChats[position]

        if (chat.chatId == nullChatId) {

            val chatInsertEntity = ChatInsertEntity (
                parameters.userIdEntity.id,
                chat.friendUserEntity.id
            )

            chatUseCase.createChat(chatInsertEntity) { chadId ->
                chat.chatId = chadId.id
             // filteredUsers.apply { removeAt(indexOfFirst { it.id == chat.friendUserEntity.id }) }
             // existingChats.add(chat)
                navigateToMessages(chat)
            }
        }
        else navigateToMessages(chat)
    }

    private fun navigateToMessages(chat: ChatPresentingEntity) {
        val parameters = MessagesParameters (
            chat.chatId,
            parameters.userIdEntity.id,
            chat.friendUserEntity.id,
            chat.friendUserEntity
        )
        router.navigateToMessages(parameters)
     // view.handleChatListChanged()
    }

    override fun handleChatCellSwipedAt(position: Int) {

        val chat = displayingChats[position]

        if (chat.chatId != nullChatId) {
            chatUseCase.deleteChat(ChatDeleteEntity(chat.chatId))

            existingChats.apply { removeAt(indexOfFirst { it.chatId == chat.chatId }) }
            if (view.searchText.length > filterMinLength && chat.friendUserEntity.name.contains(view.searchText))
                filteredUsers.add(chat.friendUserEntity)

            view.handleChatListChanged()
        }
        else {
            view.handleChatListChanged()
            view.showMessage("You can't delete chat which does not exist")
        }
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

    override fun goBackToIntroduce() {
        router.navigateToIntroduce()
    }

    override fun timerExpired() {
        fetchChatList()
    }
}
