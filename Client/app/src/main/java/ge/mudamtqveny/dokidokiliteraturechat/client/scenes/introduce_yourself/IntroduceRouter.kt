package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself

import android.util.Log
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserIdEntity

interface IntroduceRouting {
    fun navigateToChatList(userId: UserIdEntity)
}

class IntroduceRouter(private val view: IntroduceView): IntroduceRouting {

    override fun navigateToChatList(userId: UserIdEntity) {
        Log.d("butter_knife", userId.toString())
    }
}