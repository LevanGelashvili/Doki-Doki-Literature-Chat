package ge.mudamtqveny.dokidokiliteraturechat.server.scenes.server_status

import ge.mudamtqveny.dokidokiliteraturechat.server.network.HttpServer

class ServerConfigurator(private val view: ServerView) {

    fun configure() {
        val presenter = ServerPresenter()
        val server = HttpServer()

        presenter.server = server
        view.presenter = presenter
    }
}