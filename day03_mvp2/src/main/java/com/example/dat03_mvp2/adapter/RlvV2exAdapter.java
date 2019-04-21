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
import com.example.dat03_mvp2.bean.V2exListBean;

import java.util.ArrayList;

public class RlvV2exAdapter extends RecyclerView.Adapter<RlvV2exAdapter.ViewHandler> {
    private Context context;
    private ArrayList<V2exListBean> list;

    public RlvV2exAdapter(Context context, ArrayList<V2exListBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(ArrayList<V2exListBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHandler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_v2ex, null);
        return new ViewHandler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHandler viewHandler, int i) {
        Glide.with(context).load("https:"+list.get(i).getUrl()).into(viewHandler.img);
        viewHandler.title.setText(list.get(i).getTitle());
        viewHandler.pinglun.setText(list.get(i).getPinglun());
        viewHandler.secTab.setText(list.get(i).getSecTab());
        viewHandler.zuozhe.setText(list.get(i).getZuozhe());
        viewHandler.zui.setText(list.get(i).getZui());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHandler extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView title;
        private TextView pinglun;
        private TextView secTab;
        private TextView zuozhe;
        private TextView zui;
        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            title=itemView.findViewById(R.id.title);
            pinglun=itemView.findViewById(R.id.pinglun);
            secTab=itemView.findViewById(R.id.secTab);
            zuozhe=itemView.findViewById(R.id.zuozhe);
            zui=itemView.findViewById(R.id.zui);
        }
    }
}
