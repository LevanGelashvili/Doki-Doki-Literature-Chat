package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself

class IntroduceConfigurator(private val view: IntroduceView) {

    fun configure() {
        val router = IntroduceRouter(view)
        val presenter = IntroducePresenter(router)

        view.presenter = presenter
    }
}