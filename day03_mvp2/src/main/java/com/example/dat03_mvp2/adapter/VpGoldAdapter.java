package com.example.dat03_mvp2.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dat03_mvp2.base.BaseFragment;
import com.example.dat03_mvp2.bean.GoldTitleBean;

import java.util.ArrayList;

public class VpGoldAdapter extends FragmentStatePagerAdapter {
    /**
     * @author xts
     *         Created by asus on 2019/4/17.
     *         生命周期不一样
     *         FragmentStatePagerAdapter:用不着的碎片生命周期,onDetach();取消关联
     *         FragmentPagerAdapter:用不着的碎片生命周期,只会走到onDestoryView();
     *
     */

    private ArrayList<BaseFragment> mFragments;
    private ArrayList<GoldTitleBean> mTitles;
    private ArrayList<String> mNewTitles = new ArrayList<>();

    public VpGoldAdapter(FragmentManager fm,
                         ArrayList<BaseFragment> fragments, ArrayList<GoldTitleBean> titles) {
        super(fm);
        mFragments = fragments;
        mTitles = titles;

        for (int i = 0; i < mTitles.size(); i++) {
            GoldTitleBean bean = mTitles.get(i);
            if (bean.isChecked){
                mNewTitles.add(bean.title);
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mNewTitles.get(position);
    }
}
