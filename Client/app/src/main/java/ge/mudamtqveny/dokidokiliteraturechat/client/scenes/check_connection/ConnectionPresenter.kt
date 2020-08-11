package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.check_connection

import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.ConnectingUseCase

interface ConnectionPresenting {
    fun initiateConnection()
}

class ConnectionPresenter(private val router: ConnectionRouting, private val connectionUseCase: ConnectingUseCase): ConnectionPresenting {

    override fun initiateConnection() {

        connectionUseCase.connect { isSuccessful ->
            if (isSuccessful)
                connectionEstablished()
        }
    }

    private fun connectionEstablished() {
        router.navigateToIntroduce()
    }
}