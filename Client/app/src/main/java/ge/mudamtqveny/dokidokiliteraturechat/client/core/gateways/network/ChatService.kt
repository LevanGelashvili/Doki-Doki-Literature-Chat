package ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserIdEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserLoginEntity
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatService {

    @GET("connect")
    fun initiateConnection(): Call<Void>

    @Headers("Content-Type: application/json")
    @POST("verify")
    fun verifyUser(@Body loginEntity: UserLoginEntity): Call<UserIdEntity>
}