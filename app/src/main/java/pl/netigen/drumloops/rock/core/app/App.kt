package pl.netigen.drumloops.rock.core.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import pl.netigen.drumloops.rock.BuildConfig
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree());
        }
    }
}
