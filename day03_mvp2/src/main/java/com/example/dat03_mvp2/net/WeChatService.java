package com.example.dat03_mvp2.net;

import com.example.dat03_mvp2.bean.WeChatBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface WeChatService {
    //http://api.tianapi.com/wxnew/?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1

    public String wechaturl="http://api.tianapi.com/";
    @GET("wxnew/?key=52b7ec3471ac3bec6846577e79f20e4c&num=10&page=1")
    Observable<WeChatBean> getlist();

}
