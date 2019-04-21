package com.example.dat03_mvp2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dat03_mvp2.R;
import com.example.dat03_mvp2.bean.BeforeNewsBean;
import com.example.dat03_mvp2.bean.DailyNewBean;
import com.example.dat03_mvp2.bean.StoriesBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RlvDaulyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<DailyNewBean.StoriesBean> mStoriesBean;
    private ArrayList<DailyNewBean.TopStoriesBean> mTopStoriesBean;
    private ArrayList<StoriesBean> mStoriesBeansa = new ArrayList<>();
    private static final int TYPE_BANNER=0;
    private static final int TYPE_TIME=1;
    private static final int TYPE_NEWS=2;

    private String mDate="今日要闻";
    private String mDate1;

    public RlvDaulyAdapter(Context context, ArrayList<DailyNewBean.StoriesBean> mStoriesBean, ArrayList<DailyNewBean.TopStoriesBean> mTopStoriesBean) {
        this.context = context;
        this.mStoriesBean = mStoriesBean;
        this.mTopStoriesBean = mTopStoriesBean;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder holder=null;
       if (i==TYPE_BANNER){
        //banner
           View inflate = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
           holder=new BannerVH(inflate);
       }else if (i==TYPE_TIME){
           //日期
           View inflate = LayoutInflater.from(context).inflate(R.layout.item_time, null);
           holder=new TimeVH(inflate);
       }else{
           //新闻
           View inflate = LayoutInflater.from(context).inflate(R.layout.item_trim2, null);
           holder=new NewsVH(inflate);
       }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int viewType = getItemViewType(i);
        if (viewType==TYPE_BANNER){
            //banner
            BannerVH bannerVH= (BannerVH) viewHolder;
            bannerVH.mBanner.setImages(mTopStoriesBean)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            DailyNewBean.TopStoriesBean bean= (DailyNewBean.TopStoriesBean) path;
                            Glide.with(context).load(bean.getImage()).into(imageView);
                        }
                    }).start();
        }else if (viewType==TYPE_TIME){
            //日期
            TimeVH timeVH= (TimeVH) viewHolder;
            timeVH.mTvTime.setText(mDate);
        }else{
            //新闻
            int newPostion=i-1;
            if (mTopStoriesBean.size()>0){
                newPostion-=1;
            }
            DailyNewBean.StoriesBean storiesBean = mStoriesBean.get(newPostion);
            NewsVH newsVH= (NewsVH) viewHolder;
            newsVH.mTitle.setText(storiesBean.getTitle());
            newsVH.mNei.setText(storiesBean.getGa_prefix());
            Glide.with(context).load(storiesBean.getImages().get(0)).into(newsVH.mImg2);
        }
    }

    public void addBeforeNews(BeforeNewsBean bean) {
        mDate = bean.getDate();

        mStoriesBeansa.clear();
        mTopStoriesBean.clear();
        if (bean.getStories() != null && bean.getStories().size() > 0){
            mStoriesBeansa.addAll(bean.getStories());
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTopStoriesBean.size()>0){
            return 1+1+mStoriesBean.size();
        }else{
            return 1+mStoriesBean.size();
        }
    }


    @Override
    public int getItemViewType(int position) {
      if (mTopStoriesBean.size()>0){
          if (position==0){
              return TYPE_BANNER;
          }else if (position==1){
              return TYPE_TIME;
          }else{
              return TYPE_NEWS;
          }
      }else{
          if (position==0){
              return TYPE_TIME;
          }else{
              return TYPE_NEWS;
          }
      }
    }

    public void setData(DailyNewBean bean) {
        mDate1 = bean.getDate();
        mTopStoriesBean.clear();
        if (bean.getTop_stories()!=null && bean.getTop_stories().size()>0){
            mTopStoriesBean.addAll(bean.getTop_stories());
        }

        mStoriesBean.clear();
        if (bean.getStories()!=null && bean.getStories().size()>0){
            mStoriesBean.addAll(bean.getStories());
        }
        notifyDataSetChanged();
    }

    class BannerVH extends RecyclerView.ViewHolder{
        @BindView(R.id.banner)
        Banner mBanner;
        public BannerVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class TimeVH extends RecyclerView.ViewHolder{
        @BindView(R.id.tvtimer)
        TextView mTvTime;
        public TimeVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    class NewsVH extends RecyclerView.ViewHolder{
        @BindView(R.id.img2)
        ImageView mImg2;
        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.nei)
        TextView mNei;
        public NewsVH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
