
package ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserLoginEntity

interface ConnectionGateway {
    fun connect(completionHandler: (Boolean) -> (Unit))
}

interface LoginUserGateway {
    fun verify(user: UserLoginEntity, completionHandler: (UserEntity?) -> (Unit))
}
