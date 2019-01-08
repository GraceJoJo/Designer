package cn.foretree.db.star

import io.reactivex.Flowable
import io.reactivex.FlowableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/1/8 17:24 PM
 *    desc   : RxJava2Helper（Room）
 */
object RxJava2Helper {
    @JvmStatic
    fun <T> getFlowable(method: () -> T): Flowable<T> {
        return object : Flowable<T>() {
            override fun subscribeActual(s: Subscriber<in T>) {
                try {
                    s.onNext(method.invoke())
                } catch (e: Exception) {
                    e.printStackTrace()
                    s.onError(e)
                }
                s.onComplete()
            }
        }.compose(io2main<T>())
    }

    @JvmStatic
    fun <T> io2main(): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream ->
            upstream
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }
}