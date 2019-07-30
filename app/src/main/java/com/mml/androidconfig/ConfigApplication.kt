package com.mml.androidconfig

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.mml.topwho.ConstantString

@SuppressLint("StaticFieldLeak")
object ConfigApplication : Application() {
    @JvmStatic
    var instances: Application? = null
    get() {
        if (field != null)
            return field
        else
            throw Exception(ConstantString.RUNTIME_ERROR_APPLICATION_IS_NULL)
    }
    @JvmStatic
    var sContext: Context? = null
        get() {
            if (field != null)
                return field
            else
                throw Exception(ConstantString.RUNTIME_ERROR_APPLICATION_CONTEXT_IS_NULL)
        }

    /**
     * Initialize to make Config ready to work. If you didn't configure ConfigApplication
     * in the AndroidManifest.xml, make sure you call this method as soon as possible. In
     * Application's onCreate() method will be fine.
     *
     * @param application
     * Application.
     */
    @JvmStatic
    fun initialize(application: Application) {
        instances = application
    }

    /**
     * Initialize to make Config ready to work. If you didn't configure ConfigApplication
     * in the AndroidManifest.xml, make sure you call this method as soon as possible. In
     * Application's onCreate() method will be fine.
     *
     * @param context
     * Application context.
     */
    @JvmStatic
    fun initialize(context: Context) {
        sContext = context
    }

    override fun onCreate() {
        super.onCreate()
        instances=this
        sContext=applicationContext
    }

}