package com.example.test.config

/**
 * Author: Menglong Ma
 * Email: mml2015@126.com
 * Date: 19-7-29 下午12:18
 * Description: This is IConfig
 * Package: com.example.test.config
 * Project: Test
 */
interface IConfig {
    fun put(key:String)
    fun get(key:String)
    fun remove(key:String)
    fun havaChanged(key:String)
}