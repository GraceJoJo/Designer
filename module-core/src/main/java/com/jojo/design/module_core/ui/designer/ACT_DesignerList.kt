package com.jojo.design.module_core.ui.designer

import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.dagger.mvp.BaseActivity
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_core.R
import com.jojo.design.module_core.bean.DesignerEntity
import com.jojo.design.module_core.bean.TagCategoryEntity
import com.jojo.design.module_core.dagger2.DaggerCoreComponent
import com.jojo.design.module_core.mvp.contract.DesignerContract
import com.jojo.design.module_core.mvp.model.DesignerModel
import com.jojo.design.module_core.mvp.presenter.DesignerPresenter
import com.will.weiyuekotlin.component.ApplicationComponent
import com.jojo.design.common_base.utils.RecyclerviewHelper
import com.jojo.design.module_core.adapter.ADA_DesignerList
import com.jojo.design.module_core.constants.ARouterConfig
import com.jojo.design.module_core.constants.ARouterConstants
import kotlinx.android.synthetic.main.common_lrecyclcerview.*


/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/24 3:21 PM
 *    desc   : 设计师列表
 */
@Route(path = ARouterConfig.ACT_DESIGNERLIST)
class ACT_DesignerList : BaseActivity<DesignerPresenter, DesignerModel>(), DesignerContract.View {
    var mAdapter: ADA_DesignerList? = null

    override fun getContentViewLayoutId(): Int = R.layout.act_designer_list

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
        DaggerCoreComponent.builder().applicationComponent(BaseAppliction.mApplicationComponent).build().inject(this)
    }

    override fun startEvents() {
        val extras = intent.extras
        setHeaderTitle(extras.getString(ARouterConstants.TAG_NAME))
        mPresenter?.getDesinerList(extras.getString(ARouterConstants.TAGCATEGORY_ID), extras.getString(ARouterConstants.TAG_ID))
        mAdapter = ADA_DesignerList(mContext)
        RecyclerviewHelper.initRecyclerView(lrecyclerview, mAdapter!!, mContext)
    }

    override fun getDesignerTypeList(dataList: List<TagCategoryEntity>) {
    }

    override fun getRecommendDesigner(topDesigner: DesignerEntity) {
    }

    override fun getDesinerList(dataList: List<DesignerEntity>) {
        Log.e("TAG", "getDesinerList=" + dataList.size)
        mAdapter?.update(dataList, true)
    }
}