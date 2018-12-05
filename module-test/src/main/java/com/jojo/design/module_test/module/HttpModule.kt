package com.will.weiyuekotlin.module

import dagger.Module


/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/11/30 9:16 PM
 *    desc   :
 */
@Module
class HttpModule {

//    @Provides
//    internal fun provideOkHttpClient(): OkHttpClient.Builder {
//        // 指定缓存路径,缓存大小100Mb
//        val cache = Cache(File(MyApp.instance.cacheDir, "HttpCache"),
//                (1024 * 1024 * 100).toLong())
//        return OkHttpClient().newBuilder().cache(cache)
//                .retryOnConnectionFailure(true)
//                .addInterceptor(RetrofitConfig.sLoggingInterceptor)
//                .addInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
//                .addNetworkInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
//                .connectTimeout(10, TimeUnit.SECONDS)
//    }
//
//    @Provides
//    internal fun provideNetEaseApis(builder: OkHttpClient.Builder): NewsApi {
//        builder.addInterceptor(RetrofitConfig.sQueryParameterInterceptor)
//
//        val retrofitBuilder = Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(builder.build())
//
//        return NewsApi.getInstance(retrofitBuilder
//                .baseUrl(ApiConstants.sIFengApi)
//                .build().create(NewsApiService::class.java))
//    }
//
//    @Provides
//    internal fun provideJanDanApis(builder: OkHttpClient.Builder): JanDanApi {
//
//        val retrofitBuilder = Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(builder.build())
//
//        return JanDanApi.getInstance(retrofitBuilder
//                .baseUrl(ApiConstants.sJanDanApi)
//                .build().create(JanDanApiService::class.java))
//    }

}
