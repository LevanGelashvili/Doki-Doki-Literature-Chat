package ge.mudamtqveny.dokidokiliteraturechat.client.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.widget.Toast
import java.io.ByteArrayOutputStream
import java.lang.Integer.min
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun formatDate(date: Long, format: String): String {
    return SimpleDateFormat(format).format(date)
}

fun timeBetweenMessage(date: Long): String {
    val diff = System.currentTimeMillis() - date

    val hourDiff = diff / (60 * 60 * 1000)
    val minDiff = diff / (60 * 1000)
    val secDiff = diff / (1000)

    return when {
        date == 0L -> { "" }

        hourDiff >= 24 -> {
            formatDate(date, "dd/mm/yyyy")
        }
        minDiff >= 60 -> {
            "$hourDiff hour${if (hourDiff == 1L) "" else "s"}"
        }
        secDiff >= 60 -> {
            "$minDiff min"
        }
        else -> {
            "$secDiff sec"
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
    bitmap.compress(Bitmap.CompressFormat.JPEG, 25, outputStream)
    return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT)
}

/** Taken from : https://android--code.blogspot.com/2020/06/android-kotlin-bitmap-crop-square.html */
fun bitmapToSquare(bitmap: Bitmap): Bitmap {
    val side = minOf(bitmap.width, bitmap. height)
    val xOffset = (bitmap.width - side) /2
    val yOffset = (bitmap.height - side)/2
    return Bitmap.createBitmap(bitmap, xOffset, yOffset, side, side)
}