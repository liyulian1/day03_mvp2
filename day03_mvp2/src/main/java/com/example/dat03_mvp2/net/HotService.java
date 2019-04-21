package com.example.dat03_mvp2.net;

import com.example.dat03_mvp2.bean.HotBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface HotService {

    //http://news-at.zhihu.com/api/4/news/hot
    public String hoturl="http://news-at.zhihu.com/api/4/";
    @GET("news/hot")
    Observable<HotBean> getList();
}
