# AndroidConfig
>使用kotlin基于委托封装SharePreference
* 使用方式
 + 第一种，继承方式
    ```kotlin
    //继承 com.mml.easyconfig.config.Config ,实现属性spName的赋值.为空则为默认sharePreference对象
      object Config: Config() {
        override val spName: String
            get() = "NIHAO"
        //定义属性 
        var name by string(key = ConfigType.STRING)
        var users by json<User?>(null)
    }
     //使用
             Config.name="nihao"
            test.text=Config.name
    ```
 + 第二种，直接使用
 ```kotlin
 //定义
  var aa by SharedPreferenceDelegate(spName = "test",default = 0)
  var ss by SharedPreferenceDelegate.json<User?>(null,"asad")
 //使用
 aa="sss"
 ss=User("name")
 ```