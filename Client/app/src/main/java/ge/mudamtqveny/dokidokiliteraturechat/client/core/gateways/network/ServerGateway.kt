package ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network

import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.ConnectionGateway
import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.LoginUserGateway
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

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

    /** LoginUserGateway */

    // TODO: Login

    private val getClient: ChatService
        get() {

            val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

            val client : OkHttpClient = OkHttpClient.Builder().apply {
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