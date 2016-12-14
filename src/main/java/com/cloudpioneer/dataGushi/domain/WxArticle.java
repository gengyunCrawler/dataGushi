package com.cloudpioneer.dataGushi.domain;

/**
 * Created by Administrator on 2016/12/13.
 */
public class WxArticle {
    private String title;

    private String publishTime;

    private String content;

    private String wxBiz;

    private String wxId;

    private String wxName;

    private int id;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    private String tags;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWxBiz() {
        return wxBiz;
    }

    public void setWxBiz(String wxBiz) {
        this.wxBiz = wxBiz;
    }

    public String getWxId() {
        return wxId;
    }

    public void setWxId(String wxId) {
        this.wxId = wxId;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
