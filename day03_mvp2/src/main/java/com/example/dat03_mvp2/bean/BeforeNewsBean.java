package com.example.dat03_mvp2.bean;

import java.util.List;

public class BeforeNewsBean {
    /**
     * date : 20181205
     * stories : [{"images":["https://pic1.zhimg.com/v2-2b56deea9d803c3faac22706abf35574.jpg"],"type":0,"id":9703210,"ga_prefix":"120522","title":"小事 · 你我皆凡人，生在人世间"},{"title":"随便抽一段就是「弱智吧」的风格，这片子到底在讲什么？","ga_prefix":"120521","images":["https://pic4.zhimg.com/v2-dfade464a8e3114beafa6a141b72bfcf.jpg"],"multipic":true,"type":0,"id":9702936},{"images":["https://pic3.zhimg.com/v2-bf16685980c084bfc451bbc787ff8dd2.jpg"],"type":0,"id":9703187,"ga_prefix":"120519","title":"孩子不到六岁就开始撒谎，他怎么能\u2026\u2026这么聪明"},{"images":["https://pic2.zhimg.com/v2-c37ef77d594705ed5a2e0c081b5b5ef5.jpg"],"type":0,"id":9703186,"ga_prefix":"120518","title":"- 我们来做花椒鱼吧\r\n- 可我只有麻椒\r\n- 你这个是藤椒\u2026\u2026"},{"title":"为什么女模特很多都是平胸？","ga_prefix":"120516","images":["https://pic1.zhimg.com/v2-65632b5acfd9071550dec973ed448c7c.jpg"],"multipic":true,"type":0,"id":9703176},{"images":["https://pic1.zhimg.com/v2-d31859680e4410fe6b15050a1941ca3c.jpg"],"type":0,"id":9703093,"ga_prefix":"120513","title":"因为「秃如其来」的年轻人，植发成了一门好生意"},{"images":["https://pic3.zhimg.com/v2-fd966ac1c1956fe586c9e0249dc8d6e2.jpg"],"type":0,"id":9703012,"ga_prefix":"120512","title":"大误 · 灭霸能够拆散多少对情侣？"},{"images":["https://pic4.zhimg.com/v2-f2c1441cb180455192d0b4c27aab4ce3.jpg"],"type":0,"id":9703097,"ga_prefix":"120510","title":"为什么打仗时有的士兵会冒死站在第一排？"},{"images":["https://pic1.zhimg.com/v2-6c450fdf1d3a860d670a2897c927cb44.jpg"],"type":0,"id":9703101,"ga_prefix":"120509","title":"表达，重点到底在「表」还是「达」上？"},{"images":["https://pic1.zhimg.com/v2-1675e2407d2787438cd6556166546b64.jpg"],"type":0,"id":9703052,"ga_prefix":"120508","title":"凛冬将至？对互联网行业人员流动性的一些看法"},{"images":["https://pic3.zhimg.com/v2-c2e28d3d1ba299134733e2100be8917e.jpg"],"type":0,"id":9703086,"ga_prefix":"120507","title":"房价「腰斩」下的燕郊"},{"images":["https://pic4.zhimg.com/v2-f3a64fa69ed3da54e2bcbac4e9da5d83.jpg"],"type":0,"id":9703104,"ga_prefix":"120507","title":"8 年前就落地的携号转网，为什么至今还不能普及？"},{"images":["https://pic2.zhimg.com/v2-2892c325723cdec0591a9dad867e9119.jpg"],"type":0,"id":9703069,"ga_prefix":"120506","title":"瞎扯 · 如何正确地吐槽"}]
     */

    private String date;
    private List<StoriesBean> stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }


}
