package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself.viewmodels

import com.google.gson.annotations.SerializedName

data class IntroduceUserViewModel (
    /*@SerializedName("nickname") val nickname: String,
    @SerializedName("job")val job: String,
    @SerializedName("picture") val picture: String*/
    val nickname: String,
    val job: String,
    val picture: String
)