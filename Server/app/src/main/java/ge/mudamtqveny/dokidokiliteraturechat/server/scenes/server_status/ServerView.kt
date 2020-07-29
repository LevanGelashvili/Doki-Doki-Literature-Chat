package ge.mudamtqveny.dokidokiliteraturechat.server.scenes.server_status

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ge.mudamtqveny.dokidokiliteraturechat.server.R

interface ServerViewing {
    fun serverStateChanged()
}

class ServerView : AppCompatActivity(), ServerViewing {

    lateinit var presenter: ServerPresenting

    private lateinit var button: Button
    private lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.server_status_activity)

        ServerConfigurator(this).configure()

        text = findViewById(R.id.server_text)

        button = findViewById(R.id.server_button)
        button.setOnClickListener {
            serverStateChanged()
        }
    }

    override fun serverStateChanged() {
        if (presenter.changeServerState()) {
            button.setText(R.string.stop_server)
            text.setText(R.string.server_up)
        } else {
            button.setText(R.string.start_server)
            text.setText(R.string.server_down)
        }
    }
}