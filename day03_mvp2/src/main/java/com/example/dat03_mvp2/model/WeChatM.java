package com.example.dat03_mvp2.model;

import com.example.dat03_mvp2.base.BaseModel;
import com.example.dat03_mvp2.bean.WeChatBean;
import com.example.dat03_mvp2.net.BaseObserver;
import com.example.dat03_mvp2.net.HttpUtils;
import com.example.dat03_mvp2.net.ResultCallBack;
import com.example.dat03_mvp2.net.RxUtils;
import com.example.dat03_mvp2.net.WeChatService;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class WeChatM extends BaseModel {
    public void getData(final ResultCallBack<WeChatBean> resultCallBack) {
        WeChatService apiserver = HttpUtils.getInstance().getApiserver(WeChatService.wechaturl, WeChatService.class);
        Observable<WeChatBean> getlist = apiserver.getlist();
        getlist.compose(RxUtils.<WeChatBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WeChatBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                            mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(WeChatBean dailyNewBean) {
                        resultCallBack.onSuccess(dailyNewBean);
                    }
                });
    }
}
