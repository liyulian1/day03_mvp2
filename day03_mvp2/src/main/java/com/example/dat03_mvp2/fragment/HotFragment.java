package com.example.dat03_mvp2.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dat03_mvp2.R;
import com.example.dat03_mvp2.activity.WebActivity;
import com.example.dat03_mvp2.adapter.RlvHotAdapter;
import com.example.dat03_mvp2.base.BaseFragment;
import com.example.dat03_mvp2.bean.HotBean;
import com.example.dat03_mvp2.presenter.HotP;
import com.example.dat03_mvp2.view.HotV;

import java.util.ArrayList;

import butterknife.BindView;

public class HotFragment extends BaseFragment<HotV,HotP>
implements HotV{

    @BindView(R.id.rl)
    RecyclerView mRl;
    private ArrayList<HotBean.RecentBean> recentBeans;
    private RlvHotAdapter rlvHotAdapter;

    @Override
    protected HotP initPresenter() {
        return new HotP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initView() {
        super.initView();
        mRl.setLayoutManager(new LinearLayoutManager(getActivity()));
        recentBeans = new ArrayList<>();
        rlvHotAdapter = new RlvHotAdapter(getActivity(), recentBeans);
        mRl.setAdapter(rlvHotAdapter);
        rlvHotAdapter.setOnClicker(new RlvHotAdapter.OnClicker() {
            @Override
            public void onClicker(String url) {
                Intent intent = new Intent(getContext(), WebActivity.class);
                intent.putExtra("url",url);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();
        presenter.getData();
    }

    @Override
    public void setData(HotBean bean) {
        recentBeans.addAll(bean.getRecent());
        rlvHotAdapter.setList(recentBeans);
        rlvHotAdapter.notifyDataSetChanged();
    }
}
