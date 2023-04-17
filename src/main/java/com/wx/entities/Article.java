package com.wx.entities;

import lombok.Data;

import java.sql.Date;

@Data
public class Article {

    private Integer id;

    private String title;

    private String type;

    private String url;

    private String content;

    private Date datetime;

}
