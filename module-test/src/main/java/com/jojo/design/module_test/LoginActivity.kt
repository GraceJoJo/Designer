package com.jojo.design.module_test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.will.weiyuekotlin.component.DaggerHttpComponent
import javax.inject.Inject


/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/11/30 10:25 AM
 *    desc   :
 */
class LoginActivity : AppCompatActivity() {
    @Inject
    lateinit var presenter: PresenterImpl
//    @Inject
//    lateinit var helloWorld: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_login)
        //普通方式
//        LoginPresenter(this).login(UserInfo(1,"",""))


        //Dagger 2 注入
//        DaggerAppComponent
//                .builder()
//                .appModule(AppModule(this))
//                .build()
//                .inject(this)
//        presenter.login(UserInfo(1, "", ""))
//        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
//        Toast.makeText(this, helloWorld, Toast.LENGTH_SHORT).show()
//        DaggerHttpComponent.builder()
//                .applicationComponent(mApplicationComponent)
//                .build()
//                .inject(this)

        DaggerHttpComponent.builder().applicationComponent(MyApplication.mApplicationComponent).build().inject(this)

        Toast.makeText(this, presenter.getData(), Toast.LENGTH_SHORT).show()
    }

}