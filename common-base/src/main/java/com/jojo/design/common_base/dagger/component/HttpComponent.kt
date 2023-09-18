package com.will.weiyuekotlin.component

import androidx.appcompat.app.AppCompatActivity
import dagger.Component


/**
 * desc: .
 * author: Will .
 * date: 2017/9/2 .
 */
@Component(dependencies = [(ApplicationComponent::class)])
interface HttpComponent {

    fun inject(activity: AppCompatActivity)

}
