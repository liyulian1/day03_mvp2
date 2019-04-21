package com.example.dat03_mvp2.net;

import com.example.dat03_mvp2.bean.LoginBean;

/**
 * @author xts
 *         Created by asus on 2019/4/2.
 */

public interface ResultCallBack<T> {
    void onSuccess(T bean);
    void onFail(String msg);
}
