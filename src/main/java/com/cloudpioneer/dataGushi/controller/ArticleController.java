package com.cloudpioneer.dataGushi.controller;

import com.cloudpioneer.dataGushi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/11/30.
 */
@RestController
@RequestMapping("/")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    /**
     * 根据微信公众类别获取文章排行榜
     * @param year
     * @param month
     * @param categoryId 为'all'时是获取全部文章排行榜
     * @param pageNo
     * @param size
     * @return
     */
    @RequestMapping("wx/article/{year}/{month}/{pageNo}/{size}/{categoryId}")
    public Object getByCategory(@PathVariable Integer year, @PathVariable Integer month, @PathVariable String categoryId, @PathVariable Integer pageNo, @PathVariable Integer size){
      if (categoryId==null||categoryId.equals("")||categoryId.equals("all")){
          return articleService.findAll(year, month, pageNo, size);
      }
       return articleService.findAllByCategoryId(year,month,categoryId,pageNo,size);
    }

    @RequestMapping("wx/artilceUrls/{year}/{month}")
    public Object getUrlByMonth(@PathVariable("year")int year,@PathVariable("month")Integer month){
      return   articleService.findUrlByMonth(year,month);
    }
    @RequestMapping("wx/artilceUrls/page/{year}/{month}/{pageNo}/{size}")
    public Object getUrlByPage(@PathVariable Integer year,@PathVariable Integer month,@PathVariable Integer pageNo,@PathVariable Integer size){
     return   articleService.findUrlByPage(year,month,pageNo,size);
    }


}
