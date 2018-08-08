package com.example.liuwen.end_reader.Bean;

import android.support.annotation.Nullable;

import java.io.Serializable;

/**
 * author : liuwen
 * e-mail : liuwen370494581@163.com
 * time   : 2018/08/08 14:37
 * desc   :
 */
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;//书名
    private String chapterUrl;//书目Url
    private String imgUrl;//封面图片url
    private String desc;//简介
    private String author;//作者
    private String type;//类型
    private String updateDate;//更新时间
    private String newestChapterId;//最新章节id
    private String newestChapterTitle;//最新章节标题
    private String newestChapterUrl;//最新章节url
    private String historyChapterId;//上次关闭时的章节ID
    private int histtoryChapterNum;//上次关闭时的章节数
    private int sortCode;//排序编码
    private int noReadNum;//未读章数量
    private int chapterTotalNum;//总章节数
    private int lastReadPosition;//上次阅读到的章节的位置


    public Book() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChapterUrl() {
        return chapterUrl;
    }

    public void setChapterUrl(String chapterUrl) {
        this.chapterUrl = chapterUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getNewestChapterId() {
        return newestChapterId;
    }

    public void setNewestChapterId(String newestChapterId) {
        this.newestChapterId = newestChapterId;
    }

    public String getNewestChapterTitle() {
        return newestChapterTitle;
    }

    public void setNewestChapterTitle(String newestChapterTitle) {
        this.newestChapterTitle = newestChapterTitle;
    }

    public String getNewestChapterUrl() {
        return newestChapterUrl;
    }

    public void setNewestChapterUrl(String newestChapterUrl) {
        this.newestChapterUrl = newestChapterUrl;
    }

    public String getHistoryChapterId() {
        return historyChapterId;
    }

    public void setHistoryChapterId(String historyChapterId) {
        this.historyChapterId = historyChapterId;
    }

    public int getHisttoryChapterNum() {
        return histtoryChapterNum;
    }

    public void setHisttoryChapterNum(int histtoryChapterNum) {
        this.histtoryChapterNum = histtoryChapterNum;
    }

    public int getSortCode() {
        return sortCode;
    }

    public void setSortCode(int sortCode) {
        this.sortCode = sortCode;
    }

    public int getNoReadNum() {
        return noReadNum;
    }

    public void setNoReadNum(int noReadNum) {
        this.noReadNum = noReadNum;
    }

    public int getChapterTotalNum() {
        return chapterTotalNum;
    }

    public void setChapterTotalNum(int chapterTotalNum) {
        this.chapterTotalNum = chapterTotalNum;
    }

    public int getLastReadPosition() {
        return lastReadPosition;
    }

    public void setLastReadPosition(int lastReadPosition) {
        this.lastReadPosition = lastReadPosition;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", chapterUrl='" + chapterUrl + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", desc='" + desc + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", newestChapterId='" + newestChapterId + '\'' +
                ", newestChapterTitle='" + newestChapterTitle + '\'' +
                ", newestChapterUrl='" + newestChapterUrl + '\'' +
                ", historyChapterId='" + historyChapterId + '\'' +
                ", histtoryChapterNum=" + histtoryChapterNum +
                ", sortCode=" + sortCode +
                ", noReadNum=" + noReadNum +
                ", chapterTotalNum=" + chapterTotalNum +
                ", lastReadPosition=" + lastReadPosition +
                '}';
    }
}
