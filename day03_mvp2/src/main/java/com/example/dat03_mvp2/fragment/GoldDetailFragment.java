package com.example.dat03_mvp2.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.example.dat03_mvp2.R;
import com.example.dat03_mvp2.base.BaseFragment;
import com.example.dat03_mvp2.base.Constants;
import com.example.dat03_mvp2.presenter.EmptyP;
import com.example.dat03_mvp2.view.EmptyV;

import butterknife.BindView;

public class GoldDetailFragment extends BaseFragment<EmptyV,EmptyP>
implements EmptyV{


    @BindView(R.id.tv)
    TextView mTv;

    public static GoldDetailFragment newInstance(String text){
        GoldDetailFragment goldDetailFragment = new GoldDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.DATA,text);
        goldDetailFragment.setArguments(bundle);
        return goldDetailFragment;
    }

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold_detail;
    }

    @Override
    protected void initView() {
        Bundle arguments = getArguments();
        String data = arguments.getString(Constants.DATA);
        mTv.setText(data);
    }
}
