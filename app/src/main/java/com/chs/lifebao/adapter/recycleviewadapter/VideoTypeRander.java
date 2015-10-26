package com.chs.lifebao.adapter.recycleviewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chs.lifebao.R;
import com.chs.lifebao.adapter.NewItemTypeAdapter;
import com.chs.lifebao.adapter.VideoItemTypeAdapter;
import com.chs.lifebao.adapter.recycleviewadapter.extra.ABRecyclerViewTypeExtraHolder;
import com.chs.lifebao.bean.PicBean;
import com.chs.lifebao.bean.VideoBean;
import com.squareup.picasso.Picasso;

/**
 * Created by llbt on 2015/10/16.
 */
public class VideoTypeRander implements ABAdapterTypeRender<ABRecyclerViewTypeExtraHolder> {
    private Context context;
    private VideoItemTypeAdapter adapter;
    private ABRecyclerViewTypeExtraHolder holder;

    public VideoTypeRander(Context context, VideoItemTypeAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
        View view = LayoutInflater.from(context).inflate(R.layout.item_video, null);
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
        View rootView = holder.obtainView(R.id.rl_root_view);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoItemTypeAdapter.OnRecyclerViewListener listener = adapter.getOnRecyclerViewListener();
                if (null != listener) {
                    listener.onItemClick(holder.getRealItemPosition());
                }
            }
        });
        rootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                VideoItemTypeAdapter.OnRecyclerViewListener listener = adapter.getOnRecyclerViewListener();
                if (null != listener) {
                    listener.onItemLongClick(holder.getRealItemPosition());
                }
                return false;
            }
        });
    }

    @Override
    public void fitDatas(int position) {
        VideoBean videoBean = adapter.getmVideoBeanList().get(position);
        holder.obtainView(R.id.video_title, TextView.class).setText(videoBean.getTitle());
        holder.obtainView(R.id.video_time, TextView.class).setText(getTime(videoBean.getLength()));
        ImageView imPic = holder.obtainView(R.id.video_img, ImageView.class);
        Picasso.with(context) //
                .load(videoBean.getCover()) //
                .placeholder(R.drawable.base_article_bigimage) //
                .error(R.drawable.placeholder) //
                .fit() //
                .into(imPic);
    }
    public String getTime(int length) {
        int fen = length / 60;
        int miao = length % 60;
        String fenString = fen + "";
        String miaoString = miao + "";
        fenString = fenString.length() == 1 ? "0" + fenString : fenString;
        miaoString = miaoString.length() == 1 ? miaoString + "0" : miaoString;
        return fenString + ":" + miaoString;
    }
}
