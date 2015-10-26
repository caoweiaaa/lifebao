package com.chs.lifebao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chs.lifebao.R;
import com.chs.lifebao.base.BaseFragment;

/**
 * Created by llbt on 2015/9/16.
 */
public class MineFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab04, container, false);
    }
}
