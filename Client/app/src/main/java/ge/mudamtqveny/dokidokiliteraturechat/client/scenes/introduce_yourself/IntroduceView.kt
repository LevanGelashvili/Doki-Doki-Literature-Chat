package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.drawToBitmap
import androidx.fragment.app.Fragment
import ge.mudamtqveny.dokidokiliteraturechat.client.R
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself.viewmodels.IntroduceUserViewModel
import ge.mudamtqveny.dokidokiliteraturechat.client.utils.bitmapToBase64
import java.io.ByteArrayOutputStream


interface IntroduceViewing {
    fun attemptLogin()
    fun chooseImage()
}

class IntroduceView : Fragment(), IntroduceViewing {

    lateinit var presenter: IntroducePresenting

    private lateinit var nicknameTextField: EditText
    private lateinit var jobTextField: EditText
    private lateinit var startButton: Button
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        IntroduceConfigurator(this).configure()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.introduce_fragment, container, false)

        nicknameTextField = view.findViewById(R.id.nickname_textField)
        jobTextField = view.findViewById(R.id.what_i_do_textField)

        startButton = view.findViewById(R.id.start_button)
        startButton.setOnClickListener { attemptLogin() }

        image = view.findViewById(R.id.choose_image)
        image.setOnClickListener { chooseImage() }

        return view
    }

    override fun attemptLogin() {
        val nickname = nicknameTextField.text.toString()
        val job = jobTextField.text.toString()

        if (nickname.isEmpty() || job.isEmpty()) {
            Toast.makeText(this.context, "Fill both parameters and try again", Toast.LENGTH_LONG).show()
        } else {
            val viewModel = IntroduceUserViewModel(nickname, job, bitmapToBase64(image.drawToBitmap()))
            presenter.verifyUser(viewModel)
        }
    }

    override fun chooseImage() {
        val intent = Intent(Intent.ACTION_PICK).apply { type = "image/*" }
        startActivityForResult(intent, PICK_IMAGE)
    }

    /**
     * Rest of this file is Android logic for choosing image from gallery
     */

    companion object {
        const val PICK_IMAGE = 1000
        const val PERMISSION_CODE = 1001
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    chooseImage()
                }
                else {
                    Toast.makeText(this.context, "Permission denied", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            Toast.makeText(this.context, "Image chosen successfully!", Toast.LENGTH_LONG).show()
        }
    }
}