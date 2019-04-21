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
import com.example.dat03_mvp2.bean.HotBean;

import java.util.ArrayList;

public class RlvHotAdapter extends RecyclerView.Adapter<RlvHotAdapter.ViewHandler> {
    private Context context;
    private ArrayList<HotBean.RecentBean> list;

    public RlvHotAdapter(Context context, ArrayList<HotBean.RecentBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(ArrayList<HotBean.RecentBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHandler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_hot, null);
        return new ViewHandler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHandler viewHandler, final int i) {
        Glide.with(context).load(list.get(i).getThumbnail()).into(viewHandler.img);
        viewHandler.text.setText(list.get(i).getTitle());
        viewHandler.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClicker.onClicker(list.get(i).getUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHandler extends RecyclerView.ViewHolder {
       private ImageView img;
       private TextView text;
        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            text=itemView.findViewById(R.id.text);
        }
    }
    private OnClicker onClicker;

    public void setOnClicker(OnClicker onClicker) {
        this.onClicker = onClicker;
    }

    public  interface OnClicker{
        void onClicker(String url);
    }
}
