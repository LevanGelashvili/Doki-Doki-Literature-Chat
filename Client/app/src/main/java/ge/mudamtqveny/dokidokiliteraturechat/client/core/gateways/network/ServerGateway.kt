package ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.*
import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.*
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

        client.verifyUser(loginEntity).enqueue(object: Callback<UserIdEntity> {

            override fun onFailure(call: Call<UserIdEntity>, t: Throwable) {
                // TODO: Error Handling
            }

            override fun onResponse(call: Call<UserIdEntity>, response: Response<UserIdEntity>) {
                if (response.isSuccessful) {
                    completionHandler(response.body()!!)
                }
                // TODO: Error Handling
            }
        })
    }

    /** ChatGateway part */

    override fun deleteChat(chatDeleteEntity: ChatDeleteEntity) {
        client.deleteChat(chatDeleteEntity)
    }

    override fun fetchChatList(userIdEntity: UserIdEntity, completionHandler: (List<ChatPresentingEntity>) -> Unit) {

        client.fetchChatList(userIdEntity).enqueue(object: Callback<List<ChatPresentingEntity>> {

            override fun onFailure(call: Call<List<ChatPresentingEntity>>, t: Throwable) {
                // TODO: Error Handling
            }

            override fun onResponse(call: Call<List<ChatPresentingEntity>>, response: Response<List<ChatPresentingEntity>>) {
                if (response.isSuccessful) {
                    completionHandler(response.body()!!)
                }
                // TODO: Error Handling
            }
        })
    }

    override fun createChat(chatInsertEntity: ChatInsertEntity, completionHandler: (ChatIdEntity) -> Unit) {

        client.insertChat(chatInsertEntity).enqueue(object: Callback<ChatIdEntity> {

            override fun onFailure(call: Call<ChatIdEntity>, t: Throwable) {
                // TODO: Error Handling
            }

            override fun onResponse(call: Call<ChatIdEntity>, response: Response<ChatIdEntity>) {
                if (response.isSuccessful) {
                    completionHandler(response.body()!!)
                }
                // TODO: Error Handling
            }

        })
    }

    /** MessageGateway part */

    override fun sendMessage(messageEntity: MessageEntity) {
        client.sendMessage(messageEntity)
    }

    override fun getMessageList(chatIdEntity: ChatIdEntity, completionHandler: (List<MessagePresentingEntity>) -> Unit) {

        client.fetchMessageList(chatIdEntity).enqueue(object: Callback<List<MessagePresentingEntity>> {

            override fun onFailure(call: Call<List<MessagePresentingEntity>>, t: Throwable) {
                // TODO: Error Handling
            }

            override fun onResponse(call: Call<List<MessagePresentingEntity>>, response: Response<List<MessagePresentingEntity>>) {
                if (response.isSuccessful) {
                    completionHandler(response.body()!!)
                }
                // TODO: Error Handling
            }

        })
    }

    override fun fetchUsersSatisfying(searchEntity: UserSearchEntity, completionHandler: (List<UserEntity>) -> Unit) {

        client.fetchUsersSatisfying(searchEntity).enqueue(object: Callback<List<UserEntity>>{

            override fun onFailure(call: Call<List<UserEntity>>, t: Throwable) {
                // TODO: Error Handling
            }

            override fun onResponse(call: Call<List<UserEntity>>, response: Response<List<UserEntity>>) {
                if (response.isSuccessful) {
                    completionHandler(response.body()!!)
                }
                // TODO: Error Handling
            }
        })
    }
}