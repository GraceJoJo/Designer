package com.will.weiyuekotlin.component


import com.jojo.design.module_test.LoginActivity
import com.will.weiyuekotlin.module.ApplicationModule
import com.will.weiyuekotlin.module.HttpModule
import dagger.Component

/**
 * desc: .
 * author: Will .
 * date: 2017/9/2 .
 */
@Component(dependencies = [(ApplicationComponent::class)])
interface HttpComponent {

//    fun inject(videoFragment: VideoFragment)
//
//    fun inject(detailFragment: DetailFragment)
//
//    fun inject(imageBrowseActivity: ImageBrowseActivity)
//
//    fun inject(detailFragment: com.will.weiyuekotlin.ui.news.DetailFragment)
//
//    fun inject(articleReadActivity: ArticleReadActivity)
//
//    fun inject(newsFragment: NewsFragment)

    fun inject(activity: LoginActivity)

}
