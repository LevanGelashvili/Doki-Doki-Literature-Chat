package ge.mudamtqveny.dokidokiliteraturechat.server.core.gateways

interface RunnableGateway {
    val isRunning: Boolean
    fun run()
    fun halt()
}