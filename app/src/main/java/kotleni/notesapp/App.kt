package kotleni.notesapp

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App: Application() {
    companion object {
        // fixme: leak variable
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        context = applicationContext
        super.onCreate()
    }
}