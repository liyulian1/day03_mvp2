package com.example.dat03_mvp2.net;

import com.example.dat03_mvp2.bean.SectionBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface SectionService {

    //http://news-at.zhihu.com/api/4/sections
    public String sectionUrl="http://news-at.zhihu.com/api/4/";
    @GET("sections")
    Observable<SectionBean> getList();
}
