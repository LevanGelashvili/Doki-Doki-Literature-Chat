package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages

import android.util.Log
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.ChatIdEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.MessageEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.MessagePresentingEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.timer.ServiceTimer
import ge.mudamtqveny.dokidokiliteraturechat.client.core.timer.TimerObserver
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.MessageListingUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.MessageSendingUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.MessagesUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.UnseenMessageListingUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.viewmodels.MessageViewModel

interface MessagesPresenting {
    fun goBackToChats()
    fun sendMessage(messageViewModel: MessageViewModel)

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

    private var timer = ServiceTimer(this, 2000).apply {
        startService()
    }

    private var messages: List<MessagePresentingEntity> = listOf()

    override fun fetchMessages() {
        val chatIdEntity = ChatIdEntity(parameters.chatId)
        messageListingUseCase.getMessages(chatIdEntity) {
            messages = it
            view.messageListUpdated()
        }
    }

    override fun sendMessage(messageViewModel: MessageViewModel) {
        Log.d("butter knife", "Sending message from presenter")
        messageSendingUseCase.sendMessage(MessageEntity(parameters, messageViewModel))
    }

    override fun goBackToChats() {
        timer.stopService()
        router.navigateToChatList()
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
        Log.d("butter knife", "timer arrived")
    }
}