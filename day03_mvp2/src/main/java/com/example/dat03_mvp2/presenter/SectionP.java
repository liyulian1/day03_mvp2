package com.example.dat03_mvp2.presenter;

import com.example.dat03_mvp2.base.BasePresenter;
import com.example.dat03_mvp2.bean.SectionBean;
import com.example.dat03_mvp2.model.SectionM;
import com.example.dat03_mvp2.net.ResultCallBack;
import com.example.dat03_mvp2.view.SectionV;

public class SectionP extends BasePresenter<SectionV> {
    private  SectionM sectionM;
    @Override
    protected void initModel() {
        sectionM=new SectionM();
        mModels.add(sectionM);
    }

    public void getData(){
        sectionM.getData(new ResultCallBack<SectionBean>() {
            @Override
            public void onSuccess(SectionBean bean) {
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
