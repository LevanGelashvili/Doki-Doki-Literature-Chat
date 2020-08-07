package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.mudamtqveny.dokidokiliteraturechat.client.R
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.components.MessageAdapter

interface MessagesViewing {
    fun goBack()
    fun messageTyped()
}

class MessagesView: Fragment(), MessagesViewing {

    lateinit var presenter: MessagesPresenting
    lateinit var messageEditText: EditText
    lateinit var messageRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MessagesConfigurator(this).configure()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.messages_fragment, container, false)

        messageEditText = view.findViewById(R.id.message_editText)
        // TODO: DRAWABLE LISTENER

        initRecycler(view)

        return view
    }

    private fun initRecycler(view: View) {
        messageRecycler = view.findViewById(R.id.message_recyclerview)
        messageRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = MessageAdapter(presenter)
        }
    }

    override fun goBack() {
        presenter.goBackToChats()
    }

    override fun messageTyped() {
        presenter.sendMessage()
    }
}