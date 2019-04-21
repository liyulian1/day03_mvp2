package com.example.dat03_mvp2.fragment;

import com.example.dat03_mvp2.R;
import com.example.dat03_mvp2.base.BaseFragment;
import com.example.dat03_mvp2.presenter.GankP;
import com.example.dat03_mvp2.view.GankV;

public class GankFragment extends BaseFragment<GankV,GankP>
implements GankV{
    @Override
    protected GankP initPresenter() {
        return new GankP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank;
    }
}
