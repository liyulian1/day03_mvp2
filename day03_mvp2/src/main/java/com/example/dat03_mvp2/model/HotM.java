package com.example.dat03_mvp2.model;

import com.example.dat03_mvp2.base.BaseModel;
import com.example.dat03_mvp2.bean.HotBean;
import com.example.dat03_mvp2.net.BaseObserver;
import com.example.dat03_mvp2.net.HotService;
import com.example.dat03_mvp2.net.HttpUtils;
import com.example.dat03_mvp2.net.ResultCallBack;
import com.example.dat03_mvp2.net.RxUtils;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class HotM extends BaseModel {
    public void getData(final ResultCallBack<HotBean> resultCallBack) {
        HotService apiserver = HttpUtils.getInstance().getApiserver(HotService.hoturl, HotService.class);
        Observable<HotBean> list = apiserver.getList();
        list.compose(RxUtils.<HotBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HotBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(HotBean hotBean) {
                        resultCallBack.onSuccess(hotBean);
                    }
                });
    }
}
