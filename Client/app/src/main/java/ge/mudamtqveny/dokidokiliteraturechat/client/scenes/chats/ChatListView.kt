
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.mudamtqveny.dokidokiliteraturechat.client.R
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats.components.ChatsRecyclerViewAdapter
import ge.mudamtqveny.dokidokiliteraturechat.client.utils.showToast

interface ChatListViewing {
    fun handleChatListChanged(shouldDisplayChats: Boolean)
    fun showMessage(message: String)
    val searchText: String
}

class ChatListView: Fragment(), ChatListViewing {

    lateinit var presenter: ChatListPresenting

    private lateinit var searchEditText: EditText
    private lateinit var noChatHistoryText: TextView
    private lateinit var chatsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ChatListConfigurator(this).configure()

        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                presenter.goBackToIntroduce()
            }
        })

        presenter.handleOnCreate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.chats_fragment, container, false)

        noChatHistoryText = view.findViewById(R.id.chat_history_text)
        noChatHistoryText.visibility = View.INVISIBLE

        searchEditText = view.findViewById(R.id.searchEditTextText)
        searchEditText.addTextChangedListener(object: TextWatcher {

            override fun afterTextChanged(s: Editable) {
                presenter.handleAfterTextChanged(searchText)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) { }
        })

        chatsRecyclerView = view.findViewById(R.id.chatsRecyclerView)
        chatsRecyclerView.layoutManager = LinearLayoutManager(context)
        chatsRecyclerView.adapter = ChatsRecyclerViewAdapter(presenter)
        ItemTouchHelper(object: SimpleCallback(0, ItemTouchHelper.RIGHT) {

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                presenter.handleChatCellSwipedAt(viewHolder.adapterPosition)
            }

            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean { return false }

        }).attachToRecyclerView(chatsRecyclerView)

        return view
    }

    override fun handleChatListChanged(shouldDisplayChats: Boolean) {
        if (shouldDisplayChats) {
            chatsRecyclerView.visibility = View.VISIBLE
            noChatHistoryText.visibility = View.INVISIBLE
            chatsRecyclerView.adapter?.notifyDataSetChanged()
        }
        else {
            chatsRecyclerView.visibility = View.INVISIBLE
            noChatHistoryText.visibility = View.VISIBLE
        }
    }

    override fun showMessage(message: String) {
        showToast(requireContext(), message)
    }

    override val searchText: String
        get() = searchEditText.text.toString()
}
