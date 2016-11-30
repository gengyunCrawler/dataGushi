package com.cloudpioneer.dataGushi.domain;

import java.util.Date;

/**
 * Created by Tijun on 2016/11/22.
 */
public class ArticleEntity {

    private String openid;

    private String wxBiz;

    private int hour;

    private String groupName;

    private  String title;

    private String url;

    private Date date;

    private  Date publishTime;

    private  String author;

    private int readNum;

    private  int likeNum;

    private int headLineNum;

    private int isOriginal;

    private String articleId;

    private String wxName;

    private Date lastDate;

    private String wxHeadPicture;

    private String account;
    private String categoryId;

    private String categoryType;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getWxHeadPicture() {
        return wxHeadPicture;
    }

    public void setWxHeadPicture(String wxHeadPicture) {
        this.wxHeadPicture = wxHeadPicture;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getWxBiz() {
        return wxBiz;
    }

    public void setWxBiz(String wxBiz) {
        this.wxBiz = wxBiz;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getHeadLineNum() {
        return headLineNum;
    }

    public void setHeadLineNum(int headLineNum) {
        this.headLineNum = headLineNum;
    }

    public int getIsOriginal() {
        return isOriginal;
    }

    public void setIsOriginal(int isOriginal) {
        this.isOriginal = isOriginal;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
