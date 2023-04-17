package com.wx.mapper;

import com.wx.entities.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {

    List<Article> page(int pageCount, int pageSize);

    int insert(Article article);

}
