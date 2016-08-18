package com.cloudpioneer.dataGushi.domain;

import java.util.Date;

/**
 * Created by tijun on 2016/8/9.
 */
public class WeiboDataEntity
{
    private String uid;

    private String nickName;//微博昵称

    private String head;//头像地址

    private int followCount;//粉丝数


    private float rateActivity;//活跃度

    private  int interactNum;//互动数

    private int newFllowCount;//新增数

    private float qualityIndex;//质量指数

    private float influence;//影响力

    private String categoryId;//分类id

    private String categoryName;//分类名称

    private Date searchDate;//查询日期（只关心月份）

    private String deleteFlag;

    public Date getSearchDate()
    {
        return searchDate;
    }

    public void setSearchDate(Date searchDate)
    {
        this.searchDate = searchDate;
    }

    public String getDeleteFlag()
    {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag)
    {
        this.deleteFlag = deleteFlag;
    }

    public String getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(String categoryId)
    {
        this.categoryId = categoryId;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getHead()
    {
        return head;
    }

    public void setHead(String head)
    {
        this.head = head;
    }

    public int getFollowCount()
    {
        return followCount;
    }

    public void setFollowCount(int followCount)
    {
        this.followCount = followCount;
    }

    public float getRateActivity()
    {
        return rateActivity;
    }

    public void setRateActivity(float rateActivity)
    {
        this.rateActivity = rateActivity;
    }

    public int getInteractNum()
    {
        return interactNum;
    }

    public void setInteractNum(int interactNum)
    {
        this.interactNum = interactNum;
    }

    public int getNewFllowCount()
    {
        return newFllowCount;
    }

    public void setNewFllowCount(int newFllowCount)
    {
        this.newFllowCount = newFllowCount;
    }

    public float getQualityIndex()
    {
        return qualityIndex;
    }

    public void setQualityIndex(float qualityIndex)
    {
        this.qualityIndex = qualityIndex;
    }

    public float getInfluence()
    {
        return influence;
    }

    public void setInfluence(float influence)
    {
        this.influence = influence;
    }
}
