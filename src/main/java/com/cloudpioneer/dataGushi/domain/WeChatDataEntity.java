package com.cloudpioneer.dataGushi.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Tianjinjin on 2016/8/9.
 */
public class WeChatDataEntity implements Serializable
{
    private int id;
    private String userId;
    private String accountId;
    private String categoryId;
    private String categoryType;
    private String groupName;
    private String headPicture;
    private String wxName;
    private String lastUpdateTime;
    private String payTime;
    private String expireTime;
    private String wxid;
    private String username;
    private String openid;
    private String wxBiz;
    private String rank;
    private int articlesNum;
    private int totalReadNum;
    private int totalLikeNum;
    private int avgReadNum;
    private int avgLikeNum;
    private float qualityNum;
    private float influenceNum;
    private float activeNum;
    private int totalOriginalNum;
    private int avgHeadlineNum;
    private String confirm;
    private String tag;
    private String price;
    private Date latestDate;
    private String detail;
    //获取数据的账号名称
    private String account;

    private double di;
    private  double ri;
    private  double li;

    public double getDi() {
        return di;
    }

    public void setDi(double di) {
        this.di = di;
    }

    public double getRi() {
        return ri;
    }

    public void setRi(double ri) {
        this.ri = ri;
    }

    public double getLi() {
        return li;
    }

    public void setLi(double li) {
        this.li = li;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    private boolean deleteFlag;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getHeadPicture() {
        return headPicture;
    }

    public void setHeadPicture(String headPicture) {
        this.headPicture = headPicture;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getWxid() {
        return wxid;
    }

    public void setWxid(String wxid) {
        this.wxid = wxid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getArticlesNum() {
        return articlesNum;
    }

    public void setArticlesNum(int articlesNum) {
        this.articlesNum = articlesNum;
    }

    public int getTotalReadNum() {
        return totalReadNum;
    }

    public void setTotalReadNum(int totalReadNum) {
        this.totalReadNum = totalReadNum;
    }

    public int getTotalLikeNum() {
        return totalLikeNum;
    }

    public void setTotalLikeNum(int totalLikeNum) {
        this.totalLikeNum = totalLikeNum;
    }

    public int getAvgReadNum() {
        return avgReadNum;
    }

    public void setAvgReadNum(int avgReadNum) {
        this.avgReadNum = avgReadNum;
    }

    public int getAvgLikeNum() {
        return avgLikeNum;
    }

    public void setAvgLikeNum(int avgLikeNum) {
        this.avgLikeNum = avgLikeNum;
    }

    public float getQualityNum() {
        return qualityNum;
    }

    public void setQualityNum(float qualityNum) {
        this.qualityNum = qualityNum;
    }

    public float getInfluenceNum() {
        return influenceNum;
    }

    public void setInfluenceNum(float influenceNum) {
        this.influenceNum = influenceNum;
    }

    public float getActiveNum() {
        return activeNum;
    }

    public void setActiveNum(float activeNum) {
        this.activeNum = activeNum;
    }

    public int getTotalOriginalNum() {
        return totalOriginalNum;
    }

    public void setTotalOriginalNum(int totalOriginalNum) {
        this.totalOriginalNum = totalOriginalNum;
    }

    public int getAvgHeadlineNum() {
        return avgHeadlineNum;
    }

    public void setAvgHeadlineNum(int avgHeadlineNum) {
        this.avgHeadlineNum = avgHeadlineNum;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Date getLatestDate() {
        return latestDate;
    }

    public void setLatestDate(Date latestDate) {
        this.latestDate = latestDate;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}