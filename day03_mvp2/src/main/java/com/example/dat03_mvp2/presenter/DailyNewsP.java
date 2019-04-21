package com.example.dat03_mvp2.presenter;

import com.example.dat03_mvp2.base.BasePresenter;
import com.example.dat03_mvp2.bean.BeforeNewsBean;
import com.example.dat03_mvp2.bean.DailyNewBean;
import com.example.dat03_mvp2.model.DailyNesM;
import com.example.dat03_mvp2.net.BeforeNewsCallBack;
import com.example.dat03_mvp2.net.ResultCallBack;
import com.example.dat03_mvp2.view.DailyNewsV;

public class DailyNewsP extends BasePresenter<DailyNewsV> {
   private DailyNesM dailyNesM;
    @Override
    protected void initModel() {
        dailyNesM=new DailyNesM();
        mModels.add(dailyNesM);
    }
  public void getData(){
        dailyNesM.getData(new ResultCallBack<DailyNewBean>() {
            @Override
            public void onSuccess(DailyNewBean bean) {
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

    public void getBeforeNewsData(String date) {
        dailyNesM.getBeforeNewsData(date, new BeforeNewsCallBack() {
            @Override
            public void onBeforeSuccess(BeforeNewsBean beforeNewsBean) {
                if (mMvpView != null){
                    mMvpView.onBeforeNewsData(beforeNewsBean);
                }
            }
        });
    }

}
