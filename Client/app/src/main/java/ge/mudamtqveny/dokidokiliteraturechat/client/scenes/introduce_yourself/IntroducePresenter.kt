package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself

import ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself.viewmodels.IntroduceUserViewModel

interface IntroducePresenting {
    fun verifyUser(introduceUserViewModel: IntroduceUserViewModel)
}

class IntroducePresenter(private val router: IntroduceRouting): IntroducePresenting {

    override fun verifyUser(userModel: IntroduceUserViewModel) {

    }

}