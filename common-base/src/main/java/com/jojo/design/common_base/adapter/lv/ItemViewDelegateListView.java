package com.jojo.design.common_base.adapter.lv;


/**
 * Created by zhy on 16/6/22.
 */
public interface ItemViewDelegateListView<T>
{

    public abstract int getItemViewLayoutId();

    public abstract boolean isForViewType(T item, int position);

    public abstract void convert(ViewHolderListView holder, T t, int position);



}
