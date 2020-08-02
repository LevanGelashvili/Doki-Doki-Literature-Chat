package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways.localserver.server.entities

data class ChatEntity (

    var id: Long?
    //TODO

)

data class UserEntity (
    var id: Long?,
    val nickname: String,
    val job: String,
    val picture: String
    //TODO: CHATS
) {

}

data class MessageEntity (

    var id: Long?
    // TODO
)

/** Entities sent from client*/

data class IntroduceUserViewModel (
    val nickname: String,
    val job: String,
    val picture: String
)

