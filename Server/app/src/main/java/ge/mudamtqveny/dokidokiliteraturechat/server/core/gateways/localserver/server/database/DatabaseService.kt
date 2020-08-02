package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database

import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserEntity
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserLoginEntity

interface DatabaseService {

    /**
     * Takes a user login entity and verifies it
     * Registers user if not present in database, else, updates its field
     * Returns user entity on completion
     * */
    fun verifyUser(userViewModel: UserLoginEntity): UserEntity
}