package ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network

import android.util.Log
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserLoginEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.ConnectionGateway
import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.LoginUserGateway
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself.viewmodels.IntroduceUserViewModel
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

class ServerGateway: ConnectionGateway, LoginUserGateway { // TODO: Singleton

    private val endPoint = "http://localhost:8080/"

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

    override fun verify(user: UserLoginEntity, completionHandler: (UserEntity?) -> (Unit)) {
        CoroutineScope(Dispatchers.IO).launch {
            completionHandler(getUserFromCall(user))
        }
    }

    private suspend fun getUserFromCall(user: UserLoginEntity): UserEntity? {
        var user: UserEntity? = null

        try {
            getClient.verifyUser(user).enqueue( object: Callback<UserEntity?> {

                override fun onFailure(call: Call<UserEntity?>, t: Throwable) {}

                override fun onResponse(call: Call<UserEntity?>, response: Response<UserEntity?>) {
                    if (response.isSuccessful) {
                        user = response.body()
                    }
                }

            })
        } catch (e: Exception) { }

        return user
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