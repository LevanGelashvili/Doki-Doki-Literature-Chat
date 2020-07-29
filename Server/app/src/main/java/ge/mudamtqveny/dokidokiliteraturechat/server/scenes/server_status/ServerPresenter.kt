package ge.mudamtqveny.dokidokiliteraturechat.server.scenes.server_status

import ge.mudamtqveny.dokidokiliteraturechat.server.network.HttpServer

interface ServerPresenting {

    /* Returns current server state: true is server is up, false if server is down*/
    fun changeServerState(): Boolean
}

class ServerPresenter: ServerPresenting {

    private var serverRunning: Boolean = false
    lateinit var server: HttpServer

    override fun changeServerState(): Boolean {

        if (serverRunning)
            server.stop()
        else
            server.start()

        serverRunning = !serverRunning
        return serverRunning
    }
}