package com.example.test.config

import com.mml.androidconfig.config.SharedPreferenceDelegates

/**
 * Author: Menglong Ma
 * Email: mml2015@126.com
 * Date: 19-7-29 下午12:19
 * Description: This is Config
 * Package: com.example.test.config
 * Project: Test
 */
class Config:IConfig {

    var num:Int by SharedPreferenceDelegates().int(0)
    override fun get(key:String) {

    }

    override fun remove(key:String) {

    }

    override fun havaChanged(key:String) {

    }

    override fun put(key:String) {
        
    }
}