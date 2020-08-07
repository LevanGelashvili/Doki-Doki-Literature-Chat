package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database

import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserIdEntity
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserLoginEntity

interface DatabaseService {

    /**
     * Takes a user login entity and verifies it
     * Registers user if not present in database, else, updates its field
     * Invokes completionHandler on succession
     * */
    fun verifyUser(loginEntity: UserLoginEntity, completionHandler: (UserIdEntity) -> (Unit))
}