package com.example.dat03_mvp2.presenter;


import android.text.TextUtils;

import com.example.dat03_mvp2.base.BasePresenter;
import com.example.dat03_mvp2.bean.LoginBean;
import com.example.dat03_mvp2.model.LoginM;
import com.example.dat03_mvp2.net.ResultCallBack;
import com.example.dat03_mvp2.util.Logger;
import com.example.dat03_mvp2.view.LoginView;

public class LoginP extends BasePresenter<LoginView> {
    private static final String TAG = "MainP";
    private LoginM mMainM;

    public void getData(){
        //从网络拿数据
        String data = "请求回来的数据";
        if (mMvpView != null){
            //为了避免每次都强转,使用泛型
            //((LoginView)mMvpView).setData(data);
            mMvpView.setData(data);
        }
    }

    public void login() {
        String userName = mMvpView.getUserName();
        String psd = mMvpView.getPsd();

        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(psd)){
            mMvpView.showToast("用户名或者密码不能为空!");
            return;
        }


        //登录的逻辑,网络请求,M
        mMainM.login(userName,psd, new ResultCallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean bean) {
                if (bean !=null){
                    Logger.logD(TAG,bean.toString());
                    if (bean.getCode() == 200){
                        //如果页面销毁了,网络请求没有销毁,一会数据请求成功后调用mMvpView的
                        //方法的时候就会空指针
                        if (mMvpView != null){
                            mMvpView.showToast("登录成功");
                        }
                    }else {
                        if (mMvpView !=null){
                            mMvpView.showToast("登录失败");
                        }
                    }
                }
            }


            @Override
            public void onFail(String msg) {
                if (mMvpView != null){
                    mMvpView.showToast("登录失败");
                    Logger.logD(TAG,msg);
                }
            }
        });
    }

    @Override
    protected void initModel() {
        mMainM = new LoginM();
        mModels.add(mMainM);
    }
}
