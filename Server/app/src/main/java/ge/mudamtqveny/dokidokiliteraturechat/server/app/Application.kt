package ge.mudamtqveny.dokidokiliteraturechat.server.app

import android.app.Application
import android.content.Context

class Application : Application() {

    companion object {
        var context: Context? = null
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
