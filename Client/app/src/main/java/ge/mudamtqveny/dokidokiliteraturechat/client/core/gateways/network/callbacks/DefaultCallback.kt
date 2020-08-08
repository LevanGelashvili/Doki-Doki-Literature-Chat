
package ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network.callbacks

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DefaultCallback<T> (

    private val completionHandler: (T) -> Unit

): Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {
        // TODO: Error Handling
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (response.isSuccessful) {
            completionHandler(response.body()!!)
        }
        // TODO: else { Error Handling }
    }
}
