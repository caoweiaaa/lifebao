package com.chs.lifebao.adapter.recycleviewadapter.extra;

import android.view.View;

import com.chs.lifebao.adapter.recycleviewadapter.ABAdapterTypeRender;

/**
 * 带有header或者footer的view
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 1/22/15.
 */
/*public*/ class ABRecyclerViewTypeExtraRender implements ABAdapterTypeRender<ABRecyclerViewTypeExtraHolder> {
    protected ABRecyclerViewTypeExtraHolder holder;

    protected ABRecyclerViewTypeExtraRender(View extraView) {
        holder = new ABRecyclerViewTypeExtraHolder(extraView);
    }

    @Override
    public ABRecyclerViewTypeExtraHolder getReusableComponent() {
        return holder;
    }

    @Override
    public void fitEvents() {

    }

    @Override
    public void fitDatas(int position) {

    }
}
