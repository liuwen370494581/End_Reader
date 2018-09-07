package com.example.liuwen.end_reader.Action;

import com.example.liuwen.end_reader.Bean.Dish;

import java.util.ArrayList;
import java.util.List;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/09/07 14:44
 * desc   :搜索书籍的数据类 全部封装在此
 */
public class SearchBookAction {
    public static List<Dish> getReflashData() {
        List<Dish> list = new ArrayList<>();
        list.add(new Dish("黑暗王者"));
        list.add(new Dish("顶级老公,太嚣张"));
        list.add(new Dish("网游之神级机械猎人"));
        list.add(new Dish("爆笑宠妃：爷我等你休妻"));
        list.add(new Dish("司马懿吃三国"));
        list.add(new Dish("总裁大人你轻点"));
        return list;
    }

    public static List<Dish> getReflashData_2() {
        List<Dish> list = new ArrayList<>();
        list.add(new Dish("银河帝国"));
        list.add(new Dish("大主宰"));
        list.add(new Dish("军婚缠绵:大总裁,小甜心"));
        list.add(new Dish("阴阳先生"));
        list.add(new Dish("帝豪老公太狂热"));
        list.add(new Dish("踏天无痕"));
        return list;
    }

    public static List<Dish> getReflashData_3() {
        List<Dish> list = new ArrayList<>();
        list.add(new Dish("豪门天价前妻"));
        list.add(new Dish("斗罗大陆"));
        list.add(new Dish("锦绣清宫:四爷的心尖宠妃"));
        list.add(new Dish("龙血武神"));
        list.add(new Dish("随声英雄杀"));
        list.add(new Dish("铁血宏图"));
        return list;
    }

    public static String getRandomColor() {
        List<String> colorList = new ArrayList<String>();
        colorList.add("#303F9F");
        colorList.add("#FF4081");
        colorList.add("#59dbe0");
        colorList.add("#f57f68");
        colorList.add("#87d288");
        colorList.add("#f8b552");
        colorList.add("#990099");
        colorList.add("#90a4ae");
        colorList.add("#7baaf7");
        colorList.add("#4dd0e1");
        colorList.add("#4db6ac");
        colorList.add("#aed581");
        colorList.add("#f2a600");
        colorList.add("#ff8a65");
        colorList.add("#f48fb1");
        return colorList.get((int) (Math.random() * colorList.size()));
    }

}
