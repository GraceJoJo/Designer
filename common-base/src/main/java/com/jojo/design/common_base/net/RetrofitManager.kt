package com.jojo.design.common_base.net

import android.util.Log
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.bean.ErrorBean
import com.jojo.design.common_base.utils.NetUtils
import com.smart.novel.net.BaseHttpResponse
import com.zongxueguan.naochanle_android.retrofitrx.ApiService
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import okhttp3.*
import okio.Buffer
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/2 1:49 PM
 *    desc   : RetrofitManager+Rxjava联网管理类
 */
object RetrofitManager {

    /**
     * 请求接口实例对象
     */
    private var mInstance: RetrofitManager? = null
    private val DEFAULT_TIMEOUT = 60L

    private var retrofit: Retrofit? = null
    //请求头信息
    private val HEADER_CONNECTION = "keep-alive"

    val requestService: ApiService
        get() = getRetrofit().create<ApiService>(ApiService::class.java!!)

    var userAgent = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.87 Safari/537.36"


    fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            synchronized(RetrofitManager::class.java) {
                if (retrofit == null) {
                    val mClient = OkHttpClient.Builder()
                            //添加离线缓存
//                            .cache( Cache (File(context.getExternalFilesDir("okhttpCache"), ""), 14 * 1024 * 100))
//                            .addInterceptor(CacheInterceptor ())
//                            .addNetworkInterceptor(CacheInterceptor())//必须要有，否则会返回504
                            //添加公告查询参数
                            .addInterceptor(CommonQueryParamsInterceptor())
                            .addInterceptor(HeaderInterceptor())
                            .addInterceptor(LoggingInterceptor())//添加请求拦截(可以在此处打印请求信息和响应信息)
                            .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                            .build()
                    retrofit = Retrofit.Builder()
                            .baseUrl(API.BASE_SERVER_IP)//基础URL 建议以 / 结尾
                            .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//RxJava 适配器
                            .client(mClient)
                            .build()
                }
            }
        }
        return retrofit!!
    }

    /**
     * 自定义Log日志打印
     */
    class LoggingInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            //这个chain里面包含了request和response，所以你要什么都可以从这里拿
            val request = chain.request()
            val t1 = System.nanoTime()//请求发起的时间
            val method = request.method()
            val jsonObject = JSONObject()
            if ("POST" == method || "PUT" == method) {
                if (request.body() is FormBody) {
                    val body = request.body() as FormBody?
                    if (body != null) {
                        for (i in 0 until body.size()) {
                            try {
                                jsonObject.put(body.name(i), body.encodedValue(i))
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }

                        }
                    }
                    Log.e("request", String.format("发送请求 %s on %s  %nRequestParams:%s%nMethod:%s",
                            request.url(), chain.connection(), jsonObject.toString(), request.method()))
                } else {
                    val buffer = Buffer()
                    val requestBody = request.body()
                    if (requestBody != null) {
                        request.body()!!.writeTo(buffer)
                        val body = buffer.readUtf8()
                        Log.e("request", String.format("发送请求 %s on %s  %nRequestParams:%s%nMethod:%s",
                                request.url(), chain.connection(), body, request.method()))
                    }
                }
            } else {
                Log.e("request", String.format("发送请求 %s on %s%nMethod:%s",
                        request.url(), chain.connection(), request.method()))
            }
            val response = chain.proceed(request)
            val t2 = System.nanoTime()//收到响应的时间
            val responseBody = response.peekBody((1024 * 1024).toLong())
            Log.e("request",
                    String.format("Retrofit接收响应: %s %n返回json:%s %n耗时：%.1fms",
                            response.request().url(),
                            responseBody.string(),
                            (t2 - t1) / 1e6
                    ))
            return response
        }

    }

    /**
     * 设置公共查询参数
     */
    class CommonQueryParamsInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val url = request.url().newBuilder()
//                    .addQueryParameter("key", "ecce8a3ef508f54cc1905af133f5b3a5")
//                    .addQueryParameter("t", "1543210514862")
                    .addQueryParameter("key", "21ec3bb026f41191f371ace01c2b1b1e")
                    .addQueryParameter("t", "1547618501190")
                    .build()
            return chain.proceed(request.newBuilder().url(url).build())
        }
    }

    /**
     * 添加请求头需要携带的参数
     */
    class HeaderInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val requestBuilder = request.newBuilder()
                    .addHeader("Connection", HEADER_CONNECTION)
                    .addHeader("User-Agent", userAgent)
                    .method(request.method(), request.body())
                    .build()
            return chain.proceed(requestBuilder)
        }
    }

    /**
     * 设置缓存的拦截器
     */
    class CacheInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            if (!NetUtils.isNetworkConnected(BaseAppliction.context)) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build()
            }
            val response = chain.proceed(request)
            if (NetUtils.isNetworkConnected(BaseAppliction.context)) {
                val cacheControl = request.cacheControl().toString()
                Log.e("Tag", "有网")
                return response.newBuilder().header("Cache-Control", cacheControl)
                        .removeHeader("Pragma").build()
            } else {
                Log.e("Tag", "无网")
                return response.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + "60 * 60 * 24 * 7")
                        .removeHeader("Pragma").build()
            }
        }
    }


    /**
     * Retrofit上传文件
     *
     * @param mImagePath
     * @return
     */
    fun getUploadFileRequestBody(mImagePath: String): RequestBody {
        val file = File(mImagePath)
        //构建body
        return MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", file.name, RequestBody.create(MediaType.parse("multipart/form-data"), file))
                .build()
    }

    /**
     * 建立请求:data下无嵌套json的正常情况
     */
    fun <T> doCommonRequest(observable: Observable<BaseHttpResponse<T>>, observerListener: BaseObserverListener<T>): DisposableObserver<BaseHttpResponse<T>> {

        return observable
                .compose(RxSchedulers.io_main())
                .subscribeWith(object : DisposableObserver<BaseHttpResponse<T>>() {

                    override fun onNext(result: BaseHttpResponse<T>) {
                        if (result.code === 200) {
                            observerListener.onSuccess(result.data)
                        } else {
                            val errorBean = ErrorBean()
                            errorBean.code = result.code.toString()
                            errorBean.msg = result.msg.toString()
                            observerListener.onBusinessError(errorBean)
                        }
                    }

                    override fun onError(e: Throwable) {
                        observerListener.onError(e)
                    }

                    override fun onComplete() {
                        observerListener.onComplete()
                    }
                })

    }

    /**
     * 建立请求
     */
    fun <T> doRequestOther(observable: Observable<BaseResponse<T>>, observerListener: BaseObserverListener<T>): DisposableObserver<BaseResponse<T>> {

        return observable
                .compose(RxSchedulers.io_main())
                .subscribeWith(object : DisposableObserver<BaseResponse<T>>() {

                    override fun onNext(result: BaseResponse<T>) {
                        if (result.code === 200) {
                            if (result.data?.content != null) {
                                observerListener.onSuccess(result.data?.content)
                            } else {
                                observerListener.onSuccess(result.data as T)
                            }
                        } else {
                            val errorBean = ErrorBean()
                            errorBean.code = result.code.toString()
                            errorBean.msg = result.msg.toString()
                            observerListener.onBusinessError(errorBean)
                        }
                    }

                    override fun onError(e: Throwable) {
                        observerListener.onError(e)
                    }

                    override fun onComplete() {
                        observerListener.onComplete()
                    }
                })

    }
}
