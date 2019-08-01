package com.mml.androidconfig

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mml.easyconfig.ConfigApplication
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Config.name="nihao"
        test.text=Config.name
        Config.user=User("4444")
        test.text=test.text.toString()+Config.user.toString()
    }
}
