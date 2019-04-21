package com.example.dat03_mvp2.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dat03_mvp2.base.BaseFragment;

import java.util.ArrayList;

public class VpZhihuAdapter extends FragmentStatePagerAdapter {
    /**
     * @author xts
     *         Created by asus on 2019/4/17.
     *         生命周期不一样
     *         FragmentStatePagerAdapter:用不着的碎片生命周期,onDetach();取消关联
     *         FragmentPagerAdapter:用不着的碎片生命周期,只会走到onDestoryView();
     *
     */
    private Context context;
    private ArrayList<BaseFragment> mFragment;
    private ArrayList<Integer> mTitles;


    public VpZhihuAdapter(FragmentManager fm,Context context,ArrayList<BaseFragment> mFragment,ArrayList<Integer> mTitles) {
        super(fm);
        this.context=context;
        this.mFragment=mFragment;
        this.mTitles=mTitles;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragment.get(i);
    }

    @Override
    public int getCount() {
        return mFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(mTitles.get(position));
    }
}
