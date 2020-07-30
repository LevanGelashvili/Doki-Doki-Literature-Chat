package ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases

import ge.mudamtqveny.dokidokiliteraturechat.client.network.ChatClient
import java.lang.Exception

interface ConnectingUseCase {

    /* Returns true if successful, else false */
    suspend fun connect(): Boolean
}

class ConnectionUseCase: ConnectingUseCase {

    override suspend fun connect(): Boolean {
        return try {
            ChatClient().getClient.initiateConnection().isSuccessful
        } catch (e: Exception) {
            false
        }
    }
}