package ge.mudamtqveny.dokidokiliteraturechat.server.scenes.server_status

import ge.mudamtqveny.dokidokiliteraturechat.server.core.usecases.ServerTogglingUseCase

interface ServerPresenting {
    fun handleViewDidLoad()
    fun handleServerTogglerButtonClicked()
    fun serverIsRunning(): Boolean
}

class ServerPresenter (

    private val view: ServerViewing,
    private val useCase: ServerTogglingUseCase

): ServerPresenting {

    private var isServerRunning = false

    override fun handleViewDidLoad() {
        view.setDescription("Server Down")
        view.setServerTogglerButtonText("Start Server")
    }

    override fun handleServerTogglerButtonClicked() {

        isServerRunning = useCase.toggleServer()

        val description: String
        val buttonText: String

        if (isServerRunning) {
            description = "Server Up"
            buttonText = "Stop Server"
        } else {
            description = "Server Down"
            buttonText = "Start Server"
        }

        view.setDescription(description)
        view.setServerTogglerButtonText(buttonText)
    }

    override fun serverIsRunning(): Boolean {
        return isServerRunning
    }
}