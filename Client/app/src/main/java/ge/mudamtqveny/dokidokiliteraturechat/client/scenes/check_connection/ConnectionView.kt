package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.check_connection

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ge.mudamtqveny.dokidokiliteraturechat.client.R

interface ConnectionViewing

class ConnectionView: Fragment(), ConnectionViewing {

    lateinit var presenter: ConnectionPresenting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ConnectionConfigurator(this).configure()
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.connection_fragment, container, false)
    }
}