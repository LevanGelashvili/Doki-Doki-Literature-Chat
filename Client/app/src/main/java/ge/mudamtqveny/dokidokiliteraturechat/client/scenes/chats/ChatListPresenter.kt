
package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.chats

import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.ChatListingUseCase

interface ChatListPresenting {

}

class ChatListPresenter (

    private val view: ChatListViewing,
    private val parameters: ChatListParameters,
    private val chatListUseCase: ChatListingUseCase,
    private val router: ChatListRouting

): ChatListPresenting {

}
