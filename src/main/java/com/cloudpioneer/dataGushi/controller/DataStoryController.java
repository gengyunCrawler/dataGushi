package com.cloudpioneer.dataGushi.controller;

import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;
import com.cloudpioneer.dataGushi.service.WeChatDataService;
import com.cloudpioneer.dataGushi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tijun on 2016/8/9.
 */
@RestController
@RequestMapping("/")
public class DataStoryController
{
    @Autowired
    WeChatDataService weChatDataService;

    @RequestMapping(value = "index")
    public String index(int current){

        //weChatDataService.findIimitPage()
        return "index";
    }

    @RequestMapping("wx/data/{currentPage}/{pageSize}/{categoryId}")
    public  Object weChatData(@PathVariable("currentPage")Integer currentPage,@PathVariable("pageSize")Integer pageSize,@PathVariable("categoryId")String categoryId) throws Exception {
     if(categoryId.equals("all")){
         categoryId="";
     }

        Page<WeChatDataEntity> page= weChatDataService.findIimitPage(currentPage, pageSize, categoryId);
        return page;


    }
}
