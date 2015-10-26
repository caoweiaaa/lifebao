package com.chs.lifebao.adapter;

import android.content.Context;
import android.view.View;

import com.chs.lifebao.adapter.recycleviewadapter.ABAdapterTypeRender;
import com.chs.lifebao.adapter.recycleviewadapter.NewsTypeRander;
import com.chs.lifebao.adapter.recycleviewadapter.PicTypeARender;
import com.chs.lifebao.adapter.recycleviewadapter.PicTypeBRender;
import com.chs.lifebao.adapter.recycleviewadapter.extra.ABRecyclerViewTypeExtraHolder;
import com.chs.lifebao.adapter.recycleviewadapter.extra.ABRecyclerViewTypeExtraViewAdapter;
import com.chs.lifebao.bean.NewsBean;

import java.util.List;

/**
 * Created by llbt on 2015/10/16.
 */
public class NewItemTypeAdapter extends ABRecyclerViewTypeExtraViewAdapter {
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
    private List<NewsBean>mNewBeanList;
    public NewItemTypeAdapter(Context context, List<NewsBean> list,View headerView, View footerView) {
        super(headerView, footerView);
        this.context = context;
        this.mNewBeanList = list;
    }
   public List<NewsBean> getmNewBeanList(){
     return mNewBeanList;
   };
    @Override
    public ABAdapterTypeRender<ABRecyclerViewTypeExtraHolder> getAdapterTypeRenderExcludeExtraView(int type) {
        ABAdapterTypeRender<ABRecyclerViewTypeExtraHolder> render = null;
        switch (type) {
            case 0:
                render = new NewsTypeRander(context, this);
                break;
            case 1:
                render = new NewsTypeRander(context, this);
                break;
        }
        return render;
    }

    @Override
    public int getItemCountExcludeExtraView() {
        return mNewBeanList.size();
    }

    @Override
    public int getItemViewTypeExcludeExtraView(int realItemPosition) {
        return 0;
    }
}
