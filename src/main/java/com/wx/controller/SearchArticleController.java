package com.wx.controller;


import com.alibaba.fastjson2.JSONObject;
import com.wx.service.es.SearchFromEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/spider/es/")
public class SearchArticleController {



    @Autowired
    private SearchFromEsService searchFromEsService;

    @RequestMapping("searchArticle")
    public List<JSONObject> searchArticle(@RequestBody JSONObject reqData) {


        return searchFromEsService.search("articles");


    }
}
