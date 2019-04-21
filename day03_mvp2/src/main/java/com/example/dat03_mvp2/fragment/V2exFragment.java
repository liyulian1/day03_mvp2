package com.example.dat03_mvp2.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.example.dat03_mvp2.R;
import com.example.dat03_mvp2.adapter.VpV2exAdapter;
import com.example.dat03_mvp2.base.BaseFragment;
import com.example.dat03_mvp2.bean.V2exTabBean;
import com.example.dat03_mvp2.presenter.V2exP;
import com.example.dat03_mvp2.view.V2exV;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;

//王振  1808a
public class V2exFragment extends BaseFragment<V2exV, V2exP>
        implements V2exV {

    private String mUrl = "https://www.v2ex.com/";
    private View view;

    @BindView(R.id.tab)
    TabLayout mTab;
    @BindView(R.id.vp)
    ViewPager mVp;
    private ArrayList<Fragment> list;
    private VpV2exAdapter vpV2exAdapter;

    @Override
    protected V2exP initPresenter() {
        return new V2exP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_v2ex;
    }

    private static final String TAG = "V2exFragment";

    @Override
    protected void initData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(mUrl).get();
                    //class等于mashead的div标签
                    Element tabs = doc.select("div#Tabs").first();
                    Elements allTabs = tabs.select("a[href]");
                    final ArrayList<V2exTabBean> tabslist = new ArrayList<>();
                    for (Element element : allTabs) {
                        //获取href属性
                        String linlHref = element.attr("href");
                        //获取标签里面文本
                        String linkText = element.text();
                        Log.d(TAG, "linlHref: " + linlHref + ",tab:" + linkText);
                        V2exTabBean v2exTabBean = new V2exTabBean(linlHref, linkText);
                        tabslist.add(v2exTabBean);
                    }
                    Log.d(TAG, "aaaaaaaaaaaa" + tabslist.toString());
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (tabslist!=null && tabslist.size()>0){
                                list = new ArrayList<>();
                                for (int i = 0; i < tabslist.size(); i++) {
                                    list.add(new V2exItemFragment(tabslist.get(i).link));
                                    mTab.addTab(mTab.newTab().setText(tabslist.get(i).tab));
                                }
                                vpV2exAdapter = new VpV2exAdapter(getChildFragmentManager(), list);
                                mVp.setAdapter(vpV2exAdapter);
                                mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                    @Override
                                    public void onTabSelected(TabLayout.Tab tab) {
                                        mVp.setCurrentItem(tab.getPosition());
                                    }

                                    @Override
                                    public void onTabUnselected(TabLayout.Tab tab) {

                                    }

                                    @Override
                                    public void onTabReselected(TabLayout.Tab tab) {

                                    }
                                });
                                mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    @Override
    protected void initView() {
        super.initView();
    }
}
