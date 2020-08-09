package ge.mudamtqveny.dokidokiliteraturechat.client.utils

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
fun dateToString(date: Long): String {
    return SimpleDateFormat("HH:mm").format(date)
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