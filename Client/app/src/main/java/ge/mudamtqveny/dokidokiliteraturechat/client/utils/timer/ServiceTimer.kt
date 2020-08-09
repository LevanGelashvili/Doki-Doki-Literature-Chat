package ge.mudamtqveny.dokidokiliteraturechat.client.utils.timer

import kotlinx.coroutines.*

class ServiceTimer(private val observer: TimerObserver, private val ms: Long) {

    private lateinit var scope: CoroutineScope

    fun startService() {
        scope = CoroutineScope(Dispatchers.Default).apply {
            launch {
                while (true) {
                    delay(ms)
                    CoroutineScope(Dispatchers.Main).launch {
                        observer.timerExpired()
                    }
                }
            }
        }
    }

    fun stopService() {
        scope.coroutineContext.cancelChildren()
        scope.cancel()
    }
}

