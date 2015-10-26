package com.chs.lifebao.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chs.lifebao.R;
import com.chs.lifebao.base.BaseFragment;
import com.chs.lifebao.bean.VideoBean;
import com.chs.lifebao.widget.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频列表
 */
public class FindFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    private ViewPager pager;
    private String[] chinnals;
    private String[] itemIds;
    private MyPicPagerAdapter adapter;
    private SlidingTabLayout mSlidingTabLayout;
    int mCurrentPosition = 0;
    private List<VideoListFragment> videoBeanList = new ArrayList<VideoListFragment>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.tab03, container, false);
        chinnals = getActivity().getResources().getStringArray(R.array.videochinnals);
        itemIds = getActivity().getResources().getStringArray(R.array.videoitemids);
        initData();
        initView(mView);
        return mView;
    }

    private void initData() {
        videoBeanList.clear();
        for (String itemId:itemIds) {
            VideoListFragment fragment = new VideoListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("itemId",itemId);
            fragment.setArguments(bundle);
            videoBeanList.add(fragment);
        }
    }

    private void initView(View view) {
        pager = (ViewPager) view.findViewById(R.id.pager);
        adapter = new MyPicPagerAdapter(getChildFragmentManager());
        pager.setAdapter(adapter);
//        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(pager);
        mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.slidingTabs);
        pager.setCurrentItem(mCurrentPosition);
        mSlidingTabLayout.setCustomTabView(R.layout.comm_lay_tab_indicator, android.R.id.text1);
        Resources res = getResources();
        mSlidingTabLayout.setSelectedIndicatorColors(res.getColor(R.color.comm_tab_selected_strip));
        mSlidingTabLayout.setDistributeEvenly(isDistributeEvenly());
        mSlidingTabLayout.setViewPager(pager);
        mSlidingTabLayout.setOnPageChangeListener(this);
        mSlidingTabLayout.setCurrent(mCurrentPosition);
    }
    protected boolean isDistributeEvenly() {
        return chinnals.length <= 5;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    private class MyPicPagerAdapter extends FragmentStatePagerAdapter {

        public MyPicPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return videoBeanList.get(position);
        }

        @Override
        public int getCount() {
            return chinnals.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return chinnals[position];
        }
    }
}
