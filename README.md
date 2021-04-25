# Chaperone
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

项目依赖使用
    dependencies {
        implementation 'com.github.coolsili:Chaperone:1.2.1'
        api 'androidx.lifecycle:lifecycle-extensions:2.2.0'
        //rxJava
        implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
        implementation 'io.reactivex.rxjava2:rxjava:2.2.14'
        //retrofit
        implementation 'com.squareup.retrofit2:retrofit:2.3.0'
        implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
        implementation 'com.squareup.retrofit2:adapter-rxjava2:2.3.0'
        // Okhttp
        implementation 'com.squareup.okhttp3:okhttp:4.0.1'
        implementation 'com.squareup.okhttp3:logging-interceptor:3.8.1'
    }

    android{
        dataBinding {
            enabled = true
        }
     }

增加请求封装
1.添加依赖。
2.在使用的项目中添加RetrofitHandler和ApiService，
  定义接口和使用类。
3.调用查看app中HomeViewModle.class示例。
