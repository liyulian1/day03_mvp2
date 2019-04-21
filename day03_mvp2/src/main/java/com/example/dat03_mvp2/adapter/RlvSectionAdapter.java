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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.dat03_mvp2.R;
import com.example.dat03_mvp2.bean.SectionBean;

import java.util.ArrayList;

public class RlvSectionAdapter extends RecyclerView.Adapter<RlvSectionAdapter.ViewHandler> {
    private Context context;
    private ArrayList<SectionBean.DataBean> list;

    public RlvSectionAdapter(Context context, ArrayList<SectionBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setList(ArrayList<SectionBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHandler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_section, null);
        return new ViewHandler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHandler viewHandler, int i) {
        //圆角
        RoundedCorners roundedCorners=new RoundedCorners(30);
        RequestOptions options=RequestOptions.bitmapTransform(roundedCorners)
                .override(530,200);//指定图片大小

        Glide.with(context)
                .load(list.get(i).getThumbnail())
                .apply(options)
                .into(viewHandler.img);


      //  Glide.with(context).load(list.get(i).getThumbnail()).into(viewHandler.img);
        viewHandler.name.setText(list.get(i).getName());
        viewHandler.description.setText(list.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHandler extends RecyclerView.ViewHolder {
        private TextView description;
        private ImageView img;
        private TextView name;
        public ViewHandler(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
            description=itemView.findViewById(R.id.description);
        }
    }
}
