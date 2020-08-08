package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.database

import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserIdEntity
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.UserLoginEntity
import ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities.*

interface DatabaseService {

    /**
     * Takes a user login entity and verifies it
     * Registers user if not present in database, else, updates its field
     */
    fun verifyUser(loginEntity: UserLoginEntity, completionHandler: (UserIdEntity) -> (Unit))

    /**
     * Takes UserIdEntity and
     * Returns List of ChatPresentingEntities of user
     */
    fun fetchChatList(userIdEntity: UserIdEntity, completionHandler: (List<ChatPresentingEntity>) -> Unit)

    /**
     * Takes a chat delete entity and deletes chat from database
     */
    fun deleteChat(chatDeleteEntity: ChatDeleteEntity)

    /**
     * Takes a chat insert entity (two user Id's) and inserts it into a database
     * Handles everything necessary for chat creation
     */
    fun createChat(chatInsertEntity: ChatInsertEntity, completionHandler: (ChatIdEntity) -> Unit)

    /**
     * Inserts a message into a database
     */
    fun insertMessage(messageEntity: MessageEntity)

    /**
     * Takes chat id entity and
     * Returns list of messages from that chat
     */
    fun fetchMessageList(chatIdEntity: ChatIdEntity, completionHandler: (List<MessagePresentingEntity>) -> Unit)

    /**
     * Takes chat id entity and
     * Returns list of messages from that chat that user has received, but has not seen yet
     */
    fun fetchUnseenMessageList(unseenMessageEntity: UnseenMessageEntity, completionHandler: (List<MessagePresentingEntity>) -> Unit)

    /**
     * Searches for users depending on userSearchEntity
     */
    fun searchUsers(userSearchEntity: UserSearchEntity, completionHandler: (List<UserEntity>) -> Unit)
}