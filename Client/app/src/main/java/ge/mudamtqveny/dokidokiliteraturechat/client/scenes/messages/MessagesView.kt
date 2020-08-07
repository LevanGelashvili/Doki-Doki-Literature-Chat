package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ge.mudamtqveny.dokidokiliteraturechat.client.R

interface MessagesViewing {
    fun goBack()
    fun messageTyped()
}

class MessagesView: Fragment(), MessagesViewing {

    lateinit var presenter: MessagesPresenting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MessagesConfigurator(this).configure()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.messages_fragment, container, false)
        return view
    }

    override fun goBack() {
        presenter.goBackToChats()
    }

    override fun messageTyped() {
        presenter.sendMessage()
    }
}