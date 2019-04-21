package com.example.dat03_mvp2.fragment;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.dat03_mvp2.R;
import com.example.dat03_mvp2.activity.ShowActivity;
import com.example.dat03_mvp2.adapter.VpGoldAdapter;
import com.example.dat03_mvp2.base.BaseFragment;
import com.example.dat03_mvp2.base.Constants;
import com.example.dat03_mvp2.bean.GoldTitleBean;
import com.example.dat03_mvp2.presenter.GoldP;
import com.example.dat03_mvp2.view.GoldV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class GoldFragment extends BaseFragment<GoldV, GoldP>
        implements GoldV {
    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.vp)
    ViewPager mVp;
    private View view;
    private ArrayList<GoldTitleBean> mTitle;
    private ArrayList<BaseFragment> mFragment;


    @Override
    protected GoldP initPresenter() {
        return new GoldP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gold;
    }

    @Override
    protected void initView() {
        initTitle();
        setFragments();
    }

    private void setFragments() {
        initFragments();
        VpGoldAdapter adapter = new VpGoldAdapter(getChildFragmentManager(), mFragment, mTitle);
        mVp.setAdapter(adapter);
        mTablayout.setupWithViewPager(mVp);
    }
    @OnClick({R.id.iv})
    public void click(View v){
        switch (v.getId()) {
            case R.id.iv:
                go2ShowActivity();
                break;
        }
    }

    private void go2ShowActivity() {
        Intent intent = new Intent(getContext(), ShowActivity.class);
        intent.putExtra(Constants.DATA,mTitle);
        startActivityForResult(intent,100);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null){
            if (requestCode == 100 && resultCode == Activity.RESULT_OK){
                mTitle = (ArrayList<GoldTitleBean>) data.getSerializableExtra(Constants.DATA);
                setFragments();
            }
        }
    }
    private void initFragments() {
        mFragment = new ArrayList<>();
        for (int i = 0; i < mTitle.size(); i++) {
            GoldTitleBean goldTitleBean = mTitle.get(i);
            if (goldTitleBean.isChecked){
                mFragment.add(GoldDetailFragment.newInstance(goldTitleBean.title));
            }
        }
    }

    private void initTitle() {
        mTitle = new ArrayList<>();
        mTitle.add(new GoldTitleBean("Android",true));
        mTitle.add(new GoldTitleBean("iOS",true));
        mTitle.add(new GoldTitleBean("设计",true));
        mTitle.add(new GoldTitleBean("工具资源",true));
        mTitle.add(new GoldTitleBean("产品",true));
        mTitle.add(new GoldTitleBean("阅读",true));
        mTitle.add(new GoldTitleBean("前端",true));
        mTitle.add(new GoldTitleBean("后端",true));
    }


}
