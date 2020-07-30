
package ge.mudamtqveny.dokidokiliteraturechat.client.core.gateways

interface ConnectionGateway {
    fun connect(completionHandler: (Boolean) -> (Unit))
}

interface LoginUserGateway {

}
