package com.example.dat03_mvp2.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.dat03_mvp2.R;
import com.example.dat03_mvp2.adapter.VpZhihuAdapter;
import com.example.dat03_mvp2.base.BaseFragment;
import com.example.dat03_mvp2.presenter.ZhihuDaulyBewsP;
import com.example.dat03_mvp2.view.ZhihuDaulyBewsV;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;

//王振
public class ZhihuDailyNewsFragment extends BaseFragment<ZhihuDaulyBewsV, ZhihuDaulyBewsP>
        implements ZhihuDaulyBewsV {
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.vp)
    ViewPager mVp;

    private View view;
    private Unbinder unbinder;
    private ArrayList<Integer> titles;
    private ArrayList<BaseFragment> baseFragments;

    @Override
    protected ZhihuDaulyBewsP initPresenter() {
        return new ZhihuDaulyBewsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void initView() {
        super.initView();
        initTitles();
        initFragments();

        VpZhihuAdapter adapter = new VpZhihuAdapter(getChildFragmentManager(), getActivity(), baseFragments, titles);
        mVp.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mVp);

    }






    private void initFragments() {
        baseFragments = new ArrayList<>();
        baseFragments.add(new DailyNewsFragment());
        baseFragments.add(new SectionsFragment());
        baseFragments.add(new HotFragment());
    }

    private void initTitles() {
        titles = new ArrayList<>();
        titles.add(R.string.dailynews);
        titles.add(R.string.sections);
        titles.add(R.string.hot);
    }
}
