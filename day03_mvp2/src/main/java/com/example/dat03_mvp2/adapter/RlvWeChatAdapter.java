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
import com.example.dat03_mvp2.bean.WeChatBean;

import java.util.ArrayList;

public class RlvWeChatAdapter extends RecyclerView.Adapter<RlvWeChatAdapter.ViewHandler> {
    private Context context;
    private ArrayList<WeChatBean.NewslistBean> list;

    public RlvWeChatAdapter(Context context, ArrayList<WeChatBean.NewslistBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(ArrayList<WeChatBean.NewslistBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHandler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_wechat, null);
        return new ViewHandler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHandler viewHandler, int i) {
        Glide.with(context).load(list.get(i).getPicUrl()).into(viewHandler.img);
        viewHandler.title.setText(list.get(i).getTitle());
        viewHandler.description.setText(list.get(i).getDescription());
        viewHandler.ctime.setText(list.get(i).getCtime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHandler extends RecyclerView.ViewHolder {
       private ImageView img;
       private TextView title;
       private TextView description;
       private TextView ctime;
        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.description);
            ctime=itemView.findViewById(R.id.ctime);
        }
    }
}
