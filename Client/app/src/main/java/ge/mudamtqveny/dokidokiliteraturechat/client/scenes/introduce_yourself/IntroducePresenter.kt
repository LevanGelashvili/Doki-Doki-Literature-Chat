package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.IntroducingUseCase
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself.viewmodels.IntroduceUserViewModel

interface IntroducePresenting {
    fun verifyUser(introduceUserViewModel: IntroduceUserViewModel)
}

class IntroducePresenter(private val router: IntroduceRouting, private val introducingUseCase: IntroducingUseCase): IntroducePresenting {

    override fun verifyUser(user: IntroduceUserViewModel) {

        introducingUseCase.verify(user) { userEntity ->
            if (userEntity == null) {
                // TODO: Display error message
            } else {
                userVerified(userEntity)
            }

        }
    }

    private fun userVerified(user: UserEntity) {
        router.navigateToChatList(user)
    }
}