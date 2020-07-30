package ge.mudamtqveny.dokidokiliteraturechat.server.scenes.server_status

class ServerConfigurator(private val view: ServerView) {

    fun configure() {
        val presenter = ServerPresenter()
        view.presenter = presenter
    }
}