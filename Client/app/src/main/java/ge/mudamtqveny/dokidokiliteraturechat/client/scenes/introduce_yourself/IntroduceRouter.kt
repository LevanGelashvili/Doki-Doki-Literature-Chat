package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserIdEntity

interface IntroduceRouting {
    fun navigateToChatList(userId: UserIdEntity)
}

class IntroduceRouter(private val view: IntroduceView): IntroduceRouting {

    override fun navigateToChatList(userId: UserIdEntity) {
        TODO("Not yet implemented")
    }
}