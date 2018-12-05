package com.jojo.design.common_base.mvvm;

import android.view.View;

import com.jojo.design.common_base.utils.ClassReflectHelper;
import com.jojo.design.common_base.view.MultipleStatusView;

/**
 *    author : JOJO
 *    e-mail : 18510829974@163.com
 *    date   : 2018/11/29 3:07 PM
 *    desc   : mvp的BaseFramen,需联网获取数据的Fragment的父类
 */
public abstract class BaseMVPFragment<P extends BasePresenter,M extends IBaseModel> extends BaseLazyFragment implements IBaseView {

    protected P mMvpPresenter;
    protected M mModel;
    @Override
    protected void initViewsAndEvents() {
        //MVP
        mMvpPresenter = ClassReflectHelper.Companion.getT(this,0);
        mModel = ClassReflectHelper.Companion.getT(this, 1);
        if (this instanceof IBaseView) {
            if (mMvpPresenter != null && mModel != null) {
                mMvpPresenter.setVM(this, mModel);
            }
        }
        startEvents();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMvpPresenter != null) {
            mMvpPresenter.onDestroy();
        }
    }

    protected abstract void startEvents();

}

