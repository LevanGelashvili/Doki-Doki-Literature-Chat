package ge.mudamtqveny.dokidokiliteraturechat.server.scenes.server_status

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ge.mudamtqveny.dokidokiliteraturechat.server.R

interface ServerViewing {
    fun setDescription(description: String)
    fun setServerTogglerButtonText(text: String)
}

class ServerView: AppCompatActivity(), ServerViewing {

    companion object {
        lateinit var context: Context
    }

    lateinit var presenter: ServerPresenting

    private lateinit var descriptionTextView: TextView
    private lateinit var serverTogglerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.server_status_activity)
        context = applicationContext

        ServerConfigurator(this).configure()

        descriptionTextView = findViewById(R.id.server_text)
        serverTogglerButton = findViewById(R.id.server_button)

        serverTogglerButton.setOnClickListener {
            presenter.handleServerTogglerButtonClicked()
        }

        presenter.handleViewDidLoad()
    }

    override fun setDescription(description: String) {
        descriptionTextView.text = description
    }

    override fun setServerTogglerButtonText(text: String) {
        serverTogglerButton.text = text
    }
}