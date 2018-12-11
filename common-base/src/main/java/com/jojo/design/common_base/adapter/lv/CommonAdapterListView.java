package com.jojo.design.common_base.adapter.lv;

import android.content.Context;

import java.util.Collection;
import java.util.List;

public abstract class CommonAdapterListView<T> extends MultiItemTypeAdapterListView<T> {

    public CommonAdapterListView(Context context) {
        super(context);
        
        addItemViewDelegate(new ItemViewDelegateListView<T>() {
            @Override
            public int getItemViewLayoutId() {
                return itemLayoutId();
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolderListView holder, T t, int position) {
                CommonAdapterListView.this.convert(holder, t, position);
            }
        });
    }

    protected abstract int itemLayoutId();

    protected abstract void convert(ViewHolderListView viewHolder, T item, int position);

    /**
     * 设置适配器的数据，添加数据
     *
     * @param dataList
     */
    public void update(List<T> dataList) {
        if (dataList != null) {
            mDatas.addAll(dataList);
        }

        notifyDataSetChanged();
    }

    /**
     * 设置适配器数据
     *
     * @param dataList
     * @param isClear  是否需要清空list然后在加载数据
     */
    public void update(List<T> dataList, Boolean isClear) {
        if (isClear) {
            clear();
        }
        if (dataList != null) {
            mDatas.addAll(dataList);
        }
        notifyDataSetChanged();
    }

    /**
     * 设置适配器的数据,向列表具体位置添加数据
     *
     * @param t
     */
    public void update(T t, int i) {
        if (t != null) {
            mDatas.add(i, t);
        }
        notifyDataSetChanged();
    }

    /**
     * 设置适配器的数据,添加或删除某个实体
     *
     * @param i
     * @param t
     * @param isAdd
     */
    public void update(int i, T t, boolean isAdd) {
        if (t != null) {
            if (isAdd) {
                mDatas.add(i, t);
            } else {
                mDatas.remove(t);
            }
        }
        notifyDataSetChanged();
    }

    /**
     * 清除集合数据
     */
    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public List<T> getDataList() {
        return mDatas;
    }

    /**
     * 清空集合，设置适配器数据
     *
     * @param list
     */
    public void setDataList(Collection<T> list) {
        this.mDatas.clear();
        this.mDatas.addAll(list);
        notifyDataSetChanged();
    }
}
