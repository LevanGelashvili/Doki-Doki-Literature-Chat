package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database

import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.IntroduceUserViewModel
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserEntity

interface DatabaseService {

    /** */
    fun verifyUser(userViewModel: IntroduceUserViewModel): UserEntity
}