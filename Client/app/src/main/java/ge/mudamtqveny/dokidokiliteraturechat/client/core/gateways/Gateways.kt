
package ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways

import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.ChatPresentingEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserIdEntity
import ge.mudamtqveny.dokidokiliteraturechat.client.core.entities.UserLoginEntity

interface ConnectionGateway {
    fun connect(completionHandler: (Boolean) -> (Unit))
}

interface LoginUserGateway {
    fun verify(loginEntity: UserLoginEntity, completionHandler: (UserIdEntity) -> (Unit))
}

interface ChatListGateway {
    fun fetchChatList(userIdEntity: UserIdEntity, completionHandler: (List<ChatPresentingEntity>) -> Unit)
}
