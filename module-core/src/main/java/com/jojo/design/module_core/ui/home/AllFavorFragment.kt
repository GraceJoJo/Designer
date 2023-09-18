package com.jojo.design.module_core.ui.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.dagger.mvp.BaseFragment
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_core.R
import com.jojo.design.module_core.adapter.ADA_PersonLike
import com.jojo.design.module_core.bean.*
import com.jojo.design.module_core.dagger2.DaggerCoreComponent
import com.jojo.design.module_core.mvp.contract.ShoppingContract
import com.jojo.design.module_core.mvp.model.ShoppingModel
import com.jojo.design.module_core.mvp.presenter.ShoppingPresenter
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.common_recyclcerview.*

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2018/12/20 5:54 PM
 * desc   : 逛-底部-大家喜欢
 */
class AllFavorFragment : BaseFragment<ShoppingPresenter, ShoppingModel>(), ShoppingContract.View {
    private var mAdapter: ADA_PersonLike? = null
    override fun getContentViewLayoutId(): Int = R.layout.fra_all_favor
    override fun onFirstUserVisible() {
    }

    override fun onFirstUserInvisible() {
    }

    override fun onUserVisible() {
    }

    override fun onUserInvisible() {
    }

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
        DaggerCoreComponent.builder().applicationComponent(BaseAppliction.mApplicationComponent).build().inject(this)
    }

    override fun startFragmentEvents() {
        mPresenter?.getPersonLike()

        mAdapter = ADA_PersonLike(mContext)
        recyclerview.layoutManager =
            LinearLayoutManager(mContext)
        recyclerview.adapter = mAdapter
    }

    override fun getCategoryList(dataList: List<CategoryEntity>) {
    }

    override fun getGoodsList(dataList: List<GoodsEntity>) {
    }

    /**
     * 精选
     */
    override fun getHandPickedGoods(bean: RecordsEntity) {

    }

    /**
     * 大家喜欢
     */
    override fun getPersonLike(dataList: List<AllfaverEntity>) {
        mAdapter?.update(dataList[0].list, true)
    }

}