package ge.mudamtqveny.dokidokiliteraturechat.client.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.Toast
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun formatDate(date: Long, format: String): String {
    return SimpleDateFormat(format).format(date)
}

fun timeBetweenMessage(date: Long): String {
    val diff = System.currentTimeMillis() - date

    val hourDiff = diff / (60 * 60 * 1000)
    val minDiff = diff / (60 * 1000)

    return when {
        hourDiff >= 24 -> {
            formatDate(date, "dd/mm/yyyy")
        }
        minDiff >= 60 -> {
            "$hourDiff hour${if (hourDiff == 1L) "" else "s"}"
        }
        else -> {
            "$minDiff min"
        }
    }
}

fun showToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_LONG).show()
}

fun base64ToBitmap(base64String: String): Bitmap {
    val decodedString = Base64.decode(base64String, Base64.DEFAULT)
    return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
}

fun bitmapToBase64(bitmap: Bitmap): String {
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
}

/**
 *  Taken from:
 *  https://stackoverflow.com/questions/26263660/crop-image-to-square-android
 */
fun cropBitmapToSquare(bitmap: Bitmap): Bitmap {
    val width = bitmap.width
    val height = bitmap.height
    val newWidth = if (height > width) width else height
    val newHeight = if (height > width) height - (height - width) else height
    var cropW = (width - height) / 2
    cropW = if (cropW < 0) 0 else cropW
    var cropH = (height - width) / 2
    cropH = if (cropH < 0) 0 else cropH
    return Bitmap.createBitmap(bitmap, cropW, cropH, newWidth, newHeight)
}
