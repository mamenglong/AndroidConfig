package com.mml.androidconfig.config

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.mml.androidconfig.ConfigApplication
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Author: Menglong Ma
 * Email: mml2015@126.com
 * Date: 19-7-30 下午4:46
 * Description: This is SharedPreferenceDelegates
 * Package: com.mml.androidconfig.config
 * Project: AndroidConfig
 */
object SharedPreferenceDelegates {
    var spName: String = ""
    var preferences: SharedPreferences = if (spName.isEmpty()) {
        PreferenceManager.getDefaultSharedPreferences(ConfigApplication.sContext)
    } else {
        ConfigApplication.sContext!!.getSharedPreferences(spName, Context.MODE_PRIVATE)
    }

    fun int(defaultValue: Int = 0) = object : ReadWriteProperty<SharedPreferenceDelegates, Int> {

        override fun getValue(thisRef: SharedPreferenceDelegates, property: KProperty<*>): Int {
            return thisRef.preferences.getInt(property.name, defaultValue)
        }

        override fun setValue(thisRef: SharedPreferenceDelegates, property: KProperty<*>, value: Int) {
            thisRef.preferences.edit().putInt(property.name, value).apply()
        }
    }

    fun long(defaultValue: Long = 0L) = object : ReadWriteProperty<SharedPreferenceDelegates, Long> {

        override fun getValue(thisRef: SharedPreferenceDelegates, property: KProperty<*>): Long {
            return thisRef.preferences.getLong(property.name, defaultValue)
        }

        override fun setValue(thisRef: SharedPreferenceDelegates, property: KProperty<*>, value: Long) {
            thisRef.preferences.edit().putLong(property.name, value).apply()
        }
    }

    fun boolean(defaultValue: Boolean = false) = object : ReadWriteProperty<SharedPreferenceDelegates, Boolean> {
        override fun getValue(thisRef: SharedPreferenceDelegates, property: KProperty<*>): Boolean {
            return thisRef.preferences.getBoolean(property.name, defaultValue)
        }

        override fun setValue(thisRef: SharedPreferenceDelegates, property: KProperty<*>, value: Boolean) {
            thisRef.preferences.edit().putBoolean(property.name, value).apply()
        }
    }

    fun float(defaultValue: Float = 0.0f) = object : ReadWriteProperty<SharedPreferenceDelegates, Float> {
        override fun getValue(thisRef: SharedPreferenceDelegates, property: KProperty<*>): Float {
            return thisRef.preferences.getFloat(property.name, defaultValue)
        }

        override fun setValue(thisRef: SharedPreferenceDelegates, property: KProperty<*>, value: Float) {
            thisRef.preferences.edit().putFloat(property.name, value).apply()
        }
    }

    fun string(defaultValue: String? = null) = object : ReadWriteProperty<SharedPreferenceDelegates, String?> {
        override fun getValue(thisRef: SharedPreferenceDelegates, property: KProperty<*>): String? {
            return thisRef.preferences.getString(property.name, defaultValue)
        }

        override fun setValue(thisRef: SharedPreferenceDelegates, property: KProperty<*>, value: String?) {
            thisRef.preferences.edit().putString(property.name, value).apply()
        }
    }

    fun setString(defaultValue: Set<String>? = null) =
        object : ReadWriteProperty<SharedPreferenceDelegates, Set<String>?> {
            override fun getValue(thisRef: SharedPreferenceDelegates, property: KProperty<*>): Set<String>? {
                return thisRef.preferences.getStringSet(property.name, defaultValue)
            }

            override fun setValue(thisRef: SharedPreferenceDelegates, property: KProperty<*>, value: Set<String>?) {
                thisRef.preferences.edit().putStringSet(property.name, value).apply()
            }
        }

    fun Any(defaultValue: String? = null) =
        object : ReadWriteProperty<SharedPreferenceDelegates, Any?> {
            override fun getValue(thisRef: SharedPreferenceDelegates, property: KProperty<*>): Any? {
                val str = thisRef.preferences.getString(property.name, defaultValue)
                val redStr = java.net.URLDecoder.decode(str, "UTF-8")
                val byteArrayInputStream = ByteArrayInputStream(
                    redStr.toByteArray(charset("ISO-8859-1"))
                )
                val objectInputStream = ObjectInputStream(
                    byteArrayInputStream
                )
                val obj = objectInputStream.readObject()
                objectInputStream.close()
                byteArrayInputStream.close()
                return obj
            }

            override fun setValue(thisRef: SharedPreferenceDelegates, property: KProperty<*>, any: Any?) {
                val byteArrayOutputStream = ByteArrayOutputStream()
                val objectOutputStream = ObjectOutputStream(
                    byteArrayOutputStream
                )
                objectOutputStream.writeObject(any)
                var serStr = byteArrayOutputStream.toString("ISO-8859-1")
                serStr = java.net.URLEncoder.encode(serStr, "UTF-8")
                objectOutputStream.close()
                byteArrayOutputStream.close()
                thisRef.preferences.edit().putString(property.name, serStr).apply()
            }
        }
}