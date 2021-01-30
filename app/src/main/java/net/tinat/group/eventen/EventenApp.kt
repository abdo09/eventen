package net.tinat.group.eventen

import android.content.Context
import android.content.res.Configuration
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.google.firebase.FirebaseApp
import net.tinat.group.eventen.di.appModules
import net.tinat.group.eventen.di.dataModule
import net.tinat.group.eventen.di.firebaseModule
import net.tinat.group.eventen.utils.Constants
import net.tinat.group.eventen.utils.LocalizationUtil
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class EventenApp: MultiDexApplication() {

    override fun attachBaseContext(context: Context?) {
        super.attachBaseContext(context)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        //firebase initialization on the app level
        FirebaseApp.initializeApp(this)

        configureKoin()

        setDefaultLanguage()

    }

    private fun configureKoin() {
        startKoin {
            // use the Android context given there
            androidContext(this@EventenApp)
            // use AndroidLogger as Koin Logger - default Level.INFO
            androidLogger(Level.ERROR)
            // load properties from assets/koin.properties file
            androidFileProperties()

            modules(listOf(dataModule, firebaseModule, appModules))
        }
    }

    private fun setDefaultLanguage() {
        val lang = Constants().getCurrentLanguage(applicationContext)
        LocalizationUtil.setLanguage(lang, this)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        setDefaultLanguage()
        super.onConfigurationChanged(newConfig)
        setDefaultLanguage()
    }
}