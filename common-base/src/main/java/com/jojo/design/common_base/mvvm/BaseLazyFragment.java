package com.jojo.design.common_base.mvvm;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jojo.design.common_base.LoadingDialog;
import com.jojo.design.common_base.bean.ErrorBean;
import com.jojo.design.common_base.constants.BroadCastConstant;
import com.jojo.design.common_base.view.MultipleStatusView;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2018/11/29 3:07 PM
 * desc   : 懒加载的Fragment
 */
public abstract class BaseLazyFragment extends Fragment implements IBaseView {
    /**
     * Log tag
     */
    protected static String TAG_LOG = null;

    /**
     * Screen information
     */
    protected int mScreenWidth = 0;
    protected int mScreenHeight = 0;
    protected float mScreenDensity = 0.0f;

    /**
     * context
     */
    protected Context mContext = null;

    private boolean isFirstResume = true;
    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;
    private boolean isPrepared;


    private LoadingDialog mLoadingDialog;

    private boolean mIsRegisterReceiver = false;

    private View container;
    protected ViewDataBinding viewDataBinding;
    private Unbinder unBinder;
    protected MultipleStatusView mMultipleStatusView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoadingDialog = new LoadingDialog(getActivity());
        TAG_LOG = this.getClass().getSimpleName();
        if (isBindEventBusHere()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentViewLayoutID() != 0) {
            mMultipleStatusView = new MultipleStatusView(getActivity());
//            viewDataBinding = DataBindingUtil.inflate(inflater, getContentViewLayoutID(), container, false);
//            return viewDataBinding.getRoot();
            viewDataBinding = DataBindingUtil.inflate(inflater, getContentViewLayoutID(), mMultipleStatusView, true);
            return mMultipleStatusView;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unBinder = ButterKnife.bind(this, view);

        container = view;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        mScreenDensity = displayMetrics.density;
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth = displayMetrics.widthPixels;


        initViewsAndEvents();

        registerReceiver();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isBindEventBusHere()) {
            EventBus.getDefault().unregister(this);
        }

        if (mIsRegisterReceiver && broadcastReceiver != null) {
            try {
                mIsRegisterReceiver = false;
                getActivity().unregisterReceiver(broadcastReceiver);
            } catch (Exception e) {
            } finally {
                broadcastReceiver = null;
            }
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
            return;
        }
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }
        }
    }

    private synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
    }

    /**
     * when fragment is visible for the first time, here we can do some initialized work or refresh data only once
     */
    protected abstract void onFirstUserVisible();

    /**
     * this method like the fragment's lifecycle method onResume()
     */
    protected abstract void onUserVisible();

    /**
     * when fragment is invisible for the first time
     */
    private void onFirstUserInvisible() {
        // here we do not recommend do something
    }

    /**
     * this method like the fragment's lifecycle method onPause()
     */
    protected abstract void onUserInvisible();


    /**
     * init all views and add events
     */
    protected abstract void initViewsAndEvents();

    /**
     * bind layout resource file
     *
     * @return id of layout resource
     */
    protected abstract int getContentViewLayoutID();


    /**
     * is bind eventBus
     *
     * @return
     */
    protected abstract boolean isBindEventBusHere();

    /**
     * get the support fragment manager
     *
     * @return
     */
    protected FragmentManager getSupportFragmentManager() {
        return getActivity().getSupportFragmentManager();
    }

    /**
     * 返回键，关闭aty
     *
     * @param view
     */
    public void onActionFinish(View view) {
        getActivity().finish();
    }

    protected void registerReceiver() {
        try {
            PackageInfo info = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            // 注册拿到加载状态广播接收器
            getActivity().registerReceiver(broadcastReceiver, new IntentFilter(info.packageName + BroadCastConstant.BROADCASE_ADDRESS));

            mIsRegisterReceiver = true;
        } catch (Exception e) {
        }
    }

    // 注册广播 ,用于接收耗时任务的处理进度
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                PackageInfo info = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);

                if (intent.getAction().equals(info.packageName + BroadCastConstant.BROADCASE_ADDRESS)) {
                    Bundle bundle = intent.getExtras();

                    switch (bundle.getInt(BroadCastConstant.BROADCASE_INTENT)) {
                        default:
                            Integer i = bundle.getInt(BroadCastConstant.BROADCASE_INTENT);
                            if (i != null) {
                                BaseLazyFragment.this.onReceiveBroadcast(i, bundle);
                            }
                            break;
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
            }
        }
    };

    /**
     * 发送一个广播
     *
     * @param value
     */
    protected void sendBroadcast(int value) {
        sendBroadcast(getActivity(), value);
    }

    /**
     * 发送一个广播
     *
     * @param value
     */
    private void sendBroadcast(Context context, int value) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            Intent intent = new Intent();
            intent.setAction(info.packageName + BroadCastConstant.BROADCASE_ADDRESS);
            intent.putExtra(BroadCastConstant.BROADCASE_INTENT, value);
            context.sendBroadcast(intent);
        } catch (PackageManager.NameNotFoundException e) {
        }
    }

    /**
     * 接收其他类型的广播
     *
     * @param intent
     */
    protected void onReceiveBroadcast(int intent, Bundle bundle) {
    }

    @Override
    public void showException(@NotNull ErrorBean error) {

    }

    @Override
    public void showBusinessError(@NotNull ErrorBean error) {

    }

    @Override
    public void showLoading() {
        mMultipleStatusView.showLoading();
    }

    @Override
    public void showDialogLoading(@NotNull String msg) {
        mLoadingDialog.show();
    }

    @Override
    public void dismissDialogLoading() {
        mLoadingDialog.dismiss();
    }
}
