package com.example.dat03_mvp2.net;

import com.example.dat03_mvp2.bean.BeforeNewsBean;
import com.example.dat03_mvp2.bean.DailyNewBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ZhihuService {
//http://news-at.zhihu.com/api/4/news/latest
    public String  sBaseUrl="http://news-at.zhihu.com/api/4/";
    @GET("news/latest")
    Observable<DailyNewBean> getDailyNew();


    @GET("news/before/{date}")
    Observable<BeforeNewsBean> getBeforeNewsData(@Path("date") String date);

}
