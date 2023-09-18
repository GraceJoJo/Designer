package com.jojo.design.common_base.dagger.mvp

import androidx.databinding.ViewDataBinding
import androidx.annotation.Nullable
import com.jojo.design.common_base.BaseAppliction
import javax.inject.Inject

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/5 3:51 PM
 *    desc   : Dagger2-MVP-BaseFragment
 */
abstract class BaseFragment<P : BaseContract.BasePresenter, M : BaseContract.BaseModel> : BaseLazyFragment() {
    @Nullable
    @Inject
    @JvmField
    var mPresenter: P? = null
    @Nullable
    @Inject
    @JvmField
    var mModel: M? = null


    override fun startEvents() {
        initDaggerInject(BaseAppliction.mApplicationComponent)
        mPresenter?.attachViewModel(this, mModel!!)
        startFragmentEvents()
    }

    abstract fun startFragmentEvents()
}