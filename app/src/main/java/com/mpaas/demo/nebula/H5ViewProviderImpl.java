package com.mpaas.demo.nebula;

import android.content.Context;
import android.view.ViewGroup;

import com.alipay.mobile.h5container.api.H5TitleBar;
import com.alipay.mobile.nebula.provider.H5ViewProvider;
import com.alipay.mobile.nebula.view.H5NavMenuView;
import com.alipay.mobile.nebula.view.H5PullHeaderView;
import com.alipay.mobile.nebula.view.H5TitleView;
import com.alipay.mobile.nebula.view.H5WebContentView;

public class H5ViewProviderImpl implements H5ViewProvider {

    /**
     * 标题栏
     *
     * @param context
     * @return
     */
    @Override
    public H5TitleView createTitleView(Context context) {
//        H5TitleView = new NewH5TitleViewImpl(context);
        H5TitleView h5TitleView = new H5TitleBar(context);
        return h5TitleView;
    }

    /**
     * 更多菜单栏
     *
     * @return
     */
    @Override
    public H5NavMenuView createNavMenu() {
        return null;
    }

    /**
     * 下拉刷新
     *
     * @param context
     * @param viewGroup
     * @return
     */
    @Override
    public H5PullHeaderView createPullHeaderView(Context context, ViewGroup viewGroup) {
        return null;
    }

    /**
     * web主体view
     *
     * @param context
     * @return
     */
    @Override
    public H5WebContentView createWebContentView(Context context) {
        return null;
    }
}
