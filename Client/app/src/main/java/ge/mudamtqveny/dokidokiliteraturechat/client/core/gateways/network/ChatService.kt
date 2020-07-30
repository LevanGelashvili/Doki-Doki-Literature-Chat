package ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network

import retrofit2.Response
import retrofit2.http.GET

interface ChatService {

    @GET("connect")
    suspend fun initiateConnection(): Response<Void>
}