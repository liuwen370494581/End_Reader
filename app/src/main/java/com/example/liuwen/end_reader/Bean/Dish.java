package com.example.liuwen.end_reader.Bean;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/10 17:10
 * desc   :
 */
public class Dish {

    private Long id;
    private String title;
    private String url;

    public Dish() {
    }

    public Dish(String title) {
        this.title = title;
    }

    public Dish(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
