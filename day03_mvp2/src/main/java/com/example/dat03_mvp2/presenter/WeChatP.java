package com.example.dat03_mvp2.presenter;

import com.example.dat03_mvp2.base.BasePresenter;
import com.example.dat03_mvp2.bean.WeChatBean;
import com.example.dat03_mvp2.model.WeChatM;
import com.example.dat03_mvp2.net.ResultCallBack;
import com.example.dat03_mvp2.view.WeChatV;

public class WeChatP extends BasePresenter<WeChatV> {
    WeChatM weChatM;
    @Override
    protected void initModel() {
        weChatM=new WeChatM();
        mModels.add(weChatM);
    }

    public void getData(){
        weChatM.getData(new ResultCallBack<WeChatBean>() {
            @Override
            public void onSuccess(WeChatBean bean) {
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
