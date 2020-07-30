package ge.mudamtqveny.dokidokiliteraturechat.client.network

import retrofit2.Response
import retrofit2.http.GET

interface ChatService {

    @GET("connect")
    suspend fun initiateConnection(): Response<Void>
}