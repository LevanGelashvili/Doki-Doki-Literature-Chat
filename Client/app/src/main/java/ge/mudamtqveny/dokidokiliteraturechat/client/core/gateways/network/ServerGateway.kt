package ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserIdEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserLoginEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.ConnectionGateway
import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.LoginUserGateway
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ServerGateway: ConnectionGateway, LoginUserGateway {

    private val endPoint = "http://localhost:8080/"

    companion object {
        private val instance = ServerGateway()

        @Synchronized fun getInstance(): ServerGateway {
            return instance
        }
    }

    /** ConnectionGateway part */

    override fun connect(completionHandler: (Boolean) -> (Unit)) {
        CoroutineScope(Dispatchers.IO).launch {
            completionHandler (
                try {
                    getClient.initiateConnection().isSuccessful
                } catch (e: Exception) {
                    false
                }
            )
        }
    }

    /** LoginUserGateway Part */

    override fun verify(loginEntity: UserLoginEntity, completionHandler: (UserIdEntity?) -> (Unit)) {
        CoroutineScope(Dispatchers.IO).launch {
            getUserFromCall(loginEntity) { userIdEntity ->
                completionHandler(userIdEntity)
            }
        }
    }

    private suspend fun getUserFromCall(loginEntity: UserLoginEntity, completionHandler: (UserIdEntity?) -> Unit) {
        getClient.verifyUser(loginEntity).enqueue( object: Callback<UserIdEntity?> {

            override fun onFailure(call: Call<UserIdEntity?>, t: Throwable) {}

            override fun onResponse(call: Call<UserIdEntity?>, response: Response<UserIdEntity?>) {
                if (response.isSuccessful) {
                    completionHandler(response.body())
                }
            }

        })
    }

    private val getClient: ChatService get() {

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(endPoint)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ChatService::class.java)
    }
}