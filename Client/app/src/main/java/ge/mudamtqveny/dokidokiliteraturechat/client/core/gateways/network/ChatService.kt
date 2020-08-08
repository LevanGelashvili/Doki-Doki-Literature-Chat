
package ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.*
import retrofit2.Call
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
     * Users
     */

    @Headers("Content-Type: application/json")
    @POST("verify")
    fun verifyUser(@Body loginEntity: UserLoginEntity): Call<UserIdEntity>

    @Headers("Content-Type: application/json")
    @POST("users")
    fun fetchUsersSatisfying(@Body searchEntity: UserSearchEntity): Call<List<UserEntity>>

    /**
     * Chats
     */

    @Headers("Content-Type: application/json")
    @POST("chats")
    fun fetchChatList(@Body userIdEntity: UserIdEntity): Call<List<ChatPresentingEntity>>

    @Headers("Content-Type: application/json")
    @POST("deleteChat")
    fun deleteChat(@Body chatDeleteEntity: ChatDeleteEntity): Call<Void>

    @Headers("Content-Type: application/json")
    @POST("insertChat")
    fun insertChat(@Body chatInsertEntity: ChatInsertEntity): Call<ChatIdEntity>

    /**
     * Messages
     */

    @Headers("Content-Type: application/json")
    @POST("insertMessage")
    fun sendMessage(@Body messageEntity: MessageEntity): Call<Void>

    @Headers("Content-Type: application/json")
    @POST("fetchMessages")
    fun fetchMessageList(@Body chatIdEntity: ChatIdEntity): Call<List<MessagePresentingEntity>>

    @Headers("Content-Type: application/json")
    @POST("fetchUnseenMessages")
    fun fetchUnseenMessageList(@Body unseenMessageEntity: UnseenMessageEntity): Call<List<MessagePresentingEntity>>
}