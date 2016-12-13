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

    @RequestMapping("wx/article/{year}/{month}/{pageNo}/{size}/{categoryId}")
    public Object getByCategory(@PathVariable Integer year, @PathVariable Integer month, @PathVariable String categoryId, @PathVariable Integer pageNo, @PathVariable Integer size){
      if (categoryId==null||categoryId.equals("")||categoryId.equals("all")){
          return articleService.findAll(year, month, pageNo, size);
      }
       return articleService.findAllByCategoryId(year,month,categoryId,pageNo,size);
    }
}