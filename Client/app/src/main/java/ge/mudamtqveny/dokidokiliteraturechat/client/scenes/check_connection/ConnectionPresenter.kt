package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.check_connection

import android.util.Log
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.ConnectingUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface ConnectionPresenting {
    fun initiateConnection()
    fun connectionEstablished()
}

class ConnectionPresenter(private val router: ConnectionRouting, private val connectionUseCase: ConnectingUseCase): ConnectionPresenting {

    override fun initiateConnection() {
        CoroutineScope(Dispatchers.IO).launch {
            if (connectionUseCase.connect()) {
                router.navigateToIntroduce()
            }
            Log.d("Here", "wow over")
        }
    }

    override fun connectionEstablished() {
        router.navigateToIntroduce()
    }

}