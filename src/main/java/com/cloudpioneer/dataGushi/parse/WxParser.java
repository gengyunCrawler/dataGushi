package com.cloudpioneer.dataGushi.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;

/**
 * Created by Administrator on 2017/1/6.
 */
public class WxParser {

    /**
     * 将detail中的数据转换为 entity中的数据
     * @param entity
     * @param detail
     * @return
     */
    public static WeChatDataEntity  parseFromDetail(WeChatDataEntity entity,String detail){
        if (detail==null||"".equals(detail)){
            return entity;
        }
        /**进入json解析过程*/
        JSONObject map = JSON.parseObject(detail);
        JSONObject userInfo = map.getJSONObject("items").getJSONObject("userInfo");
        Integer articlesNum = userInfo.getInteger("articlesNum");
        Integer totalReadNum = userInfo.getInteger("totalReadNum");
        Integer totalLikeNum = userInfo.getInteger("totalLikeNum");
        Float qualityNum = userInfo.getFloat("qualityNum");
        Float influenceNum = userInfo.getFloat("influenceNum");
        Float activeNum = userInfo.getFloat("activeNum");
        Integer totalOriginalNum = userInfo.getInteger("totalOriginalNum");
        Integer avgHeadlineNum = userInfo.getInteger("avgHeadlineNum");
        entity.setArticlesNum(articlesNum);
        entity.setTotalReadNum(totalReadNum);
        entity.setTotalLikeNum(totalLikeNum);
        entity.setAvgReadNum((int)Math.round((totalReadNum*1.0)/articlesNum));
        entity.setAvgLikeNum((int)Math.round((totalLikeNum*1.0)/articlesNum));
        entity.setQualityNum(qualityNum);
        entity.setInfluenceNum(influenceNum);
        entity.setActiveNum(activeNum);
        entity.setTotalOriginalNum(totalOriginalNum);
        entity.setAvgHeadlineNum(avgHeadlineNum);
        entity.setDetail(detail);

        /**处理文章平均头条数*/
       JSONArray articles =  map.getJSONObject("items").getJSONArray("articles");
        int totalHeadLineNum = 0;
        if (articles!=null){
            for (int i = 0;i<articles.size();i++){
                totalHeadLineNum += articles.getJSONObject(i).getInteger("headLineNum");
            }
        }
        entity.setAvgHeadlineNum((int)Math.round((totalHeadLineNum*0.1)/articlesNum));
        return entity;
    }
}
