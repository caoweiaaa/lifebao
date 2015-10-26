package com.chs.lifebao.adapter.recycleviewadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chs.lifebao.R;
import com.chs.lifebao.adapter.NewItemTypeAdapter;
import com.chs.lifebao.adapter.PicItemTypeAdapter;
import com.chs.lifebao.adapter.recycleviewadapter.extra.ABRecyclerViewTypeExtraHolder;
import com.chs.lifebao.bean.NewsBean;
import com.chs.lifebao.bean.PicBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by llbt on 2015/10/16.
 */
public class NewsTypeRander implements ABAdapterTypeRender<ABRecyclerViewTypeExtraHolder>  {
    private Context context;
    private NewItemTypeAdapter adapter;
    private ABRecyclerViewTypeExtraHolder holder;
    public NewsTypeRander(Context context, NewItemTypeAdapter adapter) {
        this.context = context;
        this.adapter = adapter;
        View view = LayoutInflater.from(context).inflate(R.layout.item_new, null);
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
        View rootView = holder.obtainView(R.id.item_layout);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewItemTypeAdapter.OnRecyclerViewListener listener = adapter.getOnRecyclerViewListener();
                if (null != listener) {
                    listener.onItemClick(holder.getRealItemPosition());
                }
            }
        });
        rootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                NewItemTypeAdapter.OnRecyclerViewListener listener = adapter.getOnRecyclerViewListener();
                if (null != listener) {
                    listener.onItemLongClick(holder.getRealItemPosition());
                }
                return false;
            }
        });
    }

    @Override
    public void fitDatas(int position) {
        List<NewsBean> newsBeanList = adapter.getmNewBeanList();
        NewsBean newsBean = newsBeanList.get(position);
        ImageView imPic = holder.obtainView(R.id.left_image, ImageView.class);
        RelativeLayout relativeLayoutimMsg = holder.obtainView(R.id.article_top_layout,RelativeLayout.class);
        LinearLayout layoutPic = holder.obtainView(R.id.layout_image,LinearLayout.class);
        ImageView imPic1 = holder.obtainView(R.id.item_image_0, ImageView.class);
        ImageView imPic2 = holder.obtainView(R.id.item_image_1, ImageView.class);
        ImageView imPic3 = holder.obtainView(R.id.item_image_2, ImageView.class);
        TextView tvPicDesign = holder.obtainView(R.id.item_abstract,TextView.class);
        if(newsBean.getImgextra()==null){
            relativeLayoutimMsg.setVisibility(View.VISIBLE);
            layoutPic.setVisibility(View.GONE);
            holder.obtainView(R.id.item_title, TextView.class).setText(newsBean.getTitle());
            holder.obtainView(R.id.item_content, TextView.class).setText(newsBean.getDigest());
            Picasso.with(context) //
                    .load(newsBean.getImgsrc()) //
                    .placeholder(R.drawable.base_article_bigimage) //
                    .error(R.drawable.placeholder) //
                    .fit() //
                    .into(imPic);
        }else{
            relativeLayoutimMsg.setVisibility(View.GONE);
            layoutPic.setVisibility(View.VISIBLE);
            tvPicDesign.setText(newsBean.getTitle());
            Picasso.with(context) //
                    .load(newsBean.getImgsrc()) //
                    .placeholder(R.drawable.base_article_bigimage) //
                    .error(R.drawable.placeholder) //
                    .fit() //
                    .into(imPic1);
            Picasso.with(context) //
                    .load(newsBean.getImgextra().get(0).getImgsrc()) //
                    .placeholder(R.drawable.base_article_bigimage) //
                    .error(R.drawable.placeholder) //
                    .fit() //
                    .into(imPic2);
            Picasso.with(context) //
                    .load(newsBean.getImgextra().get(1).getImgsrc()) //
                    .placeholder(R.drawable.base_article_bigimage) //
                    .error(R.drawable.placeholder) //
                    .fit() //
                    .into(imPic3);
        }
    }
}
