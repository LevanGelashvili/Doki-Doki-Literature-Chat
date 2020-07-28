package ge.mudamtqveny.dokidokiliteraturechat.server.scenes.server_status

import ge.mudamtqveny.dokidokiliteraturechat.server.network.HttpServer

interface ServerPresenting {

    /*
        Returns current server state
        True is server is up, false if server is down
     */
    fun changeServerState(): Boolean
}

class ServerPresenter: ServerPresenting {

    private var serverRunning: Boolean = false
    lateinit var server: HttpServer

    override fun changeServerState(): Boolean {

        if (serverRunning)
            stopServer()
        else
            startServer()

        serverRunning = !serverRunning
        return serverRunning
    }

    private fun startServer() {
        TODO("Not yet implemented")
    }

    private fun stopServer() {
        TODO("Not yet implemented")
    }
}