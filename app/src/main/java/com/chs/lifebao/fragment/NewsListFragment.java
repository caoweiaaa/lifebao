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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.chs.lifebao.R;
import com.chs.lifebao.adapter.NewItemTypeAdapter;
import com.chs.lifebao.base.BaseFragment;
import com.chs.lifebao.bean.NewsBean;
import com.chs.lifebao.library.recycleview.ABaseLinearLayoutManager;
import com.chs.lifebao.library.recycleview.OnRecyclerViewScrollListener;
import com.chs.lifebao.library.recycleview.OnRecyclerViewScrollLocationListener;
import com.chs.lifebao.utils.DensityUtil;
import com.chs.lifebao.utils.GsonUtil;
import com.chs.lifebao.utils.L;
import com.chs.lifebao.utils.OkHttpClientManager;
import com.chs.lifebao.utils.ScreenUtils;
import com.chs.lifebao.widget.Divider;
import com.chs.lifebao.widget.RollViewPager;
import com.squareup.okhttp.Request;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 新闻列表
 * Created by llbt on 2015/9/16.
 */
public class NewsListFragment extends BaseFragment {
    private String itemId;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycleView;
    private ProgressBar mProgressBar;
    private View footerView;
    private View headNewsView;
    private int index = 0;
    private NewItemTypeAdapter adapter;
    private RollViewPager mViewPager;
    private ArrayList<View> dotList;
    private ArrayList<String> titleList, urlList;
    private LinearLayout dotLl;
    private TextView topNewsTitle;
    private LinearLayout top_news_viewpager;
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
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        initView(view);
        loadData(getCommonUrl(0 + "", itemId));
        return view;
    }

    private void initView(View view) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_swiperefresh_view);
        mRecycleView = (RecyclerView) view.findViewById(R.id.id_recycler_view);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        footerView = LayoutInflater.from(getActivity()).inflate(R.layout.recycler_view_item_type_footer, null);
        headNewsView = LayoutInflater.from(getActivity()).inflate(R.layout.layout_roll_view, null);
        dotLl = (LinearLayout) headNewsView.findViewById(R.id.dots_ll);
        topNewsTitle = (TextView) headNewsView.findViewById(R.id.top_news_title);
        top_news_viewpager = (LinearLayout) headNewsView.findViewById(R.id.top_news_viewpager);
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
                loadData(getCommonUrl(index + "", itemId));
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
                loadData(getCommonUrl(index + "", itemId));
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
        OkHttpClientManager.getAsyn(url, new OkHttpClientManager.ResultCallback<String>()
        {
            @Override
            public void onError(Request request, Exception e)
            {
                e.printStackTrace();
            }

            @Override
            public void onResponse(String u)
            {
                String str = u;
                try {
                    JSONObject object = new JSONObject(str);
                    String data = object.getString(itemId);
                    List<NewsBean> newsBeanList = GsonUtil.fromJsonArray(data, NewsBean.class);
                    urlList = new ArrayList<String>();
                    titleList = new ArrayList<String>();
                    for (int i = 0;i<4;i++){
                        urlList.add(newsBeanList.get(i).getImgsrc());
                        titleList.add(newsBeanList.get(i).getTitle());
                    }
                    initDot(urlList.size());
                    mViewPager = new RollViewPager(getActivity(), dotList,
                            R.drawable.dot_focus, R.drawable.dot_normal,
                            new RollViewPager.OnPagerClickCallback() {
                                @Override
                                public void onPagerClick(int position) {

                                }
                            });
                    mViewPager.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    //top新闻的图片地址
                    mViewPager.setUriList(urlList);
                    mViewPager.setTitle(topNewsTitle, titleList);
                    mViewPager.startRoll();
                    top_news_viewpager.removeAllViews();
                    top_news_viewpager.addView(mViewPager);
                    adapter = new NewItemTypeAdapter(getActivity(),newsBeanList,headNewsView,footerView);
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
    private void initDot(int size) {
        dotList = new ArrayList<View>();
        dotLl.removeAllViews();
        for (int i = 0; i < size; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    DensityUtil.dip2px(getActivity(), 6), DensityUtil.dip2px(getActivity(), 6));
            params.setMargins(5, 0, 5, 0);
            View m = new View(getActivity());
            if (i == 0) {
                m.setBackgroundResource(R.drawable.dot_focus);
            } else {
                m.setBackgroundResource(R.drawable.dot_normal);
            }
            m.setLayoutParams(params);
            dotLl.addView(m);
            dotList.add(m);
        }
    }
}
