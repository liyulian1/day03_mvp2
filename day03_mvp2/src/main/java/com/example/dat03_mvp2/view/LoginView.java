package com.example.dat03_mvp2.view;

import com.example.dat03_mvp2.base.BaseMvpView;

public interface LoginView extends BaseMvpView {
    void setData(String data);
    String getUserName();
    String getPsd();

    void showToast(String msg);
}
