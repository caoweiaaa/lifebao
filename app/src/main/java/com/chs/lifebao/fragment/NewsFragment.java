package com.chs.lifebao.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chs.lifebao.R;
import com.chs.lifebao.base.BaseFragment;
import com.chs.lifebao.utils.Constants;
import com.chs.lifebao.widget.SlidingTabLayout;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by llbt on 2015/9/16.
 */
public class NewsFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
//    private TabLayout tabLayout;
    private ViewPager pager;
    private MyPagerAdapter adapter;
    private String[] chinnals;
    private String[] itemIds;
    private SlidingTabLayout mSlidingTabLayout;
    int mCurrentPosition = 0;
    private List<NewsListFragment> listFragments = new ArrayList<NewsListFragment>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.tab01, container, false);
        chinnals = getActivity().getResources().getStringArray(R.array.newschinnals);
        itemIds = getActivity().getResources().getStringArray(R.array.newsitemids);
        initData();
        initView(mView);
        return mView;
    }

    private void initData() {
        listFragments.clear();;
        for (String itemId:itemIds) {
            NewsListFragment fragment = new NewsListFragment();
            Bundle bunndle = new Bundle();
            bunndle.putString("itemId",itemId);
            fragment.setArguments(bunndle);
            listFragments.add(fragment);
        }
    }

    private void initView(View view) {
//        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        pager = (ViewPager) view.findViewById(R.id.pager);
        adapter = new MyPagerAdapter(getFragmentManager());
        pager.setAdapter(adapter);
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

    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return listFragments.get(i);
        }

        @Override
        public int getCount() {
            return chinnals.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = chinnals[position];
            return title;
        }
    }
}
