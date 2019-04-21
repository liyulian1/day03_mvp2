package com.example.dat03_mvp2.view;

import com.example.dat03_mvp2.base.BaseMvpView;
import com.example.dat03_mvp2.bean.BeforeNewsBean;
import com.example.dat03_mvp2.bean.DailyNewBean;

public interface DailyNewsV extends BaseMvpView {
    void setData(DailyNewBean bean);

    void onBeforeNewsData(BeforeNewsBean beforeNewsBean);
}
