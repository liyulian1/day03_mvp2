package com.example.dat03_mvp2.widget;

public interface TouchCallBack {
    //交换条目位置
    void onItemMove(int fromPosition ,int toPosition);
    //删除条目
    void onItemDelete(int position);
}
