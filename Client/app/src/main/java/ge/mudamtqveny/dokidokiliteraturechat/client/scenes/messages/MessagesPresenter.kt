package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages

import android.util.Log
import ge.mudamtqveny.dokidokiliteraturechat.client.core.timer.ServiceTimer
import ge.mudamtqveny.dokidokiliteraturechat.client.core.timer.TimerObserver
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.viewmodels.MessageViewModel

interface MessagesPresenting {
    fun goBackToChats()
    fun sendMessage()

    fun isUserMessageAt(position: Int): Boolean
    fun viewModelAt(position: Int): MessageViewModel
    fun messageCount(): Int
}


class MessagesPresenter(private val router: MessagesRouting): MessagesPresenting, TimerObserver {

    private var timer = ServiceTimer(this, 2000).apply {
        startService()
        // TODO: StopService in navigate
    }

    override fun goBackToChats() {
        router.navigateToChatList()
    }

    override fun sendMessage() {
        TODO("Not yet implemented")
    }

    override fun isUserMessageAt(position: Int): Boolean {
        TODO("Not yet implemented")
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