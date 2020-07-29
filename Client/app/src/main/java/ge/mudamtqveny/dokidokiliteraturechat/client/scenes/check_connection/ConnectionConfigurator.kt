package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.check_connection

class ConnectionConfigurator(private val view: ConnectionView) {

    fun configure() {
        val router = ConnectionRouter(view)
        val presenter = ConnectionPresenter(router)

        view.presenter = presenter
    }
}