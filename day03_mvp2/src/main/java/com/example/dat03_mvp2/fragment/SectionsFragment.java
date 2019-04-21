package com.example.dat03_mvp2.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.dat03_mvp2.R;
import com.example.dat03_mvp2.adapter.RlvSectionAdapter;
import com.example.dat03_mvp2.base.BaseFragment;
import com.example.dat03_mvp2.bean.SectionBean;
import com.example.dat03_mvp2.presenter.SectionP;
import com.example.dat03_mvp2.view.SectionV;

import java.util.ArrayList;

import butterknife.BindView;

public class SectionsFragment extends BaseFragment<SectionV,SectionP>
implements SectionV{
    @BindView(R.id.rl)
    RecyclerView mRl;
    private RlvSectionAdapter rlvSectionAdapter;
    private ArrayList<SectionBean.DataBean> dataBeans;

    @Override
    protected SectionP initPresenter() {
        return new SectionP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sections;
    }

    @Override
    protected void initView() {
        super.initView();
        mRl.setLayoutManager(new GridLayoutManager(getActivity(),2));
        dataBeans = new ArrayList<>();
        rlvSectionAdapter = new RlvSectionAdapter(getActivity(), dataBeans);
        mRl.setAdapter(rlvSectionAdapter);

    }

    @Override
    protected void initData() {
        super.initData();
        presenter.getData();

    }

    @Override
    public void setData(SectionBean bean) {
        dataBeans.addAll(bean.getData());
        rlvSectionAdapter.setList(dataBeans);
        rlvSectionAdapter.notifyDataSetChanged();
    }
}
