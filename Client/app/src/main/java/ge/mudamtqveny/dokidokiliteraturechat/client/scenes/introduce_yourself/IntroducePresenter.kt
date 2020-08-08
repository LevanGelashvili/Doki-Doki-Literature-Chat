package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserIdEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.IntroducingUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself.viewmodels.IntroduceUserViewModel

interface IntroducePresenting {
    fun verifyUser(introduceUserViewModel: IntroduceUserViewModel)
}

class IntroducePresenter(private val router: IntroduceRouting, private val introducingUseCase: IntroducingUseCase): IntroducePresenting {

    override fun verifyUser(introduceUserViewModel: IntroduceUserViewModel) {

        val loginEntity = introduceUserViewModel.toUserLoginEntity()

        introducingUseCase.verify(loginEntity) { userIdEntity ->
            userVerified(userIdEntity)
        }
    }

    private fun userVerified(userId: UserIdEntity) {
        router.navigateToChatList(userId)
    }
}