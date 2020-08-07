package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself

import ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways.network.ServerGateway
import ge.mudamtqveny.dokidokiliteraturechat.client.core.usecases.IntroduceUseCase

class IntroduceConfigurator(private val view: IntroduceView) {

    fun configure() {
        val router = IntroduceRouter(view)
        val gateway = ServerGateway()
        val introduceUseCase = IntroduceUseCase(gateway)
        val presenter = IntroducePresenter(router, introduceUseCase)

        view.presenter = presenter
    }
}