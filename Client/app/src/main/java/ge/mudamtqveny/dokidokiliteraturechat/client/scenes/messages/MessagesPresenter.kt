package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.*
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.MessageListingUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.MessageSendingUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.UnseenMessageListingUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats.ChatListParameters
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.components.viewmodels.MessageViewModel
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.components.viewmodels.ToolbarUserViewModel
import ge.mudamtqveny.dokidokiliteraturechat.client.utils.timer.ServiceTimer
import ge.mudamtqveny.dokidokiliteraturechat.client.utils.timer.TimerObserver

interface MessagesPresenting {
    fun goBackToChats()
    fun sendMessage(messageViewModel: MessageViewModel)
    fun getToolbarViewModel(): ToolbarUserViewModel
    fun handleOnCreate()

    fun isUserMessageAt(position: Int): Boolean
    fun viewModelAt(position: Int): MessageViewModel

    fun fetchMessages()
    fun messageCount(): Int
}

class MessagesPresenter(

    private val view: MessagesViewing,
    private val parameters: MessagesParameters,
    private val router: MessagesRouting,
    private val messageSendingUseCase: MessageSendingUseCase,
    private val messageListingUseCase: MessageListingUseCase,
    private val unseenMessageListingUseCase: UnseenMessageListingUseCase

) : MessagesPresenting, TimerObserver {

    private lateinit var timer: ServiceTimer
    private var messages: MutableList<MessagePresentingEntity> = mutableListOf()

    override fun handleOnCreate() {
        timer = ServiceTimer(this, 2000).apply {
            startService()
        }
        fetchMessages()
    }

    override fun fetchMessages() {
        val chatIdEntity = ChatIdEntity(parameters.chatId)
        messageListingUseCase.getMessages(chatIdEntity) {
            messages.clear()
            messages.addAll(it)
            view.messageListUpdated()
        }
    }

    override fun sendMessage(messageViewModel: MessageViewModel) {
        val messageEntity = MessageEntity(parameters, messageViewModel)
        messages.add(MessagePresentingEntity(messageEntity))
        view.displayNewlyTypedMessage()
        messageSendingUseCase.sendMessage(MessageEntity(parameters, messageViewModel))
    }

    override fun getToolbarViewModel(): ToolbarUserViewModel {
        return parameters.userEntity.toToolbarViewModel()
    }

    override fun goBackToChats() {
        timer.stopService()
        val chatParameters = ChatListParameters(UserIdEntity(parameters.initUserId))
        router.navigateToChatList(chatParameters)
    }

    override fun isUserMessageAt(position: Int): Boolean {
        return parameters.initUserId == messages[position].userIdFrom
    }

    override fun viewModelAt(position: Int): MessageViewModel {
        return messages[position].toMessageViewModel()
    }

    override fun messageCount(): Int {
        return messages.size
    }

    override fun timerExpired() {
        val lastDate = if (messages.isEmpty()) 0 else messages.last().date
        val unseenMessageEntity = UnseenMessageEntity(lastDate, parameters.chatId)

        unseenMessageListingUseCase.getUnseenMessages(unseenMessageEntity) {
            messages.addAll(it)
            view.messageListUpdated()
        }
    }
}