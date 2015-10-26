package com.chs.lifebao.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.chs.lifebao.R;
import com.chs.lifebao.adapter.NewItemTypeAdapter;
import com.chs.lifebao.adapter.PicItemTypeAdapter;
import com.chs.lifebao.adapter.VideoItemTypeAdapter;
import com.chs.lifebao.base.BaseFragment;
import com.chs.lifebao.bean.NewsBean;
import com.chs.lifebao.bean.VideoBean;
import com.chs.lifebao.library.recycleview.ABaseLinearLayoutManager;
import com.chs.lifebao.library.recycleview.OnRecyclerViewScrollListener;
import com.chs.lifebao.library.recycleview.OnRecyclerViewScrollLocationListener;
import com.chs.lifebao.utils.GsonUtil;
import com.chs.lifebao.utils.L;
import com.chs.lifebao.utils.OkHttpClientManager;
import com.chs.lifebao.widget.Divider;
import com.squareup.okhttp.Request;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by llbt on 2015/9/22.
 */
public class VideoListFragment extends BaseFragment {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycleView;
    private int index = 1;
    private VideoItemTypeAdapter adapter;
    private View footerView;
    private ProgressBar mProgressBar;
    private String itemId;
@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle bundle = getArguments();
            itemId = bundle.getString("itemId");
//            setRetainInstance(true);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_list, container, false);
        initView(view);
        loadData(getVideoUrl(0 + "", itemId));
        return view;
    }

    private void initView(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_swiperefresh_view);
        mRecycleView = (RecyclerView) view.findViewById(R.id.id_recycler_view);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        footerView = LayoutInflater.from(getActivity()).inflate(R.layout.recycler_view_item_type_footer, null);
        //        不知道为什么在xml设置的“android:layout_width="match_parent"”无效了，需要在这里重新设置
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        footerView.setLayoutParams(lp);
        // improve performance if you know that changes in content
        // do not change the size of the RecyclerView
        mRecycleView.setHasFixedSize(true);
        final ABaseLinearLayoutManager layoutManager = new ABaseLinearLayoutManager(getActivity());
        layoutManager.setOnRecyclerViewScrollLocationListener(mRecycleView, new OnRecyclerViewScrollLocationListener() {
            @Override
            public void onTopWhenScrollIdle(RecyclerView recyclerView) {
                L.d("onTopWhenScrollIdle...");
            }

            @Override
            public void onBottomWhenScrollIdle(RecyclerView recyclerView) {
                L.d("onBottomWhenScrollIdle...");
                footerView.setVisibility(View.VISIBLE);
                currentPagte++;
                index = index + 20;
                loadData(getVideoUrl(index + "", itemId));
            }
        });
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.getRecyclerViewScrollManager().addScrollListener(mRecycleView, new OnRecyclerViewScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                mSwipeRefreshLayout.setEnabled(layoutManager.findFirstCompletelyVisibleItemPosition() == 0);
            }
        });
        mRecycleView.setLayoutManager(layoutManager);

        mSwipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                index = 0;
                loadData(getVideoUrl(index + "", itemId));
            }
        });
        mRecycleView.addItemDecoration(new Divider(getActivity()));
    }

    private void loadData(String url){
        if(hasNetWork()){
            loadNewList(url);
        }
    }
    private void loadNewList(String url) {
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(String u) {
                String str = u;
                try {
                    JSONObject object = new JSONObject(str);
                    String data = object.getString(itemId);
                    List<VideoBean> videoBeanList = GsonUtil.fromJsonArray(data, VideoBean.class);
                    adapter = new VideoItemTypeAdapter(getActivity(), videoBeanList, null, footerView);
                    mRecycleView.setAdapter(adapter);

                    mSwipeRefreshLayout.setRefreshing(false);
                    footerView.setVisibility(View.GONE);
                    mProgressBar.setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
