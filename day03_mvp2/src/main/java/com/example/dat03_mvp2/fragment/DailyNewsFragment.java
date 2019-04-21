package com.example.dat03_mvp2.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dat03_mvp2.R;
import com.example.dat03_mvp2.activity.CalendarActivity;
import com.example.dat03_mvp2.adapter.RlvDaulyAdapter;
import com.example.dat03_mvp2.base.BaseFragment;
import com.example.dat03_mvp2.bean.BeforeNewsBean;
import com.example.dat03_mvp2.bean.DailyNewBean;
import com.example.dat03_mvp2.presenter.DailyNewsP;
import com.example.dat03_mvp2.view.DailyNewsV;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.Unbinder;

public class DailyNewsFragment extends BaseFragment<DailyNewsV, DailyNewsP>
        implements DailyNewsV {
    @BindView(R.id.rl)
    RecyclerView mRl;
    private View view;
    private RlvDaulyAdapter rlvDaulyAdapter;
    private Unbinder unbinder;
    @BindView(R.id.fab_calender)
    FloatingActionButton mFloating_btn;

    @Override
    protected DailyNewsP initPresenter() {
        return new DailyNewsP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dailynews;
    }


    @Override
    protected void initView() {
        super.initView();
        mRl.setLayoutManager(new LinearLayoutManager(getActivity()));
        ArrayList<DailyNewBean.StoriesBean> storiesBeans = new ArrayList<>();
        ArrayList<DailyNewBean.TopStoriesBean> topStoriesBeans = new ArrayList<>();
        rlvDaulyAdapter = new RlvDaulyAdapter(getActivity(), storiesBeans, topStoriesBeans);
        mRl.setAdapter(rlvDaulyAdapter);

        initFloatingActionButton();

    }

    @Override
    protected void initData() {
        presenter.getData();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200){
            String date = data.getStringExtra("date");

            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
            String todayDate = df.format(new Date());

            if (date.equals(todayDate)){
                presenter.getData();
            }else {
                //因为获取出来的数据是当前日期的前一天,所以要给他加1
                int intDate = Integer.valueOf(date);
                intDate += 1;
                presenter.getBeforeNewsData(String.valueOf(intDate));
            }
        }
    }
    @Override
    public void setData(DailyNewBean bean) {
        rlvDaulyAdapter.setData(bean);
    }

    @Override
    public void onBeforeNewsData(BeforeNewsBean beforeNewsBean) {
        rlvDaulyAdapter.addBeforeNews(beforeNewsBean);

    }

    private void initFloatingActionButton() {
        mFloating_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startAnim();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void startAnim() {
        int[] location = new int[2];
        mFloating_btn.getLocationInWindow(location);
        final int cx = location[0] + mFloating_btn.getWidth() / 2;
        final int cy = location[1] + mFloating_btn.getHeight() / 2;
        final ImageView view = new ImageView(getContext());
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setImageResource(R.color.colorAccent);
        final ViewGroup decorView = (ViewGroup) getActivity().getWindow().getDecorView();
        int w = decorView.getWidth();
        int h = decorView.getHeight();
        decorView.addView(view, w, h);

        // 计算中心点至view边界的最大距离
        int maxW = Math.max(cx, w - cx);
        int maxH = Math.max(cy, h - cy);
        final int finalRadius = (int) Math.sqrt(maxW * maxW + maxH * maxH) + 1;
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
        int maxRadius = (int) Math.sqrt(w * w + h * h) + 1;
        // 若使用默认时长，则需要根据水波扩散的距离来计算实际时间
//        if (durationMills == PERFECT_MILLS) {
//            // 算出实际边距与最大边距的比率
//            double rate = 1d * finalRadius / maxRadius;
//            // 水波扩散的距离与扩散时间成正比
//            durationMills = (long) (PERFECT_MILLS * rate);
//        }
//        final long finalDuration = durationMills;
        //anim.setDuration(finalDuration);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Intent intent = new Intent(getContext(), CalendarActivity.class);
                startActivityForResult(intent, 100);
                // 默认渐隐过渡动画.
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

                // 默认显示返回至当前Activity的动画.
                mFloating_btn.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Animator anim =
                                ViewAnimationUtils.createCircularReveal(view, cx, cy, finalRadius, 0);
                        //  anim.setDuration(finalDuration);
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                try {
                                    decorView.removeView(view);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        anim.start();
                    }
                }, 1000);
            }
        });
        anim.start();
    }


}
