
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ge.mudamtqveny.dokidokiliteraturechat.client.R

interface ChatListViewing {

}

class ChatListView: Fragment(), ChatListViewing {

    lateinit var presenter: ChatListPresenting

    private lateinit var searchEditText: EditText
    private lateinit var chatsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ChatListConfigurator(this).configure()
        presenter.handleOnCreate()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val view = inflater.inflate(R.layout.chats_fragment, container, false)

        searchEditText = view.findViewById(R.id.searchEditTextText)
        chatsRecyclerView = view.findViewById(R.id.chatsRecyclerView)

        return view
    }
}
