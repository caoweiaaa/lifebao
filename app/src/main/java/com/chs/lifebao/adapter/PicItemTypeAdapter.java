package com.chs.lifebao.adapter;

import android.content.Context;
import android.view.View;

import com.chs.lifebao.adapter.recycleviewadapter.ABAdapterTypeRender;
import com.chs.lifebao.adapter.recycleviewadapter.PicTypeARender;
import com.chs.lifebao.adapter.recycleviewadapter.PicTypeBRender;
import com.chs.lifebao.adapter.recycleviewadapter.extra.ABRecyclerViewTypeExtraHolder;
import com.chs.lifebao.adapter.recycleviewadapter.extra.ABRecyclerViewTypeExtraViewAdapter;
import com.chs.lifebao.bean.PicBean;

import java.util.List;

/**
 * Created by llbt on 2015/9/29.
 */
public class PicItemTypeAdapter extends ABRecyclerViewTypeExtraViewAdapter {
    public static interface OnRecyclerViewListener {
        void onItemClick(int position);

        boolean onItemLongClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;
    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }
    public OnRecyclerViewListener getOnRecyclerViewListener() {
        return onRecyclerViewListener;
    }

    private Context context;
    private List<PicBean.PicBeanList>picBeanLists;
    public PicItemTypeAdapter(Context context, List<PicBean.PicBeanList> list, View headerView, View footerView) {
        super(headerView, footerView);
        this.context = context;
        this.picBeanLists = list;
    }
    public List<PicBean.PicBeanList> getList() {
        return picBeanLists;
    }
    @Override
    public ABAdapterTypeRender<ABRecyclerViewTypeExtraHolder> getAdapterTypeRenderExcludeExtraView(int type) {
        ABAdapterTypeRender<ABRecyclerViewTypeExtraHolder> render = null;
        switch (type) {
            case 0:
                render = new PicTypeARender(context, this);
                break;
            case 1:
                render = new PicTypeBRender(context, this);
                break;
        }
        return render;
    }

    @Override
    public int getItemCountExcludeExtraView() {
        return picBeanLists.size();
    }

    @Override
    public int getItemViewTypeExcludeExtraView(int realItemPosition) {
        return picBeanLists.get(realItemPosition).getType();
    }
}
