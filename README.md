# Designer
好设计
#####前言：
历时一个多月，利用自己的闲暇时间，终于完成了我的第一个开源项目[Designer](https://github.com/GraceJoJo/Designer) v1.0初级版本，后续将会继续开发迭代，用于学习和经验总结。项目主要是仿想去App——一个很文艺，充满设计感的电商类APP，为了丰富功能，里面还加入了仿开眼视频的模块。
#####项目截图
![项目截图Part 1.png](https://upload-images.jianshu.io/upload_images/3828835-273df966c6eab292.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)![项目截图Part 2.png](https://upload-images.jianshu.io/upload_images/3828835-3ec5bd079d0de049.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
####《一》项目简介
######1、项目初衷：
我们知道，**Kotlin**可以很大程度上提高我们编写代码的效率，而且完全兼容Java，支持lambda表达式、Null safe等，相信使用了Kotlin的朋友，都不会再想使用Java编写代码了。那么组件化呢，**组件化的优势**就更多了，特别是对于解决大型项目的迭代研发所面临的代码冗余、业务耦合、项目臃肿，资源文件大把重复等等问题帮助非常大。

######组件化的优点：
**其一**：它把项目的基础类公共部分进行单独抽离封装，有利于更好地对库的依赖进行管理，不至于随着项目的迭代而变得乱糟糟。
**其二**：将业务拆分成多个模块进行独立管理，每个业务模块都能独立运行。能单独提测，大大节省开发时间
**其三**：对项目进行业务划分，结构清晰明了，出现问题时有利于很快的进行排查错误，节省后期维护和调试的时间。

######2、项目简介
本项目采用**组件化开发+Kotlin语言**编写，页面布局全使用**ConstraintLayout**完成。网上能找到一些组件化开发的开源项目，也能找到很多Kotlin相关的开源项目，但是组件化+Kotlin结合的开源项目，还是比较少，所以我就大胆的把两者结合实践了一把，确实是遇到了不少的坑，特别是库的依赖经常报错，但是经历这个过程，自然而然获得的收获也就更大了。后续我也会把开发过程中遇到的一些问题进行汇总分享出来。

####《二》项目架构及技术要点
######1、项目架构图
![image.png](https://upload-images.jianshu.io/upload_images/3828835-dd491842f999d1a9.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

######2、项目涉及的技术要点：

1、组件化+Kotlin结合开发，如何管理第三方依赖
2、BaseActivity和BaseFragment等基类及通用布局的封装
3、MVP+Dagger 2+Retrofit+Rxjava（包括了多个BaseUrl请求的场景处理）
4、组件化开发下ARouter的运用
5、EventBus的使用
6、Google原生数据库Room的使用
7、Glide的使用（封装加载图片工具类GlideUtils，圆形、圆角图片、背景图片加载等）
8、Kotlin下使用ButterKnife
9、CommonAdapter万能适配器（包括多类型布局的运用—首页的逛模块和视频分类详情都有运用）
10、GSYVideoPlayer实现视频播放（包括全屏切换功能）
11、5.0新特性CoordinatorLayout +AppBarLayout效果实现（视频分类详情）
12、沉浸式状态栏（Activity和在Fragment中的使用及不同手机的适配）
13、DataBinding的使用
14、约束布局ConstraintLayout的使用
#####写在结尾：
[Designer](https://github.com/GraceJoJo/Designer)项目可以说得上是倾注了我蛮多心血了，每个页面和功能都当成是上线的App来做，力求做到精致和完善，其中还包括了很多自己项目开发中的经验汇总和对新技术的探索和整合，希望对各位读者有所帮助，欢迎点个star，follow，或者给个小心心，嘻嘻😝也可以分享给你更多的朋友一起学习，您的支持是我不断前进的动力。如果有任何问题，欢迎在GitHub上给我提issue或者留言。

[下载Apk体验]()

#####致谢：
[MVPArms官方快速组件化方案开源,来自5K star的信赖](https://www.jianshu.com/p/f671dd76868f)
[RxJava](https://github.com/ReactiveX/RxJava)
[Retrofit](https://github.com/square/retrofit)
[GSYVideoPlayer](https://github.com/CarGuo/GSYVideoPlayer)
[ARouter](https://github.com/alibaba/ARouter)
[Kotlin中使用Room](https://github.com/xieyangxuejun/sample-android-room)

#####声明
感谢[想去App](http://www.xiangqu.com/)和开眼App提供参考,个人使用了抓包的方式使用了其中的API，并非攻击，如构成侵权，请及时通知我删除或者修改。
