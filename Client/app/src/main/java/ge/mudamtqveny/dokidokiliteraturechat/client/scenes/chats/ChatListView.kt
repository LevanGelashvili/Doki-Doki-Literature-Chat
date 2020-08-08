
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats

import android.os.Bundle
import androidx.fragment.app.Fragment

interface ChatListViewing {

}

class ChatListView: Fragment(), ChatListViewing {

    lateinit var presenter: ChatListPresenting

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ChatListConfigurator(this).configure()
    }
}
