
package ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself.viewmodels.IntroduceUserViewModel

interface ConnectionGateway {
    fun connect(completionHandler: (Boolean) -> (Unit))
}

interface LoginUserGateway {
    fun verify(user: IntroduceUserViewModel, completionHandler: (UserEntity?) -> (Unit))
}
