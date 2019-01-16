package com.jojo.design.module_mall.helper;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.jojo.design.module_mall.adapter.BannerHolderView;

import java.util.List;

/**
 * author : JOJO
 * e-mail : 18510829974@163.com
 * date   : 2019/1/16 5:40 PM
 * desc   :
 */
public class BannerHelper {
    public static void setBanner(ConvenientBanner banner, List<String> imgUrls){
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {
                return new BannerHolderView();
            }
        }, imgUrls);
    }
}
