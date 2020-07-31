package ge.mudamtqveny.dokidokiliteraturechat.client.scenes.introduce_yourself

interface IntroducePresenting {
    fun verifyUser()
}

class IntroducePresenter(private val router: IntroduceRouting): IntroducePresenting {

    override fun verifyUser() {
        TODO("Not yet implemented")
    }

}