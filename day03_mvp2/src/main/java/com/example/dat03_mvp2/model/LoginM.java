package com.example.dat03_mvp2.model;

import com.example.dat03_mvp2.base.BaseModel;
import com.example.dat03_mvp2.bean.LoginBean;
import com.example.dat03_mvp2.net.ApiService;
import com.example.dat03_mvp2.net.BaseObserver;
import com.example.dat03_mvp2.net.HttpUtils;
import com.example.dat03_mvp2.net.ResultCallBack;
import com.example.dat03_mvp2.net.RxUtils;
import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 */

public class LoginM extends BaseModel {
    public void login(String userName, String psd, final ResultCallBack callBack) {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(ApiService.sBaseUrl)
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        ApiService apiService = retrofit.create(ApiService.class);
//
//        Observable<LoginBean> login = apiService.login(userName, psd);
//        login.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<LoginBean>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        //Disposable
//                        //用来掐断观察者和被观察者之间的联系,调用的时机是页面退出的时候
//                        //d.dispose();
//                        mCompositeDisposable.add(d);
//                    }
//
//                    @Override
//                    public void onNext(LoginBean loginBean) {
//                        callBack.onSuccess(loginBean);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        callBack.onFail(e.toString());
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        ApiService apiserver = HttpUtils.getInstance().getApiserver(ApiService.sBaseUrl, ApiService.class);
        Observable<LoginBean> login = apiserver.login(userName, psd);
        login.compose(RxUtils.<LoginBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        callBack.onSuccess(loginBean);
                    }
                });
    }
}
