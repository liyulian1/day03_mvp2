package com.example.dat03_mvp2.presenter;

import com.example.dat03_mvp2.base.BasePresenter;
import com.example.dat03_mvp2.bean.HotBean;
import com.example.dat03_mvp2.model.HotM;
import com.example.dat03_mvp2.net.ResultCallBack;
import com.example.dat03_mvp2.view.HotV;

public class HotP extends BasePresenter<HotV> {
    HotM hotM;
    @Override
    protected void initModel() {
    hotM=new HotM();
    mModels.add(hotM);
    }

    public void getData(){
        hotM.getData(new ResultCallBack<HotBean>() {
            @Override
            public void onSuccess(HotBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
