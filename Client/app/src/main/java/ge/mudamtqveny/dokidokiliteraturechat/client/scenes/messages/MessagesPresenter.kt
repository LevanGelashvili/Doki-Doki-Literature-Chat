package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages

import android.util.Log
import ge.mudamtqveny.dokidokiliteraturechat.client.core.timer.ServiceTimer
import ge.mudamtqveny.dokidokiliteraturechat.client.core.timer.TimerObserver
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.MessageListingUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.MessageSendingUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.MessagesUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.UnseenMessageListingUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.viewmodels.MessageViewModel

interface MessagesPresenting {
    fun goBackToChats()
    fun sendMessage()

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

    override fun goBackToChats() {
        timer.stopService()
        router.navigateToChatList()
    }

    override fun fetchMessages() {
        TODO("Not yet implemented")
    }

    override fun sendMessage() {
        TODO("Not yet implemented")
    }

    override fun isUserMessageAt(position: Int): Boolean {
        return true
    }

    override fun viewModelAt(position: Int): MessageViewModel {
        TODO("Not yet implemented")
    }

    override fun messageCount(): Int {
        TODO("Not yet implemented")
    }

    override fun timerExpired() {
        Log.d("Here", "timer arrived")
    }
}