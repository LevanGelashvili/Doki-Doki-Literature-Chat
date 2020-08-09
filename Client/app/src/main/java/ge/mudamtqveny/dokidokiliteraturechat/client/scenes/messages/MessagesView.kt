package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.mudamtqveny.dokidokiliteraturechat.client.R
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.components.MessageAdapter
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.viewmodels.MessageViewModel

interface MessagesViewing {
    fun goBack()
    fun messageTyped()
    fun displayNewlyTypedMessage()
    fun messageListUpdated()
}

class MessagesView: Fragment(), MessagesViewing {

    lateinit var presenter: MessagesPresenting
    private lateinit var messageEditText: EditText
    private lateinit var messageAdapter: MessageAdapter

    private lateinit var sendButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MessagesConfigurator(this).configure()
        messageAdapter = MessageAdapter(presenter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.messages_fragment, container, false)

        initEditText(view)
        initRecycler(view)

        sendButton = view.findViewById(R.id.send_button)
        sendButton.setOnClickListener {
            messageTyped()
        }

        return view
    }

    override fun goBack() {
        presenter.goBackToChats()
    }

    override fun messageTyped() {
        val viewModel = MessageViewModel(messageEditText.text.toString(), System.currentTimeMillis())
        presenter.sendMessage(viewModel)
    }

    override fun displayNewlyTypedMessage() {
        messageAdapter.requestNewlyTypedMessage()
    }

    override fun messageListUpdated() {
        messageAdapter.notifyDataSetChanged()
    }

    private fun initRecycler(view: View) {
        val messageRecycler: RecyclerView = view.findViewById(R.id.message_recyclerview)
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
            setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.transparent_divider)!!)
        }

        messageRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = messageAdapter
            addItemDecoration(divider)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initEditText(view: View) {
        messageEditText = view.findViewById(R.id.message_editText)

        /*messageEditText.setOnTouchListener { _: View, event: MotionEvent ->
            val rightDrawable = 2
            if(event.action == MotionEvent.ACTION_UP) {
                if(event.rawX >= (messageEditText.right - messageEditText.compoundDrawables[rightDrawable].bounds.width())) {
                    messageTyped()
                    true;
                }
            }
            false;
        }*/
    }
}