package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.check_connection

import androidx.navigation.fragment.findNavController
import ge.mudamtqveny.dokidokiliteraturechat.client.R

interface ConnectionRouting {
    fun navigateToIntroduce()
}

class ConnectionRouter(private val view: ConnectionView): ConnectionRouting {

    override fun navigateToIntroduce() {
        view.findNavController().navigate(R.id.navigateToIntroduce)
    }

}