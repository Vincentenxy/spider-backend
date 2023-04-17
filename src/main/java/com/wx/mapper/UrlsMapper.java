package com.wx.mapper;

import com.wx.entities.Urls;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UrlsMapper {

    List<Urls> list();

    int selectById(String url);

    int insert(Urls urls);
}
