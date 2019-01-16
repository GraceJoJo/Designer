package com.jojo.design.module_mall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.jojo.design.common_base.utils.glide.GlideUtils;
import com.jojo.design.module_mall.R;

/**
 * 图片轮播适配器
 */
public class BannerHolderView implements Holder<String> {
    ImageView iv_banner;

    @Override
    public View createView(Context context) {
        View rootview = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_banner, null);
        iv_banner = rootview.findViewById(R.id.iv_banner);
        return rootview;
    }

    @Override
    public void UpdateUI(Context context, int position, String data) {
        GlideUtils.INSTANCE.loadImage(data, iv_banner, 0);
    }
}
