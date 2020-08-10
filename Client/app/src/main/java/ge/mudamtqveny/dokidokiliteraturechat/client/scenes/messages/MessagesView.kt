package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.mudamtqveny.dokidokiliteraturechat.client.R
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.components.MessageAdapter
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.messages.viewmodels.MessageViewModel
import kotlin.math.max


interface MessagesViewing {
    fun leaveMessageView()
    fun messageTyped()
    fun displayNewlyTypedMessage()
    fun messageListUpdated()
}

class MessagesView: Fragment(), MessagesViewing {

    lateinit var presenter: MessagesPresenting
    private lateinit var messageEditText: EditText
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageRecycler: RecyclerView

    private lateinit var backImage: ImageView
    private lateinit var userImage: ImageView
    private lateinit var userName: TextView
    private lateinit var userJob: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MessagesConfigurator(this).configure()

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                leaveMessageView()
            }
        })

        messageAdapter = MessageAdapter(presenter)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.messages_fragment, container, false)

        initEditText(view)
        initRecycler(view)
        initUserFields(view)

        return view
    }

    override fun leaveMessageView() {
        presenter.goBackToChats()
    }

    override fun messageTyped() {
        val viewModel = MessageViewModel(messageEditText.text.toString(), System.currentTimeMillis())
        messageEditText.setText("")
        presenter.sendMessage(viewModel)
    }

    override fun displayNewlyTypedMessage() {
        messageAdapter.requestNewlyTypedMessage()
    }

    override fun messageListUpdated() {
        messageAdapter.notifyDataSetChanged()
    }

    private fun initRecycler(view: View) {

        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
            setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.transparent_divider)!!)
        }

        messageRecycler = view.findViewById(R.id.message_recyclerview)
        messageRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = messageAdapter
            addItemDecoration(divider)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initEditText(view: View) {
        messageEditText = view.findViewById(R.id.message_editText)

        messageEditText.setOnTouchListener { _: View, event: MotionEvent ->
            val rightDrawable = 2
            val widthCoefficient = 1.75
            if(event.action == MotionEvent.ACTION_UP) {
                if(event.rawX >= (messageEditText.right - messageEditText.compoundDrawables[rightDrawable].bounds.width() * widthCoefficient)) {
                    messageTyped()
                }
            }
            false;
        }
    }

    private fun initUserFields(view: View) {

        val viewModel = presenter.getToolbarViewModel()

        backImage = view.findViewById(R.id.expanded_back_image)
        backImage.setOnClickListener {
            leaveMessageView()
        }

        userImage = view.findViewById(R.id.expanded_circle_image)
        userImage.setImageBitmap(viewModel.bitmap)

        userName = view.findViewById(R.id.expanded_name)
        userName.text = viewModel.name

        userJob = view.findViewById(R.id.expanded_job)
        userJob.text = viewModel.job
    }
}