package ge.mudamtqveny.dokidokiliteraturechat.server.scenes.server_status

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import ge.mudamtqveny.dokidokiliteraturechat.server.R

interface ServerViewing {
    fun serverStateChanged()
}

class ServerView: Fragment(), ServerViewing {

    lateinit var presenter: ServerPresenting
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ServerConfigurator(this).configure()
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.id.server_fragment, null)

        button = view.findViewById(R.id.server_button)
        button.setOnClickListener {
            serverStateChanged()
        }

        return view
    }

    override fun serverStateChanged() {
        if (presenter.changeServerState())
            button.setText(R.string.server_up)
        else
            button.setText(R.string.server_down)
    }
}