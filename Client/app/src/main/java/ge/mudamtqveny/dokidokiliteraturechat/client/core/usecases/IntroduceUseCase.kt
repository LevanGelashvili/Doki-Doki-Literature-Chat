package ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.LoginUserGateway
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself.viewmodels.IntroduceUserViewModel

interface IntroducingUseCase {

    /** Returns user entity if registered / successfully logged in server DB, else null*/
    fun verify(user: IntroduceUserViewModel, completionHandler: (UserEntity?) -> (Unit))
}

class IntroduceUseCase(private val gateway: LoginUserGateway): IntroducingUseCase {

    override fun verify(user: IntroduceUserViewModel, completionHandler: (UserEntity?) -> (Unit)) {
        return gateway.verify(user, completionHandler)
    }
}