package com.cloudpioneer.dataGushi.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;
import com.cloudpioneer.dataGushi.domain.WeiboCategorysEntity;
import com.cloudpioneer.dataGushi.domain.WeiboDataEntity;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
public class DataStoryParse
{
    public static List<WeiboDataEntity> parseJson4Weibo(String weiboJson) throws IOException
    {
        List<WeiboDataEntity> weiboDataEntities=null;
        JSONObject object=JSON.parseObject(weiboJson);
        if (object==null){
            return null;
        }
        JSONObject data=object.getJSONObject("data");
        if(data==null){
            return null;
        }
        //
        JSONArray accountList=data.getJSONArray("accountList");
        JSONArray interactStatsList=data.getJSONArray("interactStatsList");

        //得到分类id映射
        JSONObject categorys=data.getJSONObject("categorys");

        if(accountList!=null){
            weiboDataEntities=new ArrayList<WeiboDataEntity>();
            for (int i=0;i<accountList.size();i++){
                JSONObject account=accountList.getJSONObject(i);
                JSONObject interact=interactStatsList.getJSONObject(i);
                WeiboDataEntity weiboDataEntity=new WeiboDataEntity();
                weiboDataEntity.setUid(account.getString("uid"));
                weiboDataEntity.setNickName(account.getString("nickName"));
                weiboDataEntity.setHead(account.getString("head"));
                weiboDataEntity.setFollowCount(account.getIntValue("followCount"));
                weiboDataEntity.setRateActivity(account.getFloatValue("rateActivity"));
                weiboDataEntity.setCategoryId(categorys.getString(weiboDataEntity.getUid()));

                if (interact!=null){
                    weiboDataEntity.setNewFllowCount(interact.getIntValue("thisWeekFansIncr"));
                    weiboDataEntity.setInteractNum(interact.getIntValue("thisweek"));
                }
                weiboDataEntities.add(weiboDataEntity);
            }
        }
        return weiboDataEntities;
    }



    public static List<WeChatDataEntity> parseWeChatJSONData(String jsonData) throws ParseException
    {

        List<WeChatDataEntity> arrayList=new ArrayList<WeChatDataEntity>();
        JSONObject mainObj=JSONObject.parseObject(jsonData);
        if(mainObj!=null){
            //JSONObject weChatDataList=  mainObj.getJSONObject("data").getJSONObject("Object").getJSONObject("accountList").getJSONObject("Array").getJSONObject("Object");
            JSONObject weChatDataList=  mainObj.getJSONObject("data");
            JSONArray jsonArray = weChatDataList.getJSONArray("accountList");
            for(int i=0;i<jsonArray.size();i++){
                WeChatDataEntity weChatObj=new WeChatDataEntity();
                JSONObject jsonObject=(JSONObject)jsonArray.get(i);
                weChatObj.setUserId(jsonObject.getString("userId"));
                weChatObj.setAccountId(jsonObject.getString("accountId"));
                weChatObj.setCategoryId(jsonObject.getString("categoryId"));
                weChatObj.setCategoryType(jsonObject.getString("categoryType"));
                weChatObj.setGroupName(jsonObject.getString("groupName"));
                weChatObj.setHeadPicture("http://open.weixin.qq.com/biz/headimg?wxid=" + jsonObject.getString("wxid"));
                weChatObj.setWxName(jsonObject.getString("wxName"));
                weChatObj.setLastUpdateTime(jsonObject.getString("lastUpdateTime"));
                weChatObj.setPayTime(jsonObject.getString("payTime"));
                weChatObj.setExpireTime(jsonObject.getString("expireTime"));
                weChatObj.setWxid(jsonObject.getString("wxid"));
                weChatObj.setUsername(jsonObject.getString("username"));
                weChatObj.setOpenid(jsonObject.getString("openid"));
                weChatObj.setWxBiz(jsonObject.getString("wxBiz"));
                weChatObj.setRank(jsonObject.getString("rank"));
                weChatObj.setArticlesNum(Integer.parseInt(jsonObject.getString("articlesNum")));
                weChatObj.setTotalReadNum(Integer.parseInt(jsonObject.getString("totalReadNum")));
                weChatObj.setTotalLikeNum(Integer.parseInt(jsonObject.getString("totalLikeNum")));
                weChatObj.setAvgReadNum(Integer.parseInt(jsonObject.getString("avgReadNum")));
                weChatObj.setAvgLikeNum(Integer.parseInt(jsonObject.getString("avgLikeNum")));
                weChatObj.setQualityNum(Float.parseFloat(jsonObject.getString("qualityNum")));
                weChatObj.setInfluenceNum(Float.parseFloat(jsonObject.getString("influenceNum")));
                weChatObj.setActiveNum(Float.parseFloat(jsonObject.getString("activeNum")));
                weChatObj.setTotalOriginalNum(Integer.parseInt(jsonObject.getString("totalOriginalNum")));
                weChatObj.setAvgHeadlineNum(Integer.parseInt(jsonObject.getString("avgHeadlineNum")));
                weChatObj.setConfirm(jsonObject.getString("confirm"));
                weChatObj.setTag(jsonObject.getString("tag"));
                weChatObj.setPrice(jsonObject.getString("price"));
                //String currentDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                //weChatObj.setLatestDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(currentDate));
                weChatObj.setLatestDate(new Date());
                weChatObj.setDeleteFlag(true);
                arrayList.add(weChatObj);
            }
        }
        return arrayList;
    }

    public static List<WeiboCategorysEntity> parseWeiboCategory(String weiboCategoryJson){
        JSONObject jsonObject=JSON.parseObject(weiboCategoryJson);
        JSONArray categorys=jsonObject.getJSONArray("data");
        List<WeiboCategorysEntity> categorysEntities=new ArrayList<>();
        for (Object categoryObj:categorys){
            JSONObject category= (JSONObject) categoryObj;
            WeiboCategorysEntity categorysEntity=new WeiboCategorysEntity();
            String s=category.getString("categoryId");
            categorysEntity.setCategoryId(category.getString("categoryId"));
            categorysEntity.setCategoryName(category.getString("categoryName"));
            categorysEntities.add(categorysEntity);
        }
        if(categorysEntities.size()==0){
            return null;
        }
        WeiboCategorysEntity weiboCategorysEntity=new WeiboCategorysEntity();
        weiboCategorysEntity.setCategoryId("0");
        weiboCategorysEntity.setCategoryName("未分组");
        categorysEntities.add(weiboCategorysEntity);
        return categorysEntities;
    }
}
