package com.cloudpioneer.dataGushi.controller;

import com.cloudpioneer.dataGushi.domain.WeChatDataEntity;
import com.cloudpioneer.dataGushi.domain.WeiboDataEntity;
import com.cloudpioneer.dataGushi.service.WeChatDataService;
import com.cloudpioneer.dataGushi.service.WeiboDataService;
import com.cloudpioneer.dataGushi.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by tijun on 2016/8/9.
 */
@RestController
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


    @RequestMapping("wx/data/{currentPage}/{pageSize}/{categoryId}/{year}/{month}")
    public  Object weChatData(@PathVariable("currentPage")Integer currentPage,@PathVariable("pageSize")Integer pageSize,@PathVariable("categoryId")String categoryId,
                              @PathVariable("year")int year,@PathVariable("month")int month) throws Exception {
     if(categoryId.equals("all")){
         categoryId="";
     }
        Page<WeChatDataEntity> page= weChatDataService.findIimitPage(year,month,currentPage, pageSize, categoryId);
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

    @RequestMapping("weibo/data/{currentPage}/{pageSize}/{categoryId}/{year}/{month}")
    public Object weiBoData(@PathVariable("currentPage")Integer currentPage,@PathVariable("pageSize")Integer pageSize,@PathVariable("categoryId")String categoryId,@PathVariable("year")Integer year,@PathVariable("month")Integer month) throws Exception
    {
        Page<WeiboDataEntity> page=weiboDataService.findPageBycategoryId(categoryId, currentPage, pageSize,year,month);

        return page;
    }

    /**
     * pc 端 type="weixin" /weibo
     * 手机端：mobilewx mobilewb
     * @param type
     * @return
     * @throws Exception
     */
    @RequestMapping("view/{type}")
    public ModelAndView wxWeibo(@PathVariable("type")String type) throws Exception
    {
       return new ModelAndView(type);
    }

    @RequestMapping("data/weibo/gain")
    public String wbDataGain() throws Exception
    {
            weiboDataService.gainData4DB();
        return "success";
    }

    @RequestMapping("data/weixin/gain")
    public String wxDataGain() throws Exception
    {
        weChatDataService.gainData();
        return "success";
    }
}
