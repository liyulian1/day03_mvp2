package com.example.dat03_mvp2.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dat03_mvp2.R;
import com.example.dat03_mvp2.adapter.RlvV2exAdapter;
import com.example.dat03_mvp2.bean.V2exListBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class V2exItemFragment extends Fragment {


    private View view;
    private RecyclerView mMRl;
    private String mUrl = "https://www.v2ex.com/";
    private String pinglun;
    private String zuozhe;
    private String text;
    private ArrayList<V2exListBean> list;
    private RlvV2exAdapter rlvV2exAdapter;

    public V2exItemFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public V2exItemFragment(String link) {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_v2ex_item, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private static final String TAG = "V2exItemFragment";
    private void initData() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect(mUrl).get();
                    Elements items = doc.select("div.cell.item");
                    list = new ArrayList<>();
                    for (Element item: items) {
                        //图片
                        Element image = item.select("table tr td a > img.avatar").first();
                        String src = image.attr("src");
                        Log.d(TAG, "src:"+src);

                        //评论数量和评论连接地址
                        Elements comment = item.select("table tbody tr td a.count_livid");
                        if (comment!=null){
                            String href = comment.attr("href");
                            pinglun = comment.text();
                            Log.d(TAG, "评论: "+",连接："+href+",数量："+ pinglun);
                        }
                        //标题
                        Element title = item.select("table tbody tr td span.item_title > a").first();
                        String titleText = title.text();
                        Log.d(TAG, "标题："+titleText);

                        //topic_info
                        Element topic = item.select("table tbody tr td span.topic_info").first();
                        Element secondaryTab = topic.select("a.node").first();
                        String secTab = secondaryTab.text();
                        Log.d(TAG, "secTab: "+secTab);

                        Elements people = topic.select("strong > a");
                        if (people.size()>0){
                            //作者
                            Element element = people.get(0);
                            zuozhe = element.text();
                            Log.d(TAG, "作者: "+ zuozhe);
                        }

                        if (people.size()>1){
                            //最后的评论者
                            Element element = people.get(1);
                            text = element.text();
                            Log.d(TAG, "最后的评论者: "+ text);
                        }
                        V2exListBean v2exListBean = new V2exListBean(src, titleText, pinglun, secTab, zuozhe, text);
                        list.add(v2exListBean);


                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                rlvV2exAdapter = new RlvV2exAdapter(getContext(), list);
                        mMRl.setAdapter(rlvV2exAdapter);
                        rlvV2exAdapter.notifyDataSetChanged();
                            }
                        });
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void initView(View inflate) {
        mMRl = (RecyclerView) inflate.findViewById(R.id.mRl);
        mMRl.setLayoutManager(new LinearLayoutManager(getContext()));
        mMRl.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));


    }
}
