# AndroidConfig
>使用kotlin基于委托封装SharePreference
* 使用
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