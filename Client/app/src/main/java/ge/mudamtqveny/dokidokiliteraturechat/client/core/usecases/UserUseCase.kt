
package ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserSearchEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.UserGateway

interface UserListUseCase {
    fun fetchUsersSatisfying(searchEntity: UserSearchEntity, completionHandler: (List<UserEntity>) -> Unit)
}

class UserUseCase(private val gateway: UserGateway): UserListUseCase {

    override fun fetchUsersSatisfying(searchEntity: UserSearchEntity, completionHandler: (List<UserEntity>) -> Unit) {
        gateway.fetchUsersSatisfying(searchEntity, completionHandler)
    }
}
