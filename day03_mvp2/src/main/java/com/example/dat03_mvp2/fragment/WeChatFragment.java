package com.example.dat03_mvp2.fragment;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.dat03_mvp2.R;
import com.example.dat03_mvp2.adapter.RlvWeChatAdapter;
import com.example.dat03_mvp2.base.BaseFragment;
import com.example.dat03_mvp2.bean.WeChatBean;
import com.example.dat03_mvp2.net.WeChatService2;
import com.example.dat03_mvp2.presenter.WeChatP;
import com.example.dat03_mvp2.view.WeChatV;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//王振    1808A
public class WeChatFragment extends BaseFragment<WeChatV,WeChatP>
implements WeChatV{
   @BindView(R.id.rl)
    RecyclerView mRl;

    private ArrayList<WeChatBean.NewslistBean> datalist=new ArrayList<>();
    private RlvWeChatAdapter rlvWeChatAdapter;
    private String aaa;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            initData();
        }else{
            datalist.clear();
            initD();
        }
    }

    @Override
    protected WeChatP initPresenter() {
        return new WeChatP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void initView() {
        super.initView();
        mRl.setLayoutManager(new LinearLayoutManager(getContext()));

        rlvWeChatAdapter = new RlvWeChatAdapter(getContext(), datalist);
        mRl.setAdapter(rlvWeChatAdapter);
        mRl.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.HORIZONTAL));
        EventBus.getDefault().register(this);
        initD();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private static final String TAG = "WeChatFragment";
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
        public void onEnve(String query) {
        Log.d(TAG, "onEnve: aaaaaaaaaaaaaaa"+query);
        aaa = query;
        }

    private void initD() {
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(WeChatService2.weUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        WeChatService2 weChatService2 = retrofit.create(WeChatService2.class);
        final Observable<WeChatBean> data = weChatService2.getData(aaa);
        data.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeChatBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeChatBean weChatBean) {
                        datalist.clear();
                        datalist.addAll(weChatBean.getNewslist());
                        rlvWeChatAdapter.setList(datalist);
                        rlvWeChatAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void initData() {
        super.initData();
        presenter.getData();
    }

    @Override
    public void setData(WeChatBean weChatBean) {
        datalist.addAll(weChatBean.getNewslist());
        rlvWeChatAdapter.setList(datalist);
        rlvWeChatAdapter.notifyDataSetChanged();
    }
}
