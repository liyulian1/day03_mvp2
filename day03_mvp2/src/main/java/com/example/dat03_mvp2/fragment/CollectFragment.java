package com.example.dat03_mvp2.fragment;

import com.example.dat03_mvp2.R;
import com.example.dat03_mvp2.base.BaseFragment;
import com.example.dat03_mvp2.presenter.EmptyP;
import com.example.dat03_mvp2.view.EmptyV;

public class CollectFragment extends BaseFragment<EmptyV,EmptyP>
implements EmptyV{
    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collect;
    }
}
