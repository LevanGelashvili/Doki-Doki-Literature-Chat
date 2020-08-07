package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserIdEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.IntroducingUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself.viewmodels.IntroduceUserViewModel

interface IntroducePresenting {
    fun verifyUser(introduceUserViewModel: IntroduceUserViewModel)
}

class IntroducePresenter(private val router: IntroduceRouting, private val introducingUseCase: IntroducingUseCase): IntroducePresenting {

    override fun verifyUser(userModel: IntroduceUserViewModel) {

        val loginEntity = userModel.toUserLoginEntity()

        introducingUseCase.verify(loginEntity) { userIdEntity ->
            if (userIdEntity == null) {
                // TODO: Display error message
            } else {
                userVerified(userIdEntity)
            }

        }
    }

    private fun userVerified(userId: UserIdEntity) {
        router.navigateToChatList(userId)
    }
}