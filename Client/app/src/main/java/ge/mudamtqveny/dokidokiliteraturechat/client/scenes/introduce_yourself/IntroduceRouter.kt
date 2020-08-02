package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserEntity

interface IntroduceRouting {
    fun navigateToChatList(user: UserEntity)
}

class IntroduceRouter(private val view: IntroduceView): IntroduceRouting {

    override fun navigateToChatList(user: UserEntity) {
        TODO("Not yet implemented")
    }
}