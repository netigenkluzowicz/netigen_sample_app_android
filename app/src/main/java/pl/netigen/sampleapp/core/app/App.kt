package pl.netigen.sampleapp.core.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import pl.netigen.drumloops.rock.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return "$TAG.${element.className.substringAfterLast(".")}.${element.methodName}"
                }
            })
        }
    }

    companion object {
        const val TAG = "sampleApp"
    }
}
