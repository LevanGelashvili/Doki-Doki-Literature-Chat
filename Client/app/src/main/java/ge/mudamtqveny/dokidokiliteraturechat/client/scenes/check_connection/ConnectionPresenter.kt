package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.check_connection

import android.util.Log
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.ConnectingUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface ConnectionPresenting {
    fun initiateConnection()
}

class ConnectionPresenter(private val router: ConnectionRouting, private val connectionUseCase: ConnectingUseCase): ConnectionPresenting {

    override fun initiateConnection() {
        connectionUseCase.connect { isSuccessful ->
            if (isSuccessful)
                connectionEstablished()
            // TODO: else display error message
        }
    }

    private fun connectionEstablished() {
        router.navigateToIntroduce()
    }
}