package com.chs.lifebao.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chs.lifebao.R;
import com.chs.lifebao.base.BaseFragment;
import com.chs.lifebao.utils.Url;
import com.chs.lifebao.widget.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频列表
 * Created by llbt on 2015/9/16.
 */
public class DiymainFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    private ViewPager pager;
    private String[] chinnals;
    private String[] mItemIds = new String[]{Url.JINGXUAN_ID,Url.QUTU_ID,
          Url.MEITU_ID,Url.GUSHI_ID};
    private MyPicPagerAdapter adapter;
    private List<PicListFragment> picFragments = new ArrayList<PicListFragment>();
    private SlidingTabLayout mSlidingTabLayout;
//    private TabLayout tabLayout;
    int mCurrentPosition = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.tab02, container, false);
        chinnals = getActivity().getResources().getStringArray(R.array.picchinnals);
        initData();
        initView(mView);
        return mView;
    }

    private void initData() {
        for (String url: mItemIds ) {
            PicListFragment pf = new PicListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("itemId",url);
            pf.setArguments(bundle);
            picFragments.add(pf);
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
            return picFragments.get(position);
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
