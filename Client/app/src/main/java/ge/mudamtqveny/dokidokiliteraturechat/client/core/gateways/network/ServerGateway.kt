
package ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network

import android.util.Log
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.*
import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.*
import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network.callbacks.DefaultCallback
import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network.callbacks.VoidCallback
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServerGateway: ConnectionGateway, LoginUserGateway, ChatGateway, MessageGateway, UserGateway {

    companion object {

        private val client: ChatService = {

            val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

            retrofit.create(ChatService::class.java)
        }()
    }

    /** ConnectionGateway part */

    override fun connect(completionHandler: (Boolean) -> (Unit)) {

        client.initiateConnection().enqueue(object: Callback<Void> {

            override fun onFailure(call: Call<Void>, t: Throwable) {
                completionHandler(false)
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful)
                    completionHandler(true)
                else completionHandler(false)
            }
        })
    }

    /** LoginUserGateway Part */

    override fun verify(loginEntity: UserLoginEntity, completionHandler: (UserIdEntity) -> (Unit)) {
        client.verifyUser(loginEntity).enqueue(DefaultCallback(completionHandler))
    }

    /** ChatGateway part */

    override fun deleteChat(chatDeleteEntity: ChatDeleteEntity) {
        client.deleteChat(chatDeleteEntity).enqueue(VoidCallback())
    }

    override fun fetchChatList(userIdEntity: UserIdEntity, completionHandler: (List<ChatPresentingEntity>) -> Unit) {
        client.fetchChatList(userIdEntity).enqueue(DefaultCallback(completionHandler))
    }

    override fun createChat(chatInsertEntity: ChatInsertEntity, completionHandler: (ChatIdEntity) -> Unit) {
        client.insertChat(chatInsertEntity).enqueue(DefaultCallback(completionHandler))
    }

    /** MessageGateway part */

    override fun sendMessage(messageEntity: MessageEntity) {
        client.sendMessage(messageEntity).enqueue(VoidCallback())
    }

    override fun getMessageList(chatIdEntity: ChatIdEntity, completionHandler: (List<MessagePresentingEntity>) -> Unit) {
        client.fetchMessageList(chatIdEntity).enqueue(DefaultCallback(completionHandler))
    }

    override fun getUnseenMessageList(unseenMessage: UnseenMessageEntity, completionHandler: (List<MessagePresentingEntity>) -> Unit) {
        client.fetchUnseenMessageList(unseenMessage).enqueue(DefaultCallback(completionHandler))
    }

    /** UserGateway part */

    override fun fetchUsersSatisfying(searchEntity: UserSearchEntity, completionHandler: (List<UserEntity>) -> Unit) {
        client.fetchUsersSatisfying(searchEntity).enqueue(DefaultCallback(completionHandler))
    }
}
