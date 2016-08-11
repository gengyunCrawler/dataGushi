package com.cloudpioneer.dataGushi.controller;

import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;
import com.cloudpioneer.dataGushi.domain.WeiboDataEntity;
import com.cloudpioneer.dataGushi.service.WeChatDataService;
import com.cloudpioneer.dataGushi.service.WeiboDataService;
import com.cloudpioneer.dataGushi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by tijun on 2016/8/9.
 */
@Controller
@RequestMapping("/")
public class DataStoryController
{
    @Autowired
    private WeChatDataService weChatDataService;

    @Autowired
    private WeiboDataService weiboDataService;
    @RequestMapping(value = "index")
    public String index(int current){

        //weChatDataService.findIimitPage()
        return "index";
    }

    @ResponseBody
    @RequestMapping("wx/data/{currentPage}/{pageSize}/{categoryId}")
    public  Object weChatData(@PathVariable("currentPage")Integer currentPage,@PathVariable("pageSize")Integer pageSize,@PathVariable("categoryId")String categoryId) throws Exception {
     if(categoryId.equals("all")){
         categoryId="";
     }
        Page<WeChatDataEntity> page= weChatDataService.findIimitPage(currentPage, pageSize, categoryId);
        return page;
    }

    /**
     * 如果ca
     * @param currentPage
     * @param pageSize
     * @param categoryId categoryId=="all" 即为返回全部
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("weibo/data/{currentPage}/{pageSize}/{categoryId}")
    public Object weiBoData(@PathVariable("currentPage")Integer currentPage,@PathVariable("pageSize")Integer pageSize,@PathVariable("categoryId")String categoryId) throws Exception
    {
        Page<WeiboDataEntity> page=weiboDataService.findPageBycategoryId(categoryId,currentPage,pageSize);

        return page;
    }


    @RequestMapping("view/{type}")
    public String wxWeibo(@PathVariable("type")String type) throws Exception
    {
        if (type.equals("weixin")){
            return "index";

        }else if (type.equals("weibo")){
            return "weibo";
        }
        return "erro";
        }
}
