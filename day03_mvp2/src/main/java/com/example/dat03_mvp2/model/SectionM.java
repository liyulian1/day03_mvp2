package com.example.dat03_mvp2.model;

import com.example.dat03_mvp2.base.BaseModel;
import com.example.dat03_mvp2.bean.SectionBean;
import com.example.dat03_mvp2.net.BaseObserver;
import com.example.dat03_mvp2.net.HttpUtils;
import com.example.dat03_mvp2.net.ResultCallBack;
import com.example.dat03_mvp2.net.RxUtils;
import com.example.dat03_mvp2.net.SectionService;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class SectionM extends BaseModel {
    public void getData(final ResultCallBack<SectionBean> resultCallBack) {
        SectionService apiserver = HttpUtils.getInstance().getApiserver(SectionService.sectionUrl, SectionService.class);
        Observable<SectionBean> list = apiserver.getList();
        list.compose(RxUtils.<SectionBean>rxObserableSchedulerHelper())
            .subscribe(new BaseObserver<SectionBean>() {
                @Override
                public void onSubscribe(Disposable d) {
                    mCompositeDisposable.add(d);
                }

                @Override
                public void onNext(SectionBean sectionBean) {
                    resultCallBack.onSuccess(sectionBean);
                }
            });


    }
}
