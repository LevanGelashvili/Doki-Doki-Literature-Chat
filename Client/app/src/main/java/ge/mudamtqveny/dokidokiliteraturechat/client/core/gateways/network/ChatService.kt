package ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserLoginEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself.viewmodels.IntroduceUserViewModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatService {

    @GET("connect")
    suspend fun initiateConnection(): Response<Void>

    @Headers("Content-Type: application/json")
    @POST("verify")
    suspend fun verifyUser(@Body user: UserLoginEntity): Call<UserEntity>
}