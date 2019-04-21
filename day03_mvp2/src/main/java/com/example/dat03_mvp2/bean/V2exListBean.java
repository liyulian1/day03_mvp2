package com.example.dat03_mvp2.bean;

public class V2exListBean {
    private String url;
    private String title;
    private String pinglun;
    private String secTab;
    private String zuozhe;
    private String zui;

    public V2exListBean(String url, String title, String pinglun, String secTab, String zuozhe, String zui) {
        this.url = url;
        this.title = title;
        this.pinglun = pinglun;
        this.secTab = secTab;
        this.zuozhe = zuozhe;
        this.zui = zui;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPinglun() {
        return pinglun;
    }

    public void setPinglun(String pinglun) {
        this.pinglun = pinglun;
    }

    public String getSecTab() {
        return secTab;
    }

    public void setSecTab(String secTab) {
        this.secTab = secTab;
    }

    public String getZuozhe() {
        return zuozhe;
    }

    public void setZuozhe(String zuozhe) {
        this.zuozhe = zuozhe;
    }

    public String getZui() {
        return zui;
    }

    public void setZui(String zui) {
        this.zui = zui;
    }
}
