package ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserLoginEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.LoginUserGateway

interface IntroducingUseCase {

    /** Returns user entity if registered / successfully logged in server DB, else null*/
    fun verify(user: UserLoginEntity, completionHandler: (UserEntity?) -> (Unit))
}

class IntroduceUseCase(private val gateway: LoginUserGateway): IntroducingUseCase {

    override fun verify(user: UserLoginEntity, completionHandler: (UserEntity?) -> (Unit)) {
        return gateway.verify(user, completionHandler)
    }
}