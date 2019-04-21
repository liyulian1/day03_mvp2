package com.example.dat03_mvp2.model;

import android.util.Log;

import com.example.dat03_mvp2.base.BaseModel;
import com.example.dat03_mvp2.bean.BeforeNewsBean;
import com.example.dat03_mvp2.bean.DailyNewBean;
import com.example.dat03_mvp2.net.BaseObserver;
import com.example.dat03_mvp2.net.BeforeNewsCallBack;
import com.example.dat03_mvp2.net.HttpUtils;
import com.example.dat03_mvp2.net.ResultCallBack;
import com.example.dat03_mvp2.net.RxUtils;
import com.example.dat03_mvp2.net.ZhihuService;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class DailyNesM extends BaseModel {
    public void getData(final ResultCallBack<DailyNewBean> resultCallBack) {
        ZhihuService apiserver = HttpUtils.getInstance().getApiserver(ZhihuService.sBaseUrl, ZhihuService.class);
        Observable<DailyNewBean> dailyNew = apiserver.getDailyNew();
        dailyNew.compose(RxUtils.<DailyNewBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DailyNewBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                            mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(DailyNewBean dailyNewBean) {
                        resultCallBack.onSuccess(dailyNewBean);
                    }
                });
    }

    public void getBeforeNewsData(String date, final BeforeNewsCallBack callBack) {
        ZhihuService apiserver = HttpUtils.getInstance().getApiserver(ZhihuService.sBaseUrl, ZhihuService.class);

        Observable<BeforeNewsBean> observable = apiserver.getBeforeNewsData(date);

        observable.compose(RxUtils.<BeforeNewsBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<BeforeNewsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(BeforeNewsBean beforeNewsBean) {
                        Log.i("tag", "onNext: "+beforeNewsBean.getStories().size());
                        callBack.onBeforeSuccess(beforeNewsBean);
                    }
                });
    }
}
