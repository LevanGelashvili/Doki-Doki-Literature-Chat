package ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ChatService {

    /**
     * Connection
     */

    @GET("connect")
    fun initiateConnection(): Call<Void>

    /**
     * Verification
     */

    @Headers("Content-Type: application/json")
    @POST("verify")
    fun verifyUser(@Body loginEntity: UserLoginEntity): Call<UserIdEntity>

    /**
     * Chats
     */

    @Headers("Content-Type: application/json")
    @POST("chats")
    fun fetchChatList(userIdEntity: UserIdEntity): Call<List<ChatPresentingEntity>>

    @Headers("Content-Type: application/json")
    @POST("deleteChat")
    fun deleteChat(chatDeleteEntity: ChatDeleteEntity): Call<Void>

    @Headers("Content-Type: application/json")
    @POST("insertChat")
    fun insertChat(chatInsertEntity: ChatInsertEntity): Call<ChatIdEntity>

    /**
     * Messages
     */

    @Headers("Content-Type: application/json")
    @POST("insertMessage")
    fun sendMessage(messageEntity: MessageEntity): Call<Void>

    @Headers("Content-Type: application/json")
    @POST("fetchMessages")
    fun fetchMessageList(chatIdEntity: ChatIdEntity): Call<List<MessagePresentingEntity>>
}