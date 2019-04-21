package com.example.dat03_mvp2.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.dat03_mvp2.R;
import com.example.dat03_mvp2.base.BaseActivity;
import com.example.dat03_mvp2.fragment.AboutFragment;
import com.example.dat03_mvp2.fragment.CollectFragment;
import com.example.dat03_mvp2.fragment.GankFragment;
import com.example.dat03_mvp2.fragment.GoldFragment;
import com.example.dat03_mvp2.fragment.SettingFragment;
import com.example.dat03_mvp2.fragment.V2exFragment;
import com.example.dat03_mvp2.fragment.WeChatFragment;
import com.example.dat03_mvp2.fragment.ZhihuDailyNewsFragment;
import com.example.dat03_mvp2.presenter.MainP;
import com.example.dat03_mvp2.util.ToastUtil;
import com.example.dat03_mvp2.view.MainV;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainV, MainP> implements MainV {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.nv)
    NavigationView mNv;
    @BindView(R.id.dl)
    DrawerLayout mDl;
    @BindView(R.id.search_view)
    MaterialSearchView mSearchView;
    private FragmentManager manager;
    private ArrayList<Fragment> fragments;
    private ArrayList<Integer> integers;
    private final int TYPE_ZHIHU=0;
    private final int TYPE_WECHAT=1;
    private final int TYPE_GANK=2;
    private final int TYPE_GOLD=3;
    private final int TYPE_V2EX=4;
    private final int TYPE_COLLECT=5;
    private final int TYPE_SETTING=6;
    private final int TYPE_ABOUT=7;
    private int listFragmentPosition=0;//上一次显示的fragment的索引
    private MenuItem mSearchItem;


    @Override
    protected MainP initPresenter() {
        return new MainP();
}

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        manager = getSupportFragmentManager();
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mToolbar, 0, 0);
        //设置旋转开关的颜色
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        mDl.addDrawerListener(toggle);
        toggle.syncState();
        
        initFragments();
        initTitles();
        addZhihuDailyNewsFragment();
    }

    private void addZhihuDailyNewsFragment() {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container,fragments.get(0));
        transaction.commit();

        mToolbar.setTitle(integers.get(0));
    }

    private void initTitles() {
        integers = new ArrayList<>();
        integers.add(R.id.zhihu);
        integers.add(R.id.weixin);
        integers.add(R.id.gank);
        integers.add(R.id.gold);
        integers.add(R.id.v2ex);
        integers.add(R.id.collect);
        integers.add(R.id.settings);
        integers.add(R.id.about);
    }

    private void initFragments() {
        fragments = new ArrayList<>();
        fragments.add(new ZhihuDailyNewsFragment());
        fragments.add(new WeChatFragment());
        fragments.add(new GankFragment());
        fragments.add(new GoldFragment());
        fragments.add(new V2exFragment());
        fragments.add(new CollectFragment());
        fragments.add(new SettingFragment());
        fragments.add(new AboutFragment());
    }

    @Override
    protected void initListener() {
        mNv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId!=R.id.info_title&& itemId!=R.id.option_title){
                    menuItem.setChecked(true);
                    switch (itemId){
                        case R.id.zhihu:
                            switchFragments(TYPE_ZHIHU);
                            break;
                        case R.id.weixin:
                            switchFragments(TYPE_WECHAT);
                            break;
                        case R.id.gank:
                            switchFragments(TYPE_GANK);
                            break;
                        case R.id.gold:
                            switchFragments(TYPE_GOLD);
                            break;
                        case R.id.v2ex:
                            switchFragments(TYPE_V2EX);
                            break;
                        case R.id.collect:
                            switchFragments(TYPE_COLLECT);
                            break;
                        case R.id.settings:
                            switchFragments(TYPE_SETTING);
                            break;
                        case R.id.about:
                            switchFragments(TYPE_ABOUT);
                            break;
                    }
                    mDl.closeDrawer(Gravity.LEFT);
                }else{
                    menuItem.setChecked(false);
                }
                return false;
            }
        });

        mSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //提交搜索内容时的监听
                //ToastUtil.showShort("提交的内容:"+query);
                EventBus.getDefault().postSticky(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //文本发生改变的监听
                //ToastUtil.showShort(newText);
                return false;
            }
        });

        mSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //搜索框展开
                ToastUtil.showShort("展开");
            }

            @Override
            public void onSearchViewClosed() {
                //搜索框关闭
                ToastUtil.showShort("关闭");
            }
        });

        //显示提示信息
      //  mSearchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));

    }
    private void switchFragments(int type) {
        //本质显示一个fragment，隐藏一个
        //要显示的fragment
        Fragment fragment = fragments.get(type);
        //要隐藏的碎片
        Fragment fragment1 = fragments.get(listFragmentPosition);
        FragmentTransaction transaction = manager.beginTransaction();
        if (!fragment.isAdded()){
            transaction.add(R.id.fragment_container,fragment);
        }
        transaction.hide(fragment1);
        transaction.show(fragment);
        transaction.commit();
        listFragmentPosition=type;
//显示隐藏搜索框
        if (type == TYPE_WECHAT|| type== TYPE_GANK){
            mSearchItem.setVisible(true);
        }else {
            mSearchItem.setVisible(false);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        mSearchItem = menu.findItem(R.id.action_search);
        mSearchItem.setVisible(false);
        mSearchView.setMenuItem(mSearchItem);

        return true;
    }

    /**
     * 按回退键会调用这个方法
     */
    @Override
    public void onBackPressed() {
        if (mSearchView.isSearchOpen()) {
            mSearchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
