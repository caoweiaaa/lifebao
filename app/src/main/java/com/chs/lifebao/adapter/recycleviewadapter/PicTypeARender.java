package com.chs.lifebao.adapter.recycleviewadapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chs.lifebao.R;
import com.chs.lifebao.adapter.PicItemTypeAdapter;
import com.chs.lifebao.adapter.recycleviewadapter.extra.ABRecyclerViewTypeExtraHolder;
import com.chs.lifebao.bean.PicBean;
import com.chs.lifebao.utils.ScreenUtils;
import com.squareup.picasso.Picasso;

import java.net.URI;

/**
 * Created by llbt on 2015/9/29.
 */
public class PicTypeARender implements ABAdapterTypeRender<ABRecyclerViewTypeExtraHolder> {
    private Context context;
    private PicItemTypeAdapter adapter;
    private ABRecyclerViewTypeExtraHolder holder;

    public PicTypeARender(Context context, PicItemTypeAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
        View view = LayoutInflater.from(context).inflate(R.layout.item_pic_list, null);
//        不知道为什么在xml设置的“android:layout_width="match_parent"”无效了，需要在这里重新设置
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        holder = new ABRecyclerViewTypeExtraHolder(view);
    }

    @Override
    public ABRecyclerViewTypeExtraHolder getReusableComponent() {
        return holder;
    }

    @Override
    public void fitEvents() {
        View rootView = holder.obtainView(R.id.id_pic_recycle_root);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PicItemTypeAdapter.OnRecyclerViewListener listener = adapter.getOnRecyclerViewListener();
                if (null != listener) {
                    listener.onItemClick(holder.getRealItemPosition());
                }
            }
        });
        rootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PicItemTypeAdapter.OnRecyclerViewListener listener = adapter.getOnRecyclerViewListener();
                if (null != listener) {
                    listener.onItemLongClick(holder.getRealItemPosition());
                }
                return false;
            }
        });
    }

    @Override
    public void fitDatas(int position) {
        PicBean.PicBeanList list = adapter.getList().get(position);
        holder.obtainView(R.id.photo_title, TextView.class).setText(list.getTitle());
        ImageView imPic = holder.obtainView(R.id.photo_img, ImageView.class);
        Picasso.with(context) //
                .load(list.getPics().getList().get(0).getKpic()) //
                .placeholder(R.drawable.base_article_bigimage) //
                .error(R.drawable.placeholder) //
                .fit() //
                .into(imPic);
    }
}
