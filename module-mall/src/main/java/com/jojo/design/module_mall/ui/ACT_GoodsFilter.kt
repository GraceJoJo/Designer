package com.jojo.design.module_mall.ui

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.alibaba.android.arouter.facade.annotation.Route
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.config.arouter.ARouterConfig
import com.jojo.design.common_base.config.arouter.ARouterConstants
import com.jojo.design.common_base.dagger.mvp.BaseActivity
import com.jojo.design.common_base.utils.RecyclerviewHelper
import com.jojo.design.common_base.utils.ToastUtils
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_mall.R
import com.jojo.design.module_mall.R.id.rb_recommend
import com.jojo.design.module_mall.adapter.ADA_SearchGoods
import com.jojo.design.module_mall.bean.RecordsEntity
import com.jojo.design.module_mall.dagger2.DaggerMallComponent
import com.jojo.design.module_mall.mvp.SearchContract
import com.jojo.design.module_mall.mvp.presenter.SearchModel
import com.jojo.design.module_mall.mvp.presenter.SearchPresenter
import com.smart.novel.util.bindView
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.act_goods_filter.*
import kotlinx.android.synthetic.main.common_filter_layout.*

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2019/1/2 6:10 PM
 *    desc   : 商品搜索、筛选结果页面
 */
@Route(path = ARouterConfig.ACT_GoodsFilter)
class ACT_GoodsFilter : BaseActivity<SearchPresenter, SearchModel>(), SearchContract.View {
    var mAdapter: ADA_SearchGoods? = null
    var mColumnNum = 2
    //    @BindView(R.id.iv_search) lateinit var ivSearch: ImageView //单模块下开发OK，组件化开发会报编译错误
    private val ivSearch by bindView<ImageView>(R.id.iv_search) //Kotlin下组件化开发时只能使用此种方式，否则会报编译错误。

    override fun getContentViewLayoutId(): Int = R.layout.act_goods_filter

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
        DaggerMallComponent.builder().applicationComponent(BaseAppliction.mApplicationComponent).build().inject(this)
    }

    override fun startEvents() {
        ivSearch.visibility = View.VISIBLE
        setHeaderTitle(intent.extras.getString(ARouterConstants.SEARCH_KEYWORDS))


        initListener()

        mAdapter = ADA_SearchGoods(mContext)
        RecyclerviewHelper.initLayoutManagerRecyclerView(lrecyclerview, mAdapter!!, GridLayoutManager(mContext, 2), mContext)
        // //设置item之间的间距
        lrecyclerview.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView?) {
                //设计图item之间的间距为40 (header占了一个位置，故从位置1开始显示实际的item)
                if (itemPosition != 0) {
                    outRect.top = 40
                }
                if ((itemPosition - 1) % 2 == 0) {
                    outRect.left = 40
                } else {
                    outRect.left = 20
                }
            }
        })
        lrecyclerview.setOnRefreshListener {
            android.os.Handler().postDelayed({ lrecyclerview.refreshComplete(1) }, 2000)
        }
        //传了分类ID，就不传关键字匹配
        if (!TextUtils.isEmpty(intent.extras.getString(ARouterConstants.TAGCATEGORY_ID))) mPresenter?.getSearchGoods(intent.extras.getString(ARouterConstants.TAGCATEGORY_ID), "", 0)
        else mPresenter?.getSearchGoods(intent.extras.getString(ARouterConstants.TAGCATEGORY_ID), intent.extras.getString(ARouterConstants.SEARCH_KEYWORDS), 0)


    }

    var isClick = false
    private fun initListener() {
        rb_recommend.setOnClickListener {
            if (isClick) {
                rb_recommend.isSelected = false
                isClick = false
            } else {
                rb_recommend.isSelected = true
                isClick = true
            }
        }
    }

    override fun getHotList(dataList: List<String>) {
    }


    override fun getSearchGoods(dataBean: RecordsEntity) {
        if (dataBean?.records == null || dataBean.records.isEmpty()) {
            ToastUtils.makeShortToast(BaseAppliction.context.getString(R.string.content_search_content_not_empty))
            return
        }
        mAdapter?.update(dataBean.records, true)
    }

}