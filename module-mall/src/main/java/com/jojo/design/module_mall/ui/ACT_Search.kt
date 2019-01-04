package com.jojo.design.module_mall.ui

import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jojo.design.common_base.BaseAppliction
import com.jojo.design.common_base.config.arouter.ARouterConfig
import com.jojo.design.common_base.config.arouter.ARouterConstants
import com.jojo.design.common_base.dagger.mvp.BaseActivity
import com.jojo.design.common_base.utils.RecyclerviewHelper
import com.jojo.design.common_base.utils.ToastUtils
import com.jojo.design.common_ui.view.MultipleStatusView
import com.jojo.design.module_mall.R
import com.jojo.design.module_mall.R.id.taglayout
import com.jojo.design.module_mall.adapter.ADA_SearchHistory
import com.jojo.design.module_mall.bean.RecordsEntity
import com.jojo.design.module_mall.db.bean.SearchHistoryBean
import com.jojo.design.module_mall.dagger2.DaggerMallComponent
import com.jojo.design.module_mall.db.AppDataBaseHelper
import com.jojo.design.module_mall.mvp.SearchContract
import com.jojo.design.module_mall.mvp.presenter.SearchModel
import com.jojo.design.module_mall.mvp.presenter.SearchPresenter
import com.smart.novel.adapter.ADA_HotSearchTag
import com.will.weiyuekotlin.component.ApplicationComponent
import kotlinx.android.synthetic.main.act_search.*
import kotlinx.android.synthetic.main.layout_search.*


/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/12/29 10:08 AM
 *    desc   : 商品搜索页面
 */
@Route(path = ARouterConfig.ACT_SEARCH)
class ACT_Search : BaseActivity<SearchPresenter, SearchModel>(), SearchContract.View {
    var mHistoryAdapter: ADA_SearchHistory? = null
    var mHotSearchAdapter: ADA_HotSearchTag? = null
    override fun getContentViewLayoutId(): Int = R.layout.act_search

    override fun getLoadingMultipleStatusView(): MultipleStatusView? = null

    override fun initDaggerInject(mApplicationComponent: ApplicationComponent) {
        DaggerMallComponent.builder().applicationComponent(BaseAppliction.mApplicationComponent).build().inject(this)
    }

    override fun startEvents() {
        var data = ArrayList<SearchHistoryBean>()
//        (0..10).mapTo(data) { SearchHistoryBean(it.toLong(), "item=" + it) }

        //搜索历史
        mHistoryAdapter = ADA_SearchHistory(mContext)
        RecyclerviewHelper.initNormalRecyclerView(rvHistory, mHistoryAdapter!!, object : LinearLayoutManager(mContext) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        })
        mHistoryAdapter!!.update(data, true)

        mPresenter?.getHotList()
//        mPresenter?.getSearchGoods("2", "", 0)

        initListener()
    }

    private fun initListener() {
        tv_cancle.setOnClickListener {
            // 先隐藏键盘
            (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(currentFocus!!
                            .windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            et_search.setText("")
        }
        //键盘搜索
        et_search.setOnKeyListener({ v, keyCode, event ->
            // event.action == KeyEvent.ACTION_UP 解决回车键执行两次的问题
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                if (currentFocus != null && currentFocus!!.windowToken != null) {
                    // 先隐藏键盘
                    (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
                            .hideSoftInputFromWindow(currentFocus!!
                                    .windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                }
                doSearch()
            }
            false
        })
        taglayout.setOnTagClickListener { view, position, parent ->
            var history = SearchHistoryBean()
            history.searchKeyWords = mHotSearchAdapter!!.getItem(position)
            et_search.setText(history.searchKeyWords)
            ARouter.getInstance().build(ARouterConfig.ACT_GoodsFilter)
                    .withString(ARouterConstants.SEARCH_KEYWORDS, mHotSearchAdapter!!.getItem(position))
                    .withString(ARouterConstants.TAGCATEGORY_ID, "")
                    .navigation()
            true
        }

        iv_deleteAll.setOnClickListener {
            var allHistory = AppDataBaseHelper.getInstance(mContext).appDataBase.historyDao().getAllHistory()
            ToastUtils.makeShortToast("total=" + allHistory?.size + "bean=" + allHistory[0])
        }

    }

    /**
     * 搜索
     */
    private fun doSearch() {
        if (TextUtils.isEmpty(et_search.text.toString().trim())) {
            ToastUtils.makeShortToast(BaseAppliction.context.getString(R.string.content_search_content_not_empty))
            return
        }

        ARouter.getInstance().build(ARouterConfig.ACT_GoodsFilter)
                .withString(ARouterConstants.SEARCH_KEYWORDS, et_search.text.toString().trim())
                .withString(ARouterConstants.TAGCATEGORY_ID, "")
                .navigation()
    }

    override fun getHotList(dataList: List<String>) {
        Log.e("TAG", "dataList=" + dataList.size)
        mHotSearchAdapter = ADA_HotSearchTag(dataList)
        taglayout.adapter = mHotSearchAdapter
    }

    override fun getSearchGoods(dataBean: RecordsEntity) {
        Log.e("TAG", "dataBean=" + dataBean.size)
    }
}