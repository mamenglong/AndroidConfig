package com.mml.easyconfig.config

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.mml.easyconfig.ConfigApplication
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
class SharedPreferenceDelegates(spName: String = "") {
    var preferences: SharedPreferences = if (spName.isEmpty()) {
        PreferenceManager.getDefaultSharedPreferences(ConfigApplication.sContext)
    } else {
        ConfigApplication.sContext!!.getSharedPreferences(spName, Context.MODE_PRIVATE)
    }
    /**
     * 删除全部数据
     */
    fun clearPreference(){
        preferences.edit().clear().apply()
    }

    /**
     * 根据key删除存储数据
     */
    fun clearPreference(key : String){
        preferences.edit().remove(key).apply()
    }
    fun int(defaultValue: Int = 0) = object : ReadWriteProperty<Any, Int> {

        override fun getValue(thisRef: Any, property: KProperty<*>): Int {
            return  preferences.getInt(property.name, defaultValue)
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
             preferences.edit().putInt(property.name, value).apply()
        }
    }

    fun long(defaultValue: Long = 0L) = object : ReadWriteProperty<Any, Long> {

        override fun getValue(thisRef: Any, property: KProperty<*>): Long {
            return preferences.getLong(property.name, defaultValue)
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Long) {
            preferences.edit().putLong(property.name, value).apply()
        }
    }

    fun double(defaultValue: Double = 0.0) = object : ReadWriteProperty<Any, Double> {

        override fun getValue(thisRef: Any, property: KProperty<*>): Double {
            return preferences.getString(property.name, defaultValue.toString())?.toDouble()?:defaultValue
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Double) {
            preferences.edit().putString(property.name, value.toString()).apply()
        }
    }
    fun boolean(defaultValue: Boolean = false) = object : ReadWriteProperty<Any, Boolean> {
        override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
            return preferences.getBoolean(property.name, defaultValue)
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
            preferences.edit().putBoolean(property.name, value).apply()
        }
    }

    fun float(defaultValue: Float = 0.0f) = object : ReadWriteProperty<Any, Float> {
        override fun getValue(thisRef: Any, property: KProperty<*>): Float {
            return preferences.getFloat(property.name, defaultValue)
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Float) {
            preferences.edit().putFloat(property.name, value).apply()
        }
    }

    fun string(defaultValue: String? = null) = object : ReadWriteProperty<Any, String?> {
        override fun getValue(thisRef: Any, property: KProperty<*>): String? {
            return preferences.getString(property.name, defaultValue)
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) {
            preferences.edit().putString(property.name, value).apply()
        }
    }

    fun setString(defaultValue: Set<String>? = null) =
        object : ReadWriteProperty<Any, Set<String>?> {
            override fun getValue(thisRef: Any, property: KProperty<*>): Set<String>? {
                return preferences.getStringSet(property.name, defaultValue)
            }

            override fun setValue(thisRef: Any, property: KProperty<*>, value: Set<String>?) {
                preferences.edit().putStringSet(property.name, value).apply()
            }
        }

    fun Any(defaultValue: String? = null) =
        object : ReadWriteProperty<Any, Any?> {
            override fun getValue(thisRef: Any, property: KProperty<*>): Any? {
                val str = preferences.getString(property.name, defaultValue)
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

            override fun setValue(thisRef: Any, property: KProperty<*>, any: Any?) {
                val byteArrayOutputStream = ByteArrayOutputStream()
                val objectOutputStream = ObjectOutputStream(
                    byteArrayOutputStream
                )
                objectOutputStream.writeObject(any)
                var serStr = byteArrayOutputStream.toString("ISO-8859-1")
                serStr = java.net.URLEncoder.encode(serStr, "UTF-8")
                objectOutputStream.close()
                byteArrayOutputStream.close()
                preferences.edit().putString(property.name, serStr).apply()
            }
        }
}