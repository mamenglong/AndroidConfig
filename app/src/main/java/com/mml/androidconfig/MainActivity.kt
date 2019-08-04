package com.mml.androidconfig

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mml.easyconfig.config.SharedPreferenceDelegate
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var aa by SharedPreferenceDelegate(spName = "test",default = 0)
    var ss by SharedPreferenceDelegate.json<User?>(null,"asad")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Config.name="nihao"
        test.text=Config.name
        Config.users=User("4444")
        aa=9
        test.text=test.text.toString()+Config.users.toString()
        ss=User("34rr")
        test.text=test.text.toString()+ss.toString()
    }
}
